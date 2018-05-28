package com.carrental.exception;

public class NotEnoughInventoryException extends RuntimeException {
	private String message;
   
    public NotEnoughInventoryException(String string) {
        this.message = string;
    }
   
    @Override
    public String getMessage(){
        return message;
    }
}
