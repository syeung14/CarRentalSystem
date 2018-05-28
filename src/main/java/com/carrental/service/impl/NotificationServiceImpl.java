package com.carrental.service.impl;

import com.carrental.model.Reservation;
import com.carrental.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {

	@Override
	public void notifyCustomer(Reservation reservation) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Your reservation %s has been confirmed.", reservation));
		
		System.out.println(sb.toString());
	}

}
