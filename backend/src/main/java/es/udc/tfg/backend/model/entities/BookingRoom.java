package es.udc.tfg.backend.model.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class BookingRoom {

	private Long id;
	private int quantity;
	private BigDecimal roomTotalPrice;
	private Booking booking;
	private Set<BookingDay> bookingDays = new HashSet<>();
	
	public BookingRoom(){
		
	}
	
	public BookingRoom(int quantity) {
		this.quantity = quantity;
	}

	public BookingRoom(Long id, int quantity, BigDecimal roomTotalPrice) {
		this.id = id;
		this.quantity = quantity;
		this.roomTotalPrice = roomTotalPrice;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getRoomTotalPrice() {
		return roomTotalPrice;
	}

	public void setRoomTotalPrice(BigDecimal roomTotalPrice) {
		this.roomTotalPrice = roomTotalPrice;
	}
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name= "idBooking")
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@OneToMany(mappedBy="bookingRoom")
	public Set<BookingDay> getBookingDays() {
		return bookingDays;
	}

	public void setBookingDays(Set<BookingDay> bookingDays) {
		this.bookingDays = bookingDays;
	}
	
	public void addBookingDay(BookingDay bookingDay) {
		
		bookingDays.add(bookingDay);
		bookingDay.setBookingRoom(this);
		
	}

	
}
