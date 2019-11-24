package es.udc.tfg.backend.rest.dtos;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddToSaleRoomTariffParamsDto {
	
	private BigDecimal price;
	private Long tariffId;
	private Long roomTypeId;
	private Calendar date;
	
	public AddToSaleRoomTariffParamsDto() {}

	@NotNull
	@Min(value=0)
	public BigDecimal getPrice() {
		return price;
	}

	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
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
