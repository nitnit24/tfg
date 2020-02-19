package es.udc.tfg.backend.model.services;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.RoomTable;
import es.udc.tfg.backend.model.entities.SaleRoom;
import es.udc.tfg.backend.model.entities.SaleRoomTariff;

public interface DailyPanelService {

	SaleRoomTariff uploadSaleRoomTariff(BigDecimal price, Long tariffId, Long roomTypeId, Calendar date) throws InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException;

	SaleRoom addSaleRoom(Long roomTypeId, Calendar date, int freeRooms) throws DuplicateInstanceException, InstanceNotFoundException, FreeRoomsLessThanRoomTypeQuantityException;
	
	List<RoomTable> findDailyPanel (Calendar initialDate);
	
}