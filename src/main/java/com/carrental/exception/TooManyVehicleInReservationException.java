package com.carrental.exception;

public class TooManyVehicleInReservationException  extends RuntimeException {
	private String message;
   
    public TooManyVehicleInReservationException(String string) {
        this.message = string;
    }
   
    @Override
    public String getMessage(){
        return message;
    }

}
