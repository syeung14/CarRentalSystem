package com.carrental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.carrental.model.Vehicle;
import com.carrental.model.VehicleType;
import com.carrental.service.CarInventoryService;
import com.carrental.service.impl.CarInventoryServiceImpl;

public class CarInventoryServiceTest {
	private CarInventoryService  carInvServ;
	private Vehicle[] vehicle;

	@Before
	public void setUp() {
		carInvServ = new CarInventoryServiceImpl();
		vehicle = new Vehicle[] {
				new Vehicle.Builder("Chevy", VehicleType.VAN, 1967).vin("VIN-299-2923-342").build(),
				new Vehicle.Builder("Audi", VehicleType.COMPACT, 1990).vin("VIN-523-9258-670").build(),
				new Vehicle.Builder("Jaquar", VehicleType.PICKUP, 1987).vin("VIN-943-4399-518").build()
		};
	}

	@Test
	public void testCarInventoryCreation() {
		carInvServ.updateInventory(vehicle[0], 3);
		int inventory = carInvServ.getInventory(vehicle[0]);
		assertEquals(3, inventory);
	}
	@Test
	public void testCarInventoryReduction() {
		carInvServ.updateInventory(vehicle[1], 3);
		carInvServ.updateInventory(vehicle[1], -1);
		int inventory = carInvServ.getInventory(vehicle[1]);
		assertEquals(2, inventory);
	}
	@Test
	public void testIsEnoughCarInventory() {

		boolean isEnough = carInvServ.isEnoughInventory(vehicle[1], 1);
		assertEquals(false, isEnough);

		carInvServ.updateInventory(vehicle[1], 1);
		isEnough = carInvServ.isEnoughInventory(vehicle[1], 1);
		assertEquals(true, isEnough);
	}
}
