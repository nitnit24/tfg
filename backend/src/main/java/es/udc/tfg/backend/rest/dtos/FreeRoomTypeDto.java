package es.udc.tfg.backend.rest.dtos;

import java.util.List;

public class FreeRoomTypeDto {

	public interface AllValidations {
	}

	private Long roomTypeId;
	private String roomTypeName;
	private String roomTypeDescription;
	private String image;
	private int capacity;
	private int maxFreeRooms;
	private List<FreeRoomTypeTariffsDto> freeRoomTypeTariffs;

	public FreeRoomTypeDto() {
	}

	public FreeRoomTypeDto(Long roomTypeId, String roomTypeName, String roomTypeDescription, 
			String image, int capacity, int maxFreeRooms, List<FreeRoomTypeTariffsDto> freeRoomTypeTariffs) {
		this.roomTypeId = roomTypeId;
		this.roomTypeName = roomTypeName;
		this.roomTypeDescription = roomTypeDescription;
		this.image = image;
		this.capacity = capacity;
		this.maxFreeRooms = maxFreeRooms;
		this.freeRoomTypeTariffs = freeRoomTypeTariffs;
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

	public String getRoomTypeDescription() {
		return roomTypeDescription;
	}

	public void setRoomTypeDescription(String roomTypeDescription) {
		this.roomTypeDescription = roomTypeDescription;
	}
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getMaxFreeRooms() {
		return maxFreeRooms;
	}

	public void setMaxFreeRooms(int maxFreeRooms) {
		this.maxFreeRooms = maxFreeRooms;
	}

	public List<FreeRoomTypeTariffsDto> getFreeRoomTypeTariffs() {
		return freeRoomTypeTariffs;
	}

	public void setFreeRoomTypeTariffs(List<FreeRoomTypeTariffsDto> freeRoomTypeTariffs) {
		this.freeRoomTypeTariffs = freeRoomTypeTariffs;
	}


}