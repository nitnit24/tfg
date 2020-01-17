package es.udc.tfg.backend.rest.dtos;

import java.math.BigDecimal;


public class RoomTableTariffDto {

	private Long tariffId;
	private BigDecimal price;


	public RoomTableTariffDto(){
		
	}


	public RoomTableTariffDto(Long tariffId, BigDecimal price) {
		super();
		this.tariffId = tariffId;
		this.price = price;
	}


	public Long getTariffId() {
		return tariffId;
	}


	public void setTariffId(Long tariffId) {
		this.tariffId = tariffId;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	
	
}
