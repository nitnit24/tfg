package es.udc.tfg.backend.rest.dtos;

import java.math.BigDecimal;

public class BookingDayDto {

	public interface AllValidations {
	}

	private Long id;
	private BigDecimal dayPrice;
	private Long day;
	private Long saleRoomTariffId;
	private Long bookingRoomId;

	public BookingDayDto() {
	}

	public BookingDayDto(Long id, BigDecimal dayPrice, Long day, Long saleRoomTariffId, Long bookingRoomId) {
		super();
		this.id = id;
		this.dayPrice = dayPrice;
		this.day = day;
		this.saleRoomTariffId = saleRoomTariffId;
		this.bookingRoomId = bookingRoomId;
	}

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
	
	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public Long getSaleRoomTariffId() {
		return saleRoomTariffId;
	}

	public void setSaleRoomTariffId(Long saleRoomTariffId) {
		this.saleRoomTariffId = saleRoomTariffId;
	}

	public Long getBookingRoomId() {
		return bookingRoomId;
	}

	public void setBookingRoomId(Long bookingRoomId) {
		this.bookingRoomId = bookingRoomId;
	}
	

	
}
