package es.udc.tfg.backend.rest.dtos;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

public class FindToSaleRoomParamsDto {
	
	private Long roomTypeId;
	private Calendar date;
	
	public FindToSaleRoomParamsDto() {}

	@NotNull
	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	@NotNull
	public Calendar getDate() {
		this.date.set(Calendar.MILLISECOND, 0);
		this.date.set(Calendar.SECOND, 0);
		this.date.set(Calendar.MINUTE, 0);
		this.date.set(Calendar.HOUR_OF_DAY, 0);
		return date;
	}

	
	public void setDate(Calendar date) {
		this.date = date;
	}

}
