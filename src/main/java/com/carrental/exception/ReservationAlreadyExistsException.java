package com.carrental.exception;

public class ReservationAlreadyExistsException  extends RuntimeException {
	private String message;
   
    public ReservationAlreadyExistsException(String string) {
        this.message = string;
    }
   
    @Override
    public String getMessage(){
        return message;
    }
}
