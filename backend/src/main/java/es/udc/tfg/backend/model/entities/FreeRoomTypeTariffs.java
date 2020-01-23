package es.udc.tfg.backend.model.entities;

import java.math.BigDecimal;
import java.util.List;


public class FreeRoomTypeTariffs {

	private Long tariffId;
	private String tariffName;
	private String tariffDescription;
	private BigDecimal totalPrice;
	private List<SaleRoomTariff> saleRoomTariffs;

	
	public FreeRoomTypeTariffs(){
		
	}


	public FreeRoomTypeTariffs(Long tariffId, String tariffName, String tariffDescription, BigDecimal totalPrice,
			List<SaleRoomTariff> saleRoomTariffs) {
		this.tariffId = tariffId;
		this.tariffName = tariffName;
		this.tariffDescription = tariffDescription;
		this.totalPrice = totalPrice;
		this.saleRoomTariffs = saleRoomTariffs;
	}


	public Long getTariffId() {
		return tariffId;
	}


	public void setTariffId(Long tariffId) {
		this.tariffId = tariffId;
	}


	public String getTariffName() {
		return tariffName;
	}


	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}


	public String getTariffDescription() {
		return tariffDescription;
	}


	public void setTariffDescription(String tariffDescription) {
		this.tariffDescription = tariffDescription;
	}


	public BigDecimal getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}


	public List<SaleRoomTariff> getSaleRoomTariffs() {
		return saleRoomTariffs;
	}


	public void setSaleRoomTariffs(List<SaleRoomTariff> saleRoomTariffs) {
		this.saleRoomTariffs = saleRoomTariffs;
	}


	
}
