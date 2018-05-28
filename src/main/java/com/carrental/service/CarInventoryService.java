package com.carrental.service;

import com.carrental.model.Vehicle;

public interface CarInventoryService {
	public int getInventory(Vehicle vehicle);
	public void updateInventory(Vehicle vehicle, int quantity);
	public boolean isEnoughInventory(Vehicle vehicle, int quantity);
}
