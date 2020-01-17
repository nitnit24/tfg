package es.udc.tfg.backend.model.services;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.RoomTable;
import es.udc.tfg.backend.model.entities.SaleRoomTariff;

public interface SaleRoomTariffService {

	SaleRoomTariff uploadSaleRoomTariff(BigDecimal price, Long tariffId, Long roomTypeId, Calendar date) throws InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException;

	SaleRoomTariff findByTariffIdAnRoomTypeIdAndDate(Long tariffId, Long roomTypeId, Calendar date) throws InstanceNotFoundException;

	List<RoomTable> findDailyPanel (Calendar initialDate);
}