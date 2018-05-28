package com.carrental.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carrental.exception.AgeIsBelowLimitException;
import com.carrental.exception.NotEnoughInventoryException;
import com.carrental.exception.ReservationAlreadyExistsException;
import com.carrental.exception.ReservationNotFoundException;
import com.carrental.exception.TooManyVehicleInReservationException;
import com.carrental.model.Customer;
import com.carrental.model.Reservation;
import com.carrental.service.CarInventoryService;
import com.carrental.service.ReservationService;
/**
 * Reservation Service is the controller to drive the entire reservation.
 * It validates the reservation and customer objects to ensure they satisfy the constraions before making any reservation.
 * 
 * It uses CarInventorySerivce to update the inventory after cancelled and made reservation. 
 * 
 * @author sangsinyeung
 *
 */
public class ReservationServiceImpl implements ReservationService {
	
	private final static int LIMIT_PER_RESERVATION = 1;
	private final static int MINIMAL_RESERVATION_AGE = 25;
	//a list of vehicles.
	private CarInventoryService carInventoryServ;
	/**
	 * storeage stores the entire reservation data.
	 */
	private Map<Customer, List<Reservation>>reservationStore;
	
	public ReservationServiceImpl(CarInventoryService carInventoryServ) {
		super();
		this.carInventoryServ = carInventoryServ;
		this.reservationStore = new HashMap<>();
	}

	@Override
	public boolean reserve(Reservation reservation) {
		
		if (reservation.getCustomer().getAge() < MINIMAL_RESERVATION_AGE) {
			throw new AgeIsBelowLimitException(String.format("Minimal age is %s to reserve a vehicle.", MINIMAL_RESERVATION_AGE));
		}
		
		if (reservation.getQuantity() > LIMIT_PER_RESERVATION) {
			throw new TooManyVehicleInReservationException("only one vehicle can be reserved per reservation.");
		}
		
		boolean reservered = false;
		if (checkInventory(reservation)) {
			reservered = confirmReservation(reservation);
		} else {
			throw new NotEnoughInventoryException(String.format("Vehicle [%s] can't be reserved." , reservation.getVechcle()));
		}
		
		return reservered;
	}

	private boolean checkInventory(Reservation reservation) {
		return carInventoryServ.isEnoughInventory(reservation.getVechcle(), reservation.getQuantity());
	}
	
	private boolean confirmReservation(Reservation reservation) {
		if (!reservationStore.containsKey(reservation.getCustomer())) {
			reservationStore.put(reservation.getCustomer(), new ArrayList<>());
		}
		List<Reservation> reservations = reservationStore.get(reservation.getCustomer());
		
		//a customer is not allowed to make more than one reservation in the same period. 
		//he/she is allowed to make more than one reservations in different period however.
		for (Reservation r: reservations) {
			if (r.isWithIn(reservation.getStartDate())) {
				throw new ReservationAlreadyExistsException(String.format(" Reservation %s ", reservation));
			}
		} //
		carInventoryServ.addToInventory(reservation.getVechcle(), -1 * reservation.getQuantity());
		reservationStore.get(reservation.getCustomer()).add(reservation);
		return true;
	}

	@Override
	public void cancelReservation(Reservation reservation) {
		Customer customer = reservation.getCustomer();
		List<Reservation> reservations = reservationStore.get(customer);
		
		if (reservations != null && reservations.contains(reservation)) {
			reservations.remove(reservation);
			carInventoryServ.addToInventory(reservation.getVechcle(), 1); //add one to the inventory
		} else { 
			throw new ReservationNotFoundException(String.format(" Reservation %s doesn't exist", reservation));
		}
	}

	@Override
	public boolean isReservationExist(Reservation reservation) {
		return reservationStore.get(reservation.getCustomer()).contains(reservation);
	}
}
