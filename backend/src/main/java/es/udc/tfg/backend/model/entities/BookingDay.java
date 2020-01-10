package es.udc.tfg.backend.model.entities;

import java.math.BigDecimal;

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
	private SaleRoomTariff saleRoomTariff;
	private BookingRoom bookingRoom;
	
	public BookingDay(){
		
	}

	public BookingDay(Long id, BigDecimal dayPrice, SaleRoomTariff saleRoomTariff, BookingRoom bookingRoom) {
		this.id = id;
		this.dayPrice = dayPrice;
		this.saleRoomTariff = saleRoomTariff;
		this.bookingRoom = bookingRoom;
	}
	
	public BookingDay(BigDecimal dayPrice, SaleRoomTariff saleRoomTariff) {
		this.dayPrice = dayPrice;
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
