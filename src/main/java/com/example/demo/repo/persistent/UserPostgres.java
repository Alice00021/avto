package com.example.demo.repo.persistent;

import com.example.demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UserPostgres {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public Optional<User> getByEmail(String email) {
        User user = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                      .setParameter("email", email)
                      .getResultStream()
                      .findFirst()
                      .orElse(null);
        return Optional.ofNullable(user);
    }

    @Transactional
    public User create(User user) {
        em.persist(user); 
        return user;      
    }

    public Optional<User> getById(Long id){
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Transactional
    public void delete(Long id){
        User existing = em.find(User.class, id);
         if (existing != null) {
            em.remove(existing);
        }
    }
}
