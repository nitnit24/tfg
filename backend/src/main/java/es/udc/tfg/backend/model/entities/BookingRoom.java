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
	private String roomTypeName;
	private int roomTypeCapacity;
	private String tariffName;
	private Booking booking;
	private Set<BookingDay> bookingDays = new HashSet<>();
	
	public BookingRoom(){
		
	}
	
//	public BookingRoom(int quantity) {
//		this.quantity = quantity;
//	}
//
//	public BookingRoom(Long id, int quantity, BigDecimal roomTotalPrice) {
//		this.id = id;
//		this.quantity = quantity;
//		this.roomTotalPrice = roomTotalPrice;
//	}
//	
	
	public BookingRoom(int quantity, String roomTypeName, int roomTypeCapacity, String tariffName) {
		this.quantity = quantity;
		this.roomTypeName = roomTypeName;
		this.roomTypeCapacity = roomTypeCapacity;
		this.tariffName = tariffName;
	}
	
	public BookingRoom(Long id, int quantity, BigDecimal roomTotalPrice, String roomTypeName, int roomTypeCapacity,
			String tariffName) {
		this.id = id;
		this.quantity = quantity;
		this.roomTotalPrice = roomTotalPrice;
		this.roomTypeName = roomTypeName;
		this.roomTypeCapacity = roomTypeCapacity;
		this.tariffName = tariffName;
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
	
	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public int getRoomTypeCapacity() {
		return roomTypeCapacity;
	}

	public void setRoomTypeCapacity(int roomTypeCapacity) {
		this.roomTypeCapacity = roomTypeCapacity;
	}

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
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
