package com.carrental;

import com.carrental.exception.NotEnoughInventoryException;
import com.carrental.model.Reservation;
import com.carrental.service.NotificationService;
import com.carrental.service.ReservationService;
/**
 *  Central class drives the entire Vehicle reservation process
 *  
 * @author sangsinyeung
 *
 */
public class CarRentalSystemImpl implements CarRentalSystem {
 	
	private NotificationService notificationServ;
	private ReservationService reservationServ;
	public CarRentalSystemImpl(NotificationService notificationServ, ReservationService reservationServ) {
		super();
		this.notificationServ = notificationServ;
		this.reservationServ = reservationServ;
	}

	@Override
	public void makeReservation(Reservation reservation) {
		
		try {
			if (reservationServ.reserve(reservation))
				notificationServ.notifyCustomer(reservation);
			else {
				System.out.println(String.format("Reservatoin was not succesful."));
			}
			
		} catch (NotEnoughInventoryException e) {
			throw e;
		}
	}

	@Override
	public boolean checkReservation(Reservation reservation) {
		return reservationServ.isReservationExist(reservation);
	}

	@Override
	public void cancellationReservation(Reservation reservation) {
		reservationServ.cancelReservation(reservation);;
	}
}
