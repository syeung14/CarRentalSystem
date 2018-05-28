package com.carrental;

import com.carrental.model.Customer;
import com.carrental.model.Reservation;
import com.carrental.model.Vehicle;

/**
 * 
 * @author sangsinyeung
 */
public interface CarRentalSystem {
	
//	public void updateInventory(Vehicle vehicle, int quantity);
	public void makeReservation(Customer customer, Reservation reservation);
}
