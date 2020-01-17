package es.udc.tfg.backend.model.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.RoomTable;
import es.udc.tfg.backend.model.entities.RoomTableDay;
import es.udc.tfg.backend.model.entities.RoomTableTariff;
import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.RoomTypeDao;
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

	@Autowired
	private RoomTypeDao roomTypeDao;

	@Override
	public SaleRoomTariff uploadSaleRoomTariff(BigDecimal price, Long tariffId, Long roomTypeId, Calendar date)
			throws InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException {

		Optional<Tariff> tariff = tariffDao.findById(tariffId);

		if (!tariff.isPresent()) {
			throw new InstanceNotFoundException("project.entities.tariff", tariffId);
		}

		Optional<RoomType> roomType = roomTypeDao.findById(roomTypeId);
		
		
		if ((price.compareTo(roomType.get().getMinPrice()) >= 0)
				&& (price.compareTo(roomType.get().getMaxPrice()) <= 0 )){
			Optional<SaleRoom> saleRoom = saleRoomDao.findByRoomTypeIdAndDate(roomTypeId, date);

			if (!saleRoom.isPresent()) {
//			Optional<RoomType> roomType = roomTypeDao.findById(roomTypeId);
				SaleRoom newSaleRoom = new SaleRoom(date, 0, roomType.get());
				saleRoomDao.save(newSaleRoom);

				SaleRoomTariff newSaleRoomTariff = new SaleRoomTariff(price, tariff.get(), newSaleRoom);

				return newSaleRoomTariff;
			} else {

				Optional<SaleRoomTariff> saleRoomTariff = saleRoomTariffDao
						.findByTariffIdAndSaleRoomIdSaleRoom(tariffId, saleRoom.get().getIdSaleRoom());

				if (!saleRoomTariff.isPresent()) {

					SaleRoomTariff newSaleRoomTariff = new SaleRoomTariff(price, tariff.get(), saleRoom.get());
					saleRoomTariffDao.save(newSaleRoomTariff);

					return newSaleRoomTariff;

				} else {
					saleRoomTariff.get().setPrice(price);
					saleRoomTariffDao.save(saleRoomTariff.get());

					return saleRoomTariff.get();
				}
			}
		} else {
			throw new PriceNotBetweenMinAndMaxValueException(date, roomType.get().getMinPrice(),
					roomType.get().getMaxPrice());
		}
	}
	
	@Override
	public SaleRoomTariff findByTariffIdAndRoomTypeIdAndDate(Long tariffId, Long roomTypeId, Calendar date) throws InstanceNotFoundException{
		
		Optional<SaleRoomTariff> saleRoomTariff = saleRoomTariffDao. findByTariffIdAndSaleRoomRoomTypeIdAndSaleRoomDate(tariffId, roomTypeId, date);

		if (!saleRoomTariff.isPresent()) {
			throw new InstanceNotFoundException("project.entities.roomType", roomTypeId );
		}

		return saleRoomTariff.get();
	}
	
	@Override
	public List<RoomTable> findDailyPanel (Calendar initialDate){
		
		Iterable<RoomType> roomTypes = roomTypeDao.findAll();
		
		Iterable<Tariff> tariffs = tariffDao.findAll();
		
		List<RoomTable> roomTables = new ArrayList<>();
		
		for (RoomType roomType:roomTypes) {
			
			List<RoomTableDay> roomTableDays = new ArrayList<>();
			
			for (int i = 0 ; i < 31; i++) {
				Calendar date = Calendar.getInstance();
				Long millis = initialDate.getTimeInMillis();
				date.setTimeInMillis(millis);
				date.add(Calendar.DATE, i);
				
				Optional<SaleRoom> saleRoom = saleRoomDao.findByRoomTypeIdAndDate(roomType.getId(), date);
				
				List<RoomTableTariff> roomTableTariffs = new ArrayList<>();
					
				
				for(Tariff tariff:tariffs) {
					Optional<SaleRoomTariff> saleRoomTariff= saleRoomTariffDao.findByTariffIdAndSaleRoomRoomTypeIdAndSaleRoomDate(tariff.getId(), roomType.getId(), date);
					
					if (saleRoomTariff.isPresent()) {
						roomTableTariffs.add(new RoomTableTariff(tariff.getId(), saleRoomTariff.get().getPrice()));
					}
					else {
						roomTableTariffs.add(new RoomTableTariff(tariff.getId(), null));
					}
				}
				
				if(saleRoom.isPresent()) {
					roomTableDays.add(new RoomTableDay(date, saleRoom.get().getFreeRooms(),roomTableTariffs));

				}
				else {
					roomTableDays.add(new RoomTableDay(date, null , roomTableTariffs));
				}
			}
			 
			List<Tariff> tariffsList = new ArrayList<>();
			tariffs.iterator().forEachRemaining(tariffsList::add);
	  
	        
			roomTables.add(new RoomTable(roomType.getId(), roomType.getName(),tariffsList, roomTableDays));
		}
		
		return roomTables;
	
	}

}