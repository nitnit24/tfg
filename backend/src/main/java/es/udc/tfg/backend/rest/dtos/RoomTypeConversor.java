package es.udc.tfg.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.tfg.backend.model.entities.RoomType;

public class RoomTypeConversor {

	private RoomTypeConversor() {
	}

	public final static RoomTypeDto toRoomTypeDto(RoomType roomType) {
		//String img = Base64.getEncoder().encodeToString(roomType.getImage());
		return new RoomTypeDto(roomType.getId(), roomType.getImage(), roomType.getName(), roomType.getDescription(), roomType.getCapacity(), 
				roomType.getQuantity(), roomType.getMinPrice(), roomType.getMaxPrice());
	}

	public final static RoomType toRoomType(RoomTypeDto roomTypeDto) {
		
		//byte[] img = roomTypeDto.getImage().getBytes(StandardCharsets.UTF_8);
		
		return new RoomType(roomTypeDto.getId(),roomTypeDto.getImage(), roomTypeDto.getName(), roomTypeDto.getDescription(), roomTypeDto.getCapacity(),
				 roomTypeDto.getQuantity(), roomTypeDto.getMinPrice(), roomTypeDto.getMaxPrice());
	}

	public final static List<RoomTypeDto> toRoomTypeDtos(List<RoomType> roomTypes) {
		return roomTypes.stream().map(c -> toRoomTypeDto(c)).collect(Collectors.toList());
	}

}