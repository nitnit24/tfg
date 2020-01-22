package es.udc.tfg.backend.rest.dtos;

import java.math.BigDecimal;
import java.util.List;

public class BookingRoomDto {

	public interface AllValidations {
	}

	private Long id;
	private int quantity;
	private BigDecimal roomTotalPrice;
	private String roomTypeName;
	private int roomTypeCapacity;
	private String tariffName;
	private Long bookingId;
	private List<BookingDayDto> bookingDays;

	public BookingRoomDto() {
	}
	

	public BookingRoomDto(Long id, int quantity, BigDecimal roomTotalPrice, String roomTypeName, int roomTypeCapacity,
			String tariffName, Long bookingId, List<BookingDayDto> bookingDays) {
		this.id = id;
		this.quantity = quantity;
		this.roomTotalPrice = roomTotalPrice;
		this.roomTypeName = roomTypeName;
		this.roomTypeCapacity = roomTypeCapacity;
		this.tariffName = tariffName;
		this.bookingId = bookingId;
		this.bookingDays = bookingDays;
	}


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


	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public List<BookingDayDto> getBookingDays() {
		return bookingDays;
	}

	public void setBookingDays(List<BookingDayDto> bookingDays) {
		this.bookingDays = bookingDays;
	}

	
}
