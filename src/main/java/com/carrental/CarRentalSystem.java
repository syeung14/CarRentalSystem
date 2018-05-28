package com.carrental;

import com.carrental.model.Customer;
import com.carrental.model.Reservation;

/**
 * 
 * @author sangsinyeung
 */
public interface CarRentalSystem {
	
	public void makeReservation(Customer customer, Reservation reservation);
}
