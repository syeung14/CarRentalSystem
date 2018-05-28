package com.carrental.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carrental.exception.NotEnoughInventoryException;
import com.carrental.exception.ReservationNotFoundException;
import com.carrental.model.Customer;
import com.carrental.model.Reservation;
import com.carrental.service.CarInventoryService;
import com.carrental.service.ReservationService;

public class ReservationServiceImpl implements ReservationService {
	//a list of vehicles.

	private CarInventoryService carInventoryServ;
	private Map<Customer, List<Reservation>>reservationStore;
//	private Map<Reservation, Customer>reservationlst;
	
	public ReservationServiceImpl(CarInventoryService carInventoryServ) {
		super();
		this.carInventoryServ = carInventoryServ;
		this.reservationStore = new HashMap<>();
//		this.reservationlst = new HashMap<>();
	}

	@Override
	public boolean reserve(Reservation reservation) {
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
		List<Reservation>reservations =  reservationStore.get(reservation.getCustomer());
		//a customer is not allowed to make more than one reservation in the same period. 
		//he/she is allowed to make more than more reservations in different period however.
		for (Reservation r: reservations) {
			if (r.isWithIn(r.getStartDate())) {
				return false;
			}
		} //
		carInventoryServ.updateInventory(reservation.getVechcle(), -1);
		if (!reservationStore.containsKey(reservation.getCustomer())) {
			reservationStore.put(reservation.getCustomer(), new ArrayList<>());
		}
		reservationStore.get(reservation.getCustomer()).add(reservation);
//		reservationlst.put(reservation, reservation.getCustomer());
		return true;
	}

	@Override
	public void cancelReservation(Reservation reservation) {
//		Customer customer = reservationlst.get(reservation);
		Customer customer = reservation.getCustomer();
		List<Reservation> reservations = reservationStore.get(customer);
		
		if (customer != null && !reservations.contains(reservation)) {
			reservations.remove(reservation);
//			reservationlst.remove(reservation);
		} else { 
			throw new ReservationNotFoundException(String.format(" Reservation %s doesn't exist", reservation));
		}
	}

	@Override
	public boolean isReservationExist(Reservation reservation) {
		return reservationStore.get(reservation.getCustomer()).contains(reservation);
	}
}
