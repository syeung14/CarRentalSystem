package com.carrental.exception;

public class ReservationNotFoundException extends RuntimeException {
	private String message;
   
    public ReservationNotFoundException(String string) {
        this.message = string;
    }
   
    @Override
    public String getMessage(){
        return message;
    }
}
