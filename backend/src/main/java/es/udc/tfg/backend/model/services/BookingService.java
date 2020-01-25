package es.udc.tfg.backend.model.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;

import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.Booking;
import es.udc.tfg.backend.model.entities.BookingRoomSummary;
import es.udc.tfg.backend.model.entities.FreeRoomType;

public interface BookingService {

	List<FreeRoomType> findFreeRooms(Calendar startDate, Calendar endDate, int people, int rooms);
	
	Booking makeBooking(List<BookingRoomSummary> bookingRoomSummarys, Calendar startDate, Calendar endDate, String name, 
			String surName, String phone, String email, String petition) throws InstanceNotFoundException, ThereAreNotEnoughtFreeRoomsException, UnsupportedEncodingException, IOException;
	
	Booking findByLocator(String locator) throws InstanceNotFoundException;
	
	Booking findByLocatorAndKey(String locator, String key) throws IncorrectFindLocatorKeyException;
	
	Booking cancel(String locator, String key) throws InstanceNotFoundException;
	
	Block<Booking> findBookings(String dataType, Calendar minDate, Calendar maxDate, String keywords, int page, int size);
	
}