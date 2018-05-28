package com.carrental.model;

import java.time.LocalDate;
import java.time.Period;

public class Reservation {
	private Vehicle vechcle;
	private Customer customer;
	
	private LocalDate creationDate;
	private int quantity;
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Reservation(Vehicle vechcle, Customer customer, LocalDate startDate, LocalDate endDate, int quantity) {
		super();
		this.vechcle = vechcle;
		this.customer = customer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationDate = LocalDate.now();
		this.quantity = quantity;
	}

	public Vehicle getVechcle() {
		return vechcle;
	}

	public void setVechcle(Vehicle vechcle) {
		this.vechcle = vechcle;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getReservedDay() {
		return Period.between(this.startDate, endDate).getDays();
	}
	
	public boolean isWithIn(LocalDate reservationDate) {
		
		return startDate.minusDays(1).isBefore(reservationDate) && endDate.plusDays(1).isAfter(reservationDate);
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((vechcle == null) ? 0 : vechcle.hashCode());
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
		Reservation other = (Reservation) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (vechcle == null) {
			if (other.vechcle != null)
				return false;
		} else if (!vechcle.equals(other.vechcle))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Customer : ").append(customer).append(",")
			.append("Vehicle: " ).append(vechcle).append(",")
			.append("Startdate: ").append(startDate).append(",")
			.append("enddate: ").append(endDate);
		return sb.toString();
	}
	
}
