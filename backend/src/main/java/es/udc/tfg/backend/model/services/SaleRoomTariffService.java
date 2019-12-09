package es.udc.tfg.backend.model.services;

import java.math.BigDecimal;
import java.util.Calendar;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.SaleRoomTariff;

public interface SaleRoomTariffService {

	SaleRoomTariff uploadSaleRoomTariff(BigDecimal price, Long tariffId, Long roomTypeId, Calendar date) throws InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException;

	SaleRoomTariff findByTariffIdAnRoomTypeIdAndDate(Long tariffId, Long roomTypeId, Calendar date) throws InstanceNotFoundException;
}