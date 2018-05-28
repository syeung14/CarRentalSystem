package com.carrental.service;

import com.carrental.model.Reservation;

/**
 * Common interface for making car reservation.
 * 
 * @author sangsinyeung
 *
 */
public interface ReservationService {

	public boolean reserve(Reservation reservation);
	public void cancelReservation(Reservation reservation);
	public boolean isReservationExist(Reservation reservation);
}
