package es.udc.tfg.backend.rest.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SaleRoomTariffDto {

	public interface AllValidations {
	}

	private Long id;
	private BigDecimal price;
	private Long tariffId;
	private Long roomTypeId;
	
	public SaleRoomTariffDto() {}	
	
	public SaleRoomTariffDto(Long id, BigDecimal price, Long tariffId, Long roomTypeId) {
		this.id = id;
		this.price = price;
		this.tariffId = tariffId;
		this.roomTypeId = roomTypeId;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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


	

}