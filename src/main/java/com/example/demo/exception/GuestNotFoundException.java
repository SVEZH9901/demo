package com.example.demo.exception;


import java.io.Serial;

public class GuestNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1;

    public GuestNotFoundException(String message){
        super(message);
    }
}
