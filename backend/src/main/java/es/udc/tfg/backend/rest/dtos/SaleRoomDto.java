package es.udc.tfg.backend.rest.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SaleRoomDto {

	public interface AllValidations {
	}

	private Long saleRoomId;
	private Long date;
	private int freeRooms;
	private Long roomTypeId;

	public SaleRoomDto() {
	}

	
	public SaleRoomDto(Long saleRoomId, Long date, int freeRooms, Long roomTypeId) {
		this.saleRoomId = saleRoomId;
		this.date = date;
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
	public Long getDate() {
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

	@NotNull
	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	
	
	

}