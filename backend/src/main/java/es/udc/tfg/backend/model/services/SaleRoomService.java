package es.udc.tfg.backend.model.services;

import java.util.Calendar;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.SaleRoom;

public interface SaleRoomService {

	SaleRoom addSaleRoom(Long roomTypeId, Calendar date, int freeRooms) throws DuplicateInstanceException, InstanceNotFoundException;

	SaleRoom findByRoomTypeIdAndDate(Long idRoomType, Calendar date) throws InstanceNotFoundException;

}