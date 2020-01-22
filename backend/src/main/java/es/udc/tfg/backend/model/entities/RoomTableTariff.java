package es.udc.tfg.backend.model.entities;

import java.math.BigDecimal;


public class RoomTableTariff {

	private Long tariffId;
	private BigDecimal price;


	public RoomTableTariff(){
		
	}


	public RoomTableTariff(Long tariffId, BigDecimal price) {
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
