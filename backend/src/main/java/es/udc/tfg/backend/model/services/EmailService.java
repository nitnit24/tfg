package es.udc.tfg.backend.model.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import es.udc.tfg.backend.model.entities.Booking;
import es.udc.tfg.backend.model.entities.Hotel;
import es.udc.tfg.backend.model.entities.RoomType;

public interface  EmailService {


	public void sendMsgBookingToClient(Booking booking, Hotel hotel) throws UnsupportedEncodingException, IOException;
	
	public void sendMsgBookingToHotel(Booking booking, Hotel hotel) throws UnsupportedEncodingException, IOException;

	public void sendMsgUpdateBookingToClient(Booking booking, Hotel hotel) throws UnsupportedEncodingException, IOException;

	public void sendMsgUpdateBookingToHotel(Booking booking, Hotel hotel) throws UnsupportedEncodingException, IOException;

	public void sendMsgCancelBookingToClient(Booking booking, Hotel hotel) throws UnsupportedEncodingException, IOException;

	public void sendMsgCancelBookingToHotel(Booking booking, Hotel hotel) throws UnsupportedEncodingException, IOException;
	
	public void sendMsgFreeRoomsZero(RoomType roomType, Calendar date, Hotel hotel) throws UnsupportedEncodingException, IOException; 
}