package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibController {

    @GetMapping("/fib")
    public long fib(@RequestParam int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n должно быть >= 0");
        }
        return fibonacci(n);
    }

    private long fibonacci(int n) {
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

