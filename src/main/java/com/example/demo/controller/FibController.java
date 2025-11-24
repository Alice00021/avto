package com.example.demo.controller;


import com.example.demo.controller.dto.responses.FibResponse;
import com.example.demo.service.contracts.FibonacciService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibController {

    private final FibonacciService fibonacciService; 

    public FibController(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    @GetMapping("/fib")
    public FibResponse fib(@RequestParam int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n должно быть >= 0");
        }
        long result = fibonacciService.fibonacci(n);
        
        return new FibResponse(n, result);
    }

}

