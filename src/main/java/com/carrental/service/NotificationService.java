package com.carrental.service;

import com.carrental.model.Reservation;

/**
 * NotificationService send notification to customer once the reservation is confirmed or cancelled.
 * Different ways could be used to send notification such as email, sms etc.
 *  
 * @author sangsinyeung
 *
 */
public interface NotificationService {
	
	public void notifyCustomer(Reservation reservation);

}
