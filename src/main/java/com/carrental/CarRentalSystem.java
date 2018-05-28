package com.carrental;

import com.carrental.model.Reservation;

/**
 * Central class drives the entire Vehicle reservation process
 *   Reserve a Vehicle
 *   Cancel the reservation.
 *   send notification to customers
 * 
 * @author sangsinyeung
 */
public interface CarRentalSystem {
	
	public void makeReservation(Reservation reservation);
	public boolean checkReservation(Reservation reservation);
	public void cancellationReservation(Reservation reservation);
}
