package es.udc.tfg.backend.rest.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddToSaleRoomParamsDto {
	
	private Long idRoomType;
	private Long date;
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
	public Long getDate() {
//		this.date.set(Calendar.MILLISECOND, 0);
//		this.date.set(Calendar.SECOND, 0);
//		this.date.set(Calendar.MINUTE, 0);
//		this.date.set(Calendar.HOUR_OF_DAY, 0);
		return date;
	}

	
	public void setDate(Long date) {
		this.date = date;
	}

	@NotNull
	@Min(value=0)
	public int getFreeRooms() {
		return freeRooms;
	}

	
	public void setFreeRooms(int freeRooms) {
		this.freeRooms = freeRooms;
	}

}
