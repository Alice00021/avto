package com.example.demo.controller.dto.responses;

public class FibResponse {
    private int n;
    private long result;

    public FibResponse(int n, long result) {
        this.n = n;
        this.result = result;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }
}

