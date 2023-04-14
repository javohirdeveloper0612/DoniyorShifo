package com.example.exp.telegram;

public class TelegramBotException extends RuntimeException{
    public TelegramBotException(String message) {
        super(message);
    }
}
