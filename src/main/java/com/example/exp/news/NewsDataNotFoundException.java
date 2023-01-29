package com.example.exp.news;

public class NewsDataNotFoundException extends RuntimeException {
    public NewsDataNotFoundException(String message) {
        super(message);
    }
}
