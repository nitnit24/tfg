package es.udc.tfg.backend.model.services;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.SaleRoom;

public interface SaleRoomService {

	SaleRoom addSaleRoom(Long roomTypeId, Calendar date, int freeRooms) throws DuplicateInstanceException, InstanceNotFoundException;

	SaleRoom findByRoomTypeIdAndDate(Long idRoomType, Calendar date) throws InstanceNotFoundException;

//	SaleRoom updateSaleRoom(SaleRoom saleRoom) throws InstanceNotFoundException;
//
	List<Calendar> findDate();
}