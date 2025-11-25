package com.example.demo.controller.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FibResponse {
    private int n;
    private long result;
}
