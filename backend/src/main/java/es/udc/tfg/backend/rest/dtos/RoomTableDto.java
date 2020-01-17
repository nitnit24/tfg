package es.udc.tfg.backend.rest.dtos;

import java.util.List;

public class RoomTableDto {
	
	private Long roomTypeId;
	private String roomTypeName;
	private List<RoomTableDayDto> roomTableDays;
	private List<TariffDto> tariffs;
	
	public RoomTableDto() {}
	

	public RoomTableDto(Long roomTypeId, String roomTypeName, List<RoomTableDayDto> roomTableDays,
			List<TariffDto> tariffs) {
		super();
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

	public List<RoomTableDayDto> getRoomTableDays() {
		return roomTableDays;
	}

	public void setRoomTableDays(List<RoomTableDayDto> roomTableDays) {
		this.roomTableDays = roomTableDays;
	}

	public List<TariffDto> getTariffs() {
		return tariffs;
	}

	public void setTariffs(List<TariffDto> tariffs) {
		this.tariffs = tariffs;
	}



}
