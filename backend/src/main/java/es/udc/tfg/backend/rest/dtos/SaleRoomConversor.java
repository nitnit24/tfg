package es.udc.tfg.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.SaleRoom;

public class SaleRoomConversor {

	private SaleRoomConversor() {
	}

	public final static SaleRoomDto toSaleRoomDto(SaleRoom saleRoom) {
		return new SaleRoomDto(saleRoom.getIdSaleRoom(), saleRoom.getDate(), saleRoom.getFreeRooms(), 
				saleRoom.getRoomType());
	}

	public final static SaleRoom toSaleRoom(SaleRoomDto saleRoomDto) {
		return new SaleRoom(saleRoomDto.getIdSaleRoom(), saleRoomDto.getDate(), saleRoomDto.getFreeRooms(),
				saleRoomDto.getRoomType());
	}

	public final static List<SaleRoomDto> toSaleRoomDtos(List<SaleRoom> saleRooms) {
		return saleRooms.stream().map(c -> toSaleRoomDto(c)).collect(Collectors.toList());
	}

}