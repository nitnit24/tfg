package es.udc.tfg.backend.rest.dtos;

import java.util.Calendar;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddToSaleRoomParamsDto {
	
	private Long idRoomType;
	private Calendar date;
	private int freeRooms;
	
	public AddToSaleRoomParamsDto() {}

	@NotNull
	public Long getIdRoomType() {
		return idRoomType;
	}

	public void setIdRoomType(Long idRoomType) {
		this.idRoomType = idRoomType;
	}

	@NotNull
	public Calendar getDate() {
		return date;
	}

	
	public void setDate(Calendar date) {
		this.date = date;
	}

	@NotNull
	@Min(value=1)
	public int getFreeRooms() {
		return freeRooms;
	}

	
	public void setFreeRooms(int freeRooms) {
		this.freeRooms = freeRooms;
	}

}
