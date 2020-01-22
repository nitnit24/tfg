package es.udc.tfg.backend.model.entities;

import java.util.Calendar;
import java.util.List;


public class RoomTableDay {

	private Calendar day;
	private Integer freeRooms;
	private List<RoomTableTariff> roomTableTariffs;

	
	public RoomTableDay(){
		
	}


	public RoomTableDay(Calendar day, Integer freeRooms, List<RoomTableTariff> roomTableTariffs) {
		super();
		this.day = day;
		this.freeRooms = freeRooms;
		this.roomTableTariffs = roomTableTariffs;
	}


	public Calendar getDay() {
		return day;
	}


	public void setDay(Calendar day) {
		this.day = day;
	}


	public Integer getFreeRooms() {
		return freeRooms;
	}


	public void setFreeRooms(Integer freeRooms) {
		this.freeRooms = freeRooms;
	}


	public List<RoomTableTariff> getRoomTableTariffs() {
		return roomTableTariffs;
	}


	public void setRoomTabaleTariffs(List<RoomTableTariff> roomTableTariffs) {
		this.roomTableTariffs = roomTableTariffs;
	}


	
	
}
