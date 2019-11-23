package es.udc.tfg.backend.rest.dtos;

import java.util.Calendar;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SaleRoomDto {

	public interface AllValidations {
	}

	private Long saleRoomId;
	private Calendar date;
	private int freeRooms;
	private Long roomTypeId;

	public SaleRoomDto() {
	}

	
	public SaleRoomDto(Long saleRoomId, Calendar date, int freeRooms, Long roomTypeId) {
		this.saleRoomId = saleRoomId;
		this.date = date;
		if (date != null) {
			this.date.set(Calendar.MILLISECOND, 0);
			this.date.set(Calendar.SECOND, 0);
			this.date.set(Calendar.MINUTE, 0);
			this.date.set(Calendar.HOUR_OF_DAY, 00);
		}
		this.freeRooms = freeRooms;
		this.roomTypeId = roomTypeId;
	}


	public Long getSaleRoomId() {
		return saleRoomId;
	}

	public void setSaleRoomId(Long saleRoomId) {
		this.saleRoomId = saleRoomId;
	}

	@NotNull
	public Calendar getDate() {
		this.date.set(Calendar.MILLISECOND, 0);
		this.date.set(Calendar.SECOND, 0);
		this.date.set(Calendar.MINUTE, 0);
		this.date.set(Calendar.HOUR_OF_DAY, 00);
		return date;
	}


	public void setDate(Calendar date) {
		this.date = date;
		if (date != null) {
			this.date.set(Calendar.MILLISECOND, 0);
			this.date.set(Calendar.SECOND, 0);
			this.date.set(Calendar.MINUTE, 0);
			this.date.set(Calendar.HOUR_OF_DAY, 00);
		}
	}


	@NotNull
	@Min(value=0)
	public int getFreeRooms() {
		return freeRooms;
	}

	
	public void setFreeRooms(int freeRooms) {
		this.freeRooms = freeRooms;
	}

	@NotNull
	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	
	
	

}