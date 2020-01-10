package es.udc.tfg.backend.model.entities;

import java.util.List;


public class BookingRoomSummary {

	private List<SaleRoomTariff> saleRoomTariffs;
	private int quantity;
	
	public BookingRoomSummary(){
		
	}

	public BookingRoomSummary(List<SaleRoomTariff> saleRoomTariffs, int quantity) {
		this.saleRoomTariffs = saleRoomTariffs;
		this.quantity = quantity;
	}



	public List<SaleRoomTariff> getSaleRoomTariffs() {
		return saleRoomTariffs;
	}

	public void setSaleRoomTariffs(List<SaleRoomTariff> saleRoomTariffs) {
		this.saleRoomTariffs = saleRoomTariffs;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
