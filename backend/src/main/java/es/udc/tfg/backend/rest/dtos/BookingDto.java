package es.udc.tfg.backend.rest.dtos;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class BookingDto {

	public interface AllValidations {
	}

	private Long id;
	private String key;
	private String locator;
	private List<BookingRoomDto> bookingRooms;
	private Calendar date;
	private Calendar startDate;
	private int duration;
	private Calendar endDate;
	private String name;
	private String surName;
	private String phone;
	private String email;
	private String petition;
	private BigDecimal totalPrice;

	public BookingDto() {
	}

	public BookingDto(Long id, String key, String locator, List<BookingRoomDto> bookingRooms, Calendar date,
			Calendar startDate, int duration, Calendar endDate, String name, String surName, String phone, String email,
			String petition, BigDecimal totalPrice) {
		this.id = id;
		this.key = key;
		this.locator = locator;
		this.bookingRooms = bookingRooms;
		this.date = date;
		this.startDate = startDate;
		this.duration = duration;
		this.endDate = endDate;
		this.name = name;
		this.surName = surName;
		this.phone = phone;
		this.email = email;
		this.petition = petition;
		this.totalPrice = totalPrice;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
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

	public String getPetition() {
		return petition;
	}

	public void setPetition(String petition) {
		this.petition = petition;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}


}
