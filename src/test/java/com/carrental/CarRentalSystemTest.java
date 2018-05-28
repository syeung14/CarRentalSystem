package com.carrental;

import org.junit.Before;

import com.carrental.model.Customer;
import com.carrental.model.Vehicle;
import com.carrental.service.CarInventoryService;
import com.carrental.service.NotificationService;
import com.carrental.service.ReservationService;
import com.carrental.service.impl.CarInventoryServiceImpl;
import com.carrental.service.impl.NotificationServiceImpl;
import com.carrental.service.impl.ReservationServiceImpl;

public class CarRentalSystemTest {
	private CarRentalSystem  carRentalSystem;
	private Vehicle[] vehicle;
	private Customer[] customers;

	@Before
	public void setUp() {
		CarInventoryService carInventoryServ = new CarInventoryServiceImpl();
		NotificationService notificationServ = new NotificationServiceImpl();
		ReservationService reservationServ = new ReservationServiceImpl(carInventoryServ);
		carRentalSystem = new CarRentalSystemImpl(notificationServ, reservationServ);

	}

}
