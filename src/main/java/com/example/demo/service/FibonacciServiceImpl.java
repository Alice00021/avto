package com.example.demo.service;

import com.example.demo.service.contracts.FibonacciService;
import org.springframework.stereotype.Service;

@Service
public class FibonacciServiceImpl implements FibonacciService {

    @Override
    public long fibonacci(int n) {
        if (n <= 1) return n;

        long a = 0;
        long b = 1;

        for (int i = 2; i <= n; i++) {
            long next = a + b;
            a = b;
            b = next;
        }

        return b;
    }
}
