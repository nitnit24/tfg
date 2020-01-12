package es.udc.tfg.backend.model.entities;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class BookingDay {

	private Long id;
	private BigDecimal dayPrice;
	private Calendar day;
	private SaleRoomTariff saleRoomTariff;
	private BookingRoom bookingRoom;
	
	public BookingDay(){
		
	}

	public BookingDay(Long id, BigDecimal dayPrice, Calendar day, SaleRoomTariff saleRoomTariff, BookingRoom bookingRoom) {
		this.id = id;
		this.dayPrice = dayPrice;
		this.day = day;
		this.saleRoomTariff = saleRoomTariff;
		this.bookingRoom = bookingRoom;
	}
	
	public BookingDay(BigDecimal dayPrice, Calendar day, SaleRoomTariff saleRoomTariff) {
		this.dayPrice = dayPrice;
		this.day = day;
		this.saleRoomTariff = saleRoomTariff;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public BigDecimal getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(BigDecimal dayPrice) {
		this.dayPrice = dayPrice;
	}
	

	public Calendar getDay() {
		return day;
	}

	public void setDay(Calendar day) {
		this.day = day;
	}

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name= "idSaleRoomTariff")
	public SaleRoomTariff getSaleRoomTariff() {
		return saleRoomTariff;
	}

	public void setSaleRoomTariff(SaleRoomTariff saleRoomTariff) {
		this.saleRoomTariff = saleRoomTariff;
	}

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name= "idBookingRoom")
	public BookingRoom getBookingRoom() {
		return bookingRoom;
	}

	public void setBookingRoom(BookingRoom bookingRoom) {
		this.bookingRoom = bookingRoom;
	}
	
	
	
}
