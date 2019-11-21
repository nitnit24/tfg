package es.udc.tfg.backend.rest.dtos;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.udc.tfg.backend.model.entities.RoomType;

public class SaleRoomDto {

	public interface AllValidations {
	}

	private Long idSaleRoom;
	private Calendar date;
	private int freeRooms;
	private RoomType roomType;

	public SaleRoomDto() {
	}

	
	public SaleRoomDto(Long idSaleRoom, Calendar date, int freeRooms, RoomType roomType) {
		this.idSaleRoom = idSaleRoom;
		this.date = date;
		if (date != null) {
			this.date.set(Calendar.MILLISECOND, 0);
			this.date.set(Calendar.SECOND, 0);
			this.date.set(Calendar.MINUTE, 0);
			this.date.set(Calendar.HOUR_OF_DAY, 00);
		}
		this.freeRooms = freeRooms;
		this.roomType = roomType;
	}


	public Long getIdSaleRoom() {
		return idSaleRoom;
	}

	public void setIdSaleRoom(Long idSaleRoom) {
		this.idSaleRoom = idSaleRoom;
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
	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	
	
	

}