package es.udc.tfg.backend.model.entities;

import java.util.List;


public class RoomTable {

	private Long roomTypeId;
	private String roomTypeName;
	private List<RoomTableDay> roomTableDays;
	private List<Tariff> tariffs;

	
	public RoomTable(){
		
	}


	public RoomTable(Long roomTypeId, String roomTypeName, List<Tariff> tariffs, List<RoomTableDay> roomTableDays) {
		this.roomTypeId = roomTypeId;
		this.roomTypeName = roomTypeName;
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
