package es.udc.tfg.backend.rest.dtos;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

public class FindToSaleRoomTariffParamsDto {
	
	private Long tariffId;
	private Long roomTypeId;
	private Calendar date;
	
	public FindToSaleRoomTariffParamsDto() {}
	
	@NotNull
	public Long getTariffId() {
		return tariffId;
	}

	public void setTariffId(Long tariffId) {
		this.tariffId = tariffId;
	}
	
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
