package com.carrental.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.carrental.model.Vehicle;
import com.carrental.service.CarInventoryService;

public class CarInventoryServiceImpl implements CarInventoryService {

	Map<Vehicle, Integer> inventoryList;
	
	public CarInventoryServiceImpl() {
		this.inventoryList = new HashMap<>();
	}

	public Map<Vehicle, Integer> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(Map<Vehicle, Integer> inventoryList) {
		this.inventoryList = inventoryList;
	}
	@Override
	public void updateInventory(Vehicle vehicle, int quantity) {
		if (inventoryList.containsKey(vehicle)) {
			quantity = quantity + inventoryList.get(vehicle);
		}
		inventoryList.put(vehicle, quantity);
	}

	@Override
	public boolean isEnoughInventory(Vehicle vehicle, int quantity) {
		Integer totalQty = inventoryList.get(vehicle);
		if (totalQty == null || totalQty == 0) {
			return false;
		}
		if (totalQty >= quantity) {
			return true;
		}
		return false;
	}

	@Override
	public int getInventory(Vehicle vehicle) {
		return inventoryList.get(vehicle);
		
	}
	
}
