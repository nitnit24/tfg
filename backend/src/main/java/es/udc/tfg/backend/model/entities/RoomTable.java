package es.udc.tfg.backend.model.entities;

import java.math.BigDecimal;
import java.util.List;


public class RoomTable {

	private Long roomTypeId;
	private String roomTypeName;
	private int quantity;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private List<RoomTableDay> roomTableDays;
	private List<Tariff> tariffs;

	
	public RoomTable(){
		
	}


	public RoomTable(Long roomTypeId, String roomTypeName, int quantity, BigDecimal minPrice, BigDecimal maxPrice,
		 List<Tariff> tariffs, List<RoomTableDay> roomTableDays) {
	
		this.roomTypeId = roomTypeId;
		this.roomTypeName = roomTypeName;
		this.quantity = quantity;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.roomTableDays = roomTableDays;
		this.tariffs = tariffs;
	}





	public Long getRoomTypeId() {
		return roomTypeId;
	}


	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}


	public String getRoomTypeName() {
		return roomTypeName;
	}


	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getMinPrice() {
		return minPrice;
	}


	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}


	public BigDecimal getMaxPrice() {
		return maxPrice;
	}


	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}


	public List<RoomTableDay> getRoomTableDays() {
		return roomTableDays;
	}


	public void setRoomTableDays(List<RoomTableDay> roomTableDays) {
		this.roomTableDays = roomTableDays;
	}


	public List<Tariff> getTariffs() {
		return tariffs;
	}


	public void setTariffs(List<Tariff> tariffs) {
		this.tariffs = tariffs;
	}



}
