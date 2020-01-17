package es.udc.tfg.backend.rest.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddToSaleRoomTariffParamsDto {
	
	private BigDecimal price;
	private Long tariffId;
	private Long roomTypeId;
	private Long date;
	
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
	public Long getDate() {
		return date;
	}

	
	public void setDate(Long date) {
		this.date = date;
	}


}
