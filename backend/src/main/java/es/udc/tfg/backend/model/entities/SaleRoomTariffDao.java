package es.udc.tfg.backend.model.entities;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SaleRoomTariffDao extends PagingAndSortingRepository<SaleRoomTariff, Long> {

	Optional<SaleRoomTariff> findByTariffIdAndSaleRoomIdSaleRoom(Long tariffId, Long saleRoomId);
	
	Optional<SaleRoomTariff> findByTariffIdAndSaleRoomRoomTypeIdAndSaleRoomDate(Long tariffId,Long roomTypeId,Calendar date);

}