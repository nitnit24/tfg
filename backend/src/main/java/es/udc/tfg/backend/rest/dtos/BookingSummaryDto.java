package es.udc.tfg.backend.rest.dtos;

import java.math.BigDecimal;
import java.util.List;

import es.udc.tfg.backend.model.entities.State;

public class BookingSummaryDto {
	
	private Long hotelId;
	private String locator;
	private List<BookingRoomDto> bookingRooms;
	private long date;
	private String name;
	private String surname;
	private long startDate;
	private long endDate;
	private State state;
	private BigDecimal totalPrice;
	
	public BookingSummaryDto() {}


	public BookingSummaryDto(Long hotelId, String locator, List<BookingRoomDto> bookingRooms, long date, String name, String surname,
			long startDate, long endDate, State state, BigDecimal totalPrice) {
		this.hotelId = hotelId;
		this.locator = locator;
		this.bookingRooms = bookingRooms;
		this.date = date;
		this.name =  name;
		this.surname =  surname;
		this.startDate = startDate;
		this.endDate = endDate;
		this.state = state;
		this.totalPrice = totalPrice;
	}



	public Long getHotelId() {
		return hotelId;
	}


	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}


	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	
	public List<BookingRoomDto> getBookingRooms() {
		return bookingRooms;
	}

	public void setBookingRooms(List<BookingRoomDto> bookingRooms) {
		this.bookingRooms = bookingRooms;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}





}
