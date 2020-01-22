package es.udc.tfg.backend.rest.dtos;

import java.util.List;

public class RoomTableDayDto {

	private Long day;
	private Integer freeRooms;
	private List<RoomTableTariffDto> roomTableTariffs;
	
	
	public RoomTableDayDto() {
	
	}

	public RoomTableDayDto(Long day, Integer freeRooms, List<RoomTableTariffDto> roomTableTariffs) {
		this.day = day;
		this.freeRooms = freeRooms;
		this.roomTableTariffs = roomTableTariffs;
	}
	
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}
	public Integer getFreeRooms() {
		return freeRooms;
	}
	public void setFreeRooms(Integer freeRooms) {
		this.freeRooms = freeRooms;
	}
	public List<RoomTableTariffDto> getRoomTableTariffs() {
		return roomTableTariffs;
	}
	public void setRoomTableTariffs(List<RoomTableTariffDto> roomTableTariffs) {
		this.roomTableTariffs = roomTableTariffs;
	}
	
	
}
