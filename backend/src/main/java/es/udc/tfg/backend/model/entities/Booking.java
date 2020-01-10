package es.udc.tfg.backend.model.entities;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Booking {


	private Long id;
	private String key;
	private String locator;
	private Set<BookingRoom> bookingRooms = new HashSet<>();
	private Calendar date;
	private Calendar startDate;
	private int duration;
	private Calendar endDate;
	private State state;
	private String name;
	private String surName;
	private String phone;
	private String email;
	private String petition;
	private BigDecimal totalPrice;
	
	public Booking () {
		
	}
	
	public Booking(Calendar date, Calendar startDate,int duration, Calendar endDate, State state,
			String name, String surName, String phone, String email, String petition) {
		this.date= date;
		this.startDate = startDate;
		this.duration = duration;
		this.endDate = endDate;
		this.state = state;
		this.name = name;
		this.surName = surName;
		this.phone = phone;
		this.email = email;
		this.petition = petition;
	}

	public Booking( String key, String locator, Calendar date, Calendar startDate,int duration, Calendar endDate,
			State state, String name, String surName, String phone, String email, String petition) {
		this.key = key;
		this.locator = locator;
		this.date= date;
		this.startDate = startDate;
		this.duration = duration;
		this.endDate = endDate;
		this.state = state;
		this.name = name;
		this.surName = surName;
		this.phone = phone;
		this.email = email;
		this.petition = petition;
	}
	
	public Booking(Long id, String key, String locator, Calendar date, Calendar startDate,
			int duration, Calendar endDate, String name, String surName, String phone, String email, String petition,
			BigDecimal totalPrice) {
		this.id= id;
		this.key = key;
		this.locator = locator;
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


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="bookingKey")
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


	@OneToMany(mappedBy="booking")
	public Set<BookingRoom> getBookingRooms() {
		return bookingRooms;
	}

	public void setBookingRooms(Set<BookingRoom> bookingRooms) {
		this.bookingRooms = bookingRooms;
	}
	
	public void addBookingRoom(BookingRoom bookingRoom) {
		
		bookingRooms.add(bookingRoom);
		bookingRoom.setBooking(this);
		
	}

	public Calendar getDate() {
		return date;
	}


	public void setDate(Calendar date) {
		if (date != null) {
			this.date.set(Calendar.MILLISECOND, 0);
			this.date.set(Calendar.SECOND, 0);
		}
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


	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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
