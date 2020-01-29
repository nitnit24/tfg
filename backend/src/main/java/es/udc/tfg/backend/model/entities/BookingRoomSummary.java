package es.udc.tfg.backend.model.entities;

import java.util.List;


public class BookingRoomSummary {

	private Long hotelId;
	private List<SaleRoomTariff> saleRoomTariffs;
	private int quantity;
	
	
	public BookingRoomSummary(){
		
	}

	public BookingRoomSummary(Long hotelId, List<SaleRoomTariff> saleRoomTariffs, int quantity) {
		this.hotelId = hotelId;
		this.saleRoomTariffs = saleRoomTariffs;
		this.quantity = quantity;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
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
