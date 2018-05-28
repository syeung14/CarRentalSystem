package com.carrental.service;

import com.carrental.model.Vehicle;

/**
 * CarInventoryService manages the Vehicle inventory data.
 * 
 * @author sangsinyeung
 *
 */
public interface CarInventoryService {
	public int getInventory(Vehicle vehicle);
	public void addToInventory(Vehicle vehicle, int quantity);
	public boolean isEnoughInventory(Vehicle vehicle, int quantity);
}
