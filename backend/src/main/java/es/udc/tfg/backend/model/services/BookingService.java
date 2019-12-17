package es.udc.tfg.backend.model.services;

import java.util.Calendar;
import java.util.List;

import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.SaleRoomTariff;
import es.udc.tfg.backend.model.entities.Tariff;

public interface BookingService {

//	Booking addBooking(List<BookingRoom> bookingRooms, Calendar startDate, Calendar endDate, String name, 
//			String surName, String phone, String email, String petition) throws InstanceNotFoundException;

	List<RoomType> findFreeRooms(Calendar startDate, Calendar endDate, int people, int rooms);
	
	List<Tariff> findTariffsByFreeRoom(Calendar startDate, Calendar endDate, Long roomTypeId);
}