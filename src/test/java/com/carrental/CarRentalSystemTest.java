package com.carrental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.carrental.exception.AgeIsBelowLimitException;
import com.carrental.exception.NotEnoughInventoryException;
import com.carrental.exception.ReservationAlreadyExistsException;
import com.carrental.exception.ReservationNotFoundException;
import com.carrental.exception.TooManyVehicleInReservationException;
import com.carrental.model.Customer;
import com.carrental.model.Reservation;
import com.carrental.model.Vehicle;
import com.carrental.model.VehicleType;
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
	
	private CarInventoryService  carInvServ;

	@Before
	public void setUp() {
		carInvServ = new CarInventoryServiceImpl();
		NotificationService notificationServ = new NotificationServiceImpl();
		ReservationService reservationServ = new ReservationServiceImpl(carInvServ);
		carRentalSystem = new CarRentalSystemImpl(notificationServ, reservationServ);
		
		vehicle = new Vehicle[] {
				new Vehicle.Builder("Chevy", VehicleType.VAN, 1967).vin("VIN-299-2923-342").build(),
				new Vehicle.Builder("Audi", VehicleType.COMPACT, 1990).vin("VIN-523-9258-670").build(),
				new Vehicle.Builder("Jaquar", VehicleType.PICKUP, 1987).vin("VIN-943-4399-518").build()
		};
		customers = new Customer[] {
				new Customer.Builder("Kathy", LocalDate.of(1983, 11, 01), "Kathy.Prashant@ANI.COM").gender("F").build(),
				new Customer.Builder("Ingrid", LocalDate.of(1983, 11, 01), "Ingrid.Welles@TEAL.COM").firstName("Ingrid")
				.lastName("Welles").gender("M").phone("3013517625").build(),
				new Customer.Builder("Dom", LocalDate.of(2000, 03, 10), "Dom.Hoskins@AVOCET.COM").firstName("Dom")
				.lastName("Hoskins").gender("M").phone("3011188009").build()
		};
	}
	@Test
	public void customerMakeOneReservation() {
		carInvServ.addToInventory(vehicle[0], 1);
		
		Reservation reservation =new Reservation(vehicle[0], customers[0], LocalDate.of(2018, 6, 1), LocalDate.of(2018, 6, 5), 1);
		carRentalSystem.makeReservation(reservation);
		
		assertEquals(true, carRentalSystem.checkReservation(reservation));
		
		assertEquals(0, carInvServ.getInventory(vehicle[0]));
	}
	@Test(expected=NotEnoughInventoryException.class)
	public void customerMakeOneReservationButOutofInventory() {
		carInvServ.addToInventory(vehicle[0], 0);
		
		Reservation reservation = new Reservation(vehicle[0], customers[0], LocalDate.of(2018, 6, 1), LocalDate.of(2018, 6, 5), 1);
		carRentalSystem.makeReservation(reservation);
		
	}
	@Test(expected=ReservationAlreadyExistsException.class)
	public void customerMakeAnotherReservationWithinSamePeriod() {
		carInvServ.addToInventory(vehicle[0], 2);
		
		Reservation reservation =new Reservation(vehicle[0], customers[0], LocalDate.of(2018, 6, 1), LocalDate.of(2018, 6, 5), 1);
		carRentalSystem.makeReservation(reservation);
		
		carInvServ.addToInventory(vehicle[1], 1);
		reservation =new Reservation(vehicle[1], customers[0], LocalDate.of(2018, 6, 5), LocalDate.of(2018, 6, 5), 1);
		carRentalSystem.makeReservation(reservation);
	}
	@Test
	public void customerMakeAnotherReservationInDifferentPeriod() {
		carInvServ.addToInventory(vehicle[0], 2);
		
		Reservation reservation1 =new Reservation(vehicle[0], customers[0], LocalDate.of(2018, 6, 1), LocalDate.of(2018, 6, 5), 1);
		carRentalSystem.makeReservation(reservation1);
		assertEquals(true, carRentalSystem.checkReservation(reservation1));

		carInvServ.addToInventory(vehicle[1], 1);
		Reservation reservation2 =new Reservation(vehicle[1], customers[0], LocalDate.of(2018, 6, 6), LocalDate.of(2018, 6, 5), 1);
		carRentalSystem.makeReservation(reservation2);
		assertEquals(true, carRentalSystem.checkReservation(reservation2));
	}
	@Test
	public void twoCustomersMakeReservationInSamePeriodandSameVehicle() {
		carInvServ.addToInventory(vehicle[0], 2);
		
		Reservation reservation1 =new Reservation(vehicle[0], customers[0], LocalDate.of(2018, 6, 1), LocalDate.of(2018, 6, 5), 1);
		carRentalSystem.makeReservation(reservation1);
		assertEquals(true, carRentalSystem.checkReservation(reservation1));
		
		Reservation reservation2 =new Reservation(vehicle[0], customers[1], LocalDate.of(2018, 6, 1), LocalDate.of(2018, 6, 5), 1);
		carRentalSystem.makeReservation(reservation2);
		assertEquals(true, carRentalSystem.checkReservation(reservation2));
		
		assertEquals(0, carInvServ.getInventory(vehicle[0]));
	}
	@Test
	public void testReservationDays() {
		carInvServ.addToInventory(vehicle[0], 1);
		
		Reservation reservation1 =new Reservation(vehicle[0], customers[0], LocalDate.of(2018, 6, 1), LocalDate.of(2018, 6, 5), 1);
		carRentalSystem.makeReservation(reservation1);
		assertEquals(true, carRentalSystem.checkReservation(reservation1));
		
		assertEquals(5, reservation1.getReservationDays());
	}

	@Test(expected=TooManyVehicleInReservationException.class)
	public void customerReserveMoreThanOneVehicleInOneReservation() {
		carInvServ.addToInventory(vehicle[0], 2);
		
		Reservation reservation1 =new Reservation(vehicle[0], customers[0], LocalDate.of(2018, 6, 1), LocalDate.of(2018, 6, 5), 2);
		carRentalSystem.makeReservation(reservation1);
	}
	@Test(expected=AgeIsBelowLimitException.class)
	public void customerIsYoungtoMakeReservation() {
		carInvServ.addToInventory(vehicle[0], 1);
		
		Reservation reservation1 =new Reservation(vehicle[0], customers[2], LocalDate.of(2018, 6, 1), LocalDate.of(2018, 6, 5), 1);
		carRentalSystem.makeReservation(reservation1);
	}
	@Test
	public void cancellationReservation() {
		carInvServ.addToInventory(vehicle[0], 2);
		
		Reservation reservation1 =new Reservation(vehicle[0], customers[0], LocalDate.of(2018, 6, 1), LocalDate.of(2018, 6, 5), 1);
		carRentalSystem.makeReservation(reservation1);
		assertEquals(true, carRentalSystem.checkReservation(reservation1));

		carRentalSystem.cancellationReservation(reservation1);
		assertEquals(false, carRentalSystem.checkReservation(reservation1));
		assertEquals(2, carInvServ.getInventory(vehicle[0]));
		
	}
	@Test(expected=ReservationNotFoundException.class)
	public void cancellationReservationThatDoesntExists() {
		carInvServ.addToInventory(vehicle[0], 2);
		
		Reservation reservation1 =new Reservation(vehicle[0], customers[0], LocalDate.of(2018, 6, 1), LocalDate.of(2018, 6, 5), 1);
		carRentalSystem.cancellationReservation(reservation1);
		
	}
	
}
