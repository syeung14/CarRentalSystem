package com.carrental.model;

public enum VehicleType {
	VAN, PICKUP, COMPACT, LUXURY, ECONOMY;

	@Override
	public String toString() {
		switch (this) {
		case VAN:
			return "Van";
		case PICKUP:
			return "Pickup";
		case COMPACT:
			return "Compact";
		case LUXURY:
			return "Liuxury";
		case ECONOMY:
			return "Economy";
		default:
			return "Unknown";
		}
	}
}
