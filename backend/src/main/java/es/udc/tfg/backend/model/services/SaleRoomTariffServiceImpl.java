package es.udc.tfg.backend.model.services;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.SaleRoom;
import es.udc.tfg.backend.model.entities.SaleRoomDao;
import es.udc.tfg.backend.model.entities.SaleRoomTariff;
import es.udc.tfg.backend.model.entities.SaleRoomTariffDao;
import es.udc.tfg.backend.model.entities.Tariff;
import es.udc.tfg.backend.model.entities.TariffDao;

@Service
@Transactional
public class SaleRoomTariffServiceImpl implements SaleRoomTariffService {

	@Autowired
	private SaleRoomDao saleRoomDao;
	
	@Autowired
	private SaleRoomTariffDao saleRoomTariffDao;
	
	@Autowired
	private TariffDao tariffDao;

	@Override
	public SaleRoomTariff uploadSaleRoomTariff(BigDecimal price, Long tariffId, Long roomTypeId, Calendar date) throws InstanceNotFoundException {
		
		Optional<Tariff> tariff = tariffDao.findById(tariffId);

		if (!tariff.isPresent()) {
			throw new InstanceNotFoundException("project.entities.tariff", tariffId);
		}
		
		Optional<SaleRoom> saleRoom = saleRoomDao.findByRoomTypeIdAndDate(roomTypeId, date);

		if (!saleRoom.isPresent()) {
			throw new InstanceNotFoundException("project.entities.saleRoom", saleRoom.get().getIdSaleRoom());
		}
		
		Optional<SaleRoomTariff> saleRoomTariff = saleRoomTariffDao.findByTariffIdAndSaleRoomIdSaleRoom(tariffId, roomTypeId);
		
		if(!saleRoomTariff.isPresent()) {
			SaleRoomTariff newSaleRoomTariff = new SaleRoomTariff(price, tariff.get(), saleRoom.get());
			
			saleRoomTariffDao.save(newSaleRoomTariff);
			
			return newSaleRoomTariff;
			
		}else {
			saleRoomTariff.get().setPrice(price);
			saleRoomTariffDao.save(saleRoomTariff.get());
			
			return saleRoomTariff.get();
		}
		
		
	}
	
	@Override
	public SaleRoomTariff findByTariffIdAnRoomTypeIdAndDate(Long tariffId, Long roomTypeId, Calendar date) throws InstanceNotFoundException{
		
		
		Optional<SaleRoomTariff> saleRoomTariff = saleRoomTariffDao. findByTariffIdAndSaleRoomRoomTypeIdAndSaleRoomDate(tariffId, roomTypeId, date);

		if (!saleRoomTariff.isPresent()) {
			throw new InstanceNotFoundException("project.entities.roomType", roomTypeId );
		}

		return saleRoomTariff.get();
	}
	


}