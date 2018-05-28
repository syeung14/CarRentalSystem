package com.carrental.model;

public class Vehicle {
	private String plate;
	private String vin;
	private int year;
	private String make;
	private VehicleType type;
	
	public Vehicle(Builder builder) {
		this.make = builder.make;
		this.type = builder.type;
		this.year = builder.year;
		this.plate = builder.plate;
		this.vin = builder.vin;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public VehicleType getType() {
		return type;
	}
	public void setType(VehicleType type) {
		this.type = type;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + year;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (type != other.type)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Vin:").append(getVin()).append(",")
		.append("Make:").append(getMake()).append(",")
		.append("Year:").append(getYear()).append(",")
		.append("Type:").append(getType());
		return sb.toString();
	}
	public static class Builder {
		private int year;
		private String make;
		private VehicleType type;
		
		private String plate;
		private String vin;
		
		public Builder(String make, VehicleType type, int year) {
			this.year = year;
			this.make = make;
			this.type = type;
		}
		
		public Builder plate(String val) {
			this.plate = val;
			return this;
		}
		public Builder vin(String val) {
			this.vin = val;
			return this;
		}
		public Vehicle build() {
			return new Vehicle(this);
		}
	}
	
}
