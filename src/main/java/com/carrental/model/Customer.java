package com.carrental.model;

import java.time.LocalDate;
import java.time.Period;

public class Customer {
	private String userName;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String gender;
	private String phone;
	private String email;
	private String address;

	private Customer(Builder builder) {
		this.userName = builder.userName;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.dob = builder.dob;
		this.gender = builder.gender;
		this.phone = builder.phone;
		this.email = builder.email;
		this.address = builder.address;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}
	
	public static class Builder {
		private String userName;
		private String firstName;
		private String lastName;
		private LocalDate dob;
		
		private String gender;
		private String phone;
		private String email;
		private String address;
		
		public Builder(String userName, LocalDate dob, String email) {
			this.userName = userName;
			this.dob = dob;
			this.email = email;
		}
		
		public Builder firstName(String val) {
			this.firstName = val;
			return this;
		}
		public Builder lastName(String val) {
			this.lastName = val;
			return this;
		}
		public Builder gender(String val) {
			this.gender = val;
			return this;
		}
		public Builder phone(String val) {
			this.phone = val;
			return this;
		}
		public Builder address(String val) {
			this.address = val;
			return this;
		}
		
		public Customer build() {
			return new Customer(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Customer other = (Customer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb =new StringBuilder();
		sb.append("UserName: ").append(getUserName()).append(",")
		 	.append("email: ").append(getEmail()).append(",")
		 	.append("dob: ").append(getDob()).append(",")
		 	.append("gender: ").append(getGender()).append(",")
		 	.append("lastname: ").append(getLastName());
		 	
		return sb.toString();
	}
	
	
}
