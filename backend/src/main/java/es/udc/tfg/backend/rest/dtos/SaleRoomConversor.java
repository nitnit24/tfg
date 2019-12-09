package es.udc.tfg.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.tfg.backend.model.entities.SaleRoom;

public class SaleRoomConversor {

	private SaleRoomConversor() {
	}

	public final static SaleRoomDto toSaleRoomDto(SaleRoom saleRoom) {
		return new SaleRoomDto(saleRoom.getIdSaleRoom(), saleRoom.getDate(), saleRoom.getFreeRooms(), 
				saleRoom.getRoomType().getId());
	}

}