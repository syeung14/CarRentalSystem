package com.carrental.exception;

public class AgeIsBelowLimitException extends RuntimeException {
	private String message;
   
    public AgeIsBelowLimitException(String string) {
        this.message = string;
    }
   
    @Override
    public String getMessage(){
        return message;
    }
}
