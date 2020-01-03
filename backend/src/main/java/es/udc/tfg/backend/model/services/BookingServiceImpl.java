package es.udc.tfg.backend.model.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.SaleRoom;
import es.udc.tfg.backend.model.entities.SaleRoomDao;
import es.udc.tfg.backend.model.entities.SaleRoomTariff;
import es.udc.tfg.backend.model.entities.SaleRoomTariffDao;
import es.udc.tfg.backend.model.entities.Tariff;
import es.udc.tfg.backend.model.entities.TariffDao;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

//	@Autowired
//	private BookingDao bookingDao;
//	
//	@Autowired
//	private BookingDayDao bookingDayDao;
//	
//	@Autowired
//	private BookingRoomDao bookingRoomDao;
	
	@Autowired
	private TariffDao tariffDao;
	
	
	@Autowired
	private SaleRoomDao saleRoomDao;

	@Autowired
	private SaleRoomTariffDao saleRoomTariffDao;

	
//	@Override
//	public Booking addBooking(List<BookingRoom> bookingRooms, Calendar startDate, Calendar endDate, String name,
//			String surName, String phone, String email, String petition) throws InstanceNotFoundException {
//
//		for (BookingRoom bookingRoom : bookingRooms) {
//			List<BookingDay> bookingDays = bookingRoom.getBookingDays();
//			
//			for (BookingDay bookingDay: bookingDays) {
//				Long saleRoomTariffId = bookingDay.getSaleRoomTariff().getId();
//				
//				Optional<SaleRoomTariff> saleRoomTariff = saleRoomTariffDao.findById(saleRoomTariffId);
//
//				if (!saleRoomTariff.isPresent()) {
//					throw new InstanceNotFoundException("project.entities.saleRoomTariff", saleRoomTariffId);
//				}
//				else {
//					BookingDay newBookingDay = new BookingDay(saleRoomTariff.get().getPrice(), saleRoomTariff.get());
//					bookingDayDao.save(newBookingDay);
//				}
//			}
//			BookingRoom newBookingRoom= new BookingRoom(bookingDays);
//			bookingRoomDao.save(newBookingRoom);	
//		}
//
//		Calendar now = Calendar.getInstance();
//		
//		Booking newBooking = new Booking(bookingRooms, now, startDate, endDate, name, surName,
//				phone, email, petition);
//		
//		return newBooking;
//
//	}
	
	public List<RoomType> findFreeRooms(Calendar startDate, Calendar endDate, int people, int rooms) {

		// diferents
		Optional<List<SaleRoom>> freeSaleRooms = saleRoomDao.findByDate(startDate);

		List<RoomType> freeRoomTypes = new ArrayList<>();


		for (SaleRoom freeSaleRoom : freeSaleRooms.get()) {
			
			Calendar date = Calendar.getInstance();
			date.setTime(startDate.getTime());

				while (date.compareTo(endDate) < 0) {
					Optional<SaleRoom> saleRoom = saleRoomDao
							.findByRoomTypeIdAndDate(freeSaleRoom.getRoomType().getId(), date);

					if (saleRoom.get().getFreeRooms() < rooms) {
						break;
					}
					else {
						date.add(Calendar.DAY_OF_YEAR, 1);
					}
					
					if (date.compareTo(endDate) == 0) {
						freeRoomTypes.add(saleRoom.get().getRoomType());
					}
			
			}
		}

		return freeRoomTypes;
	}
	
	public List<Tariff> findTariffsByFreeRoom(Calendar startDate, Calendar endDate, Long roomTypeId){
			
		Iterable<Tariff> tariffs = tariffDao.findAll(new Sort(Sort.Direction.ASC, "id"));
		List<Tariff> tariffsAsList = new ArrayList<>();

		tariffs.forEach(c -> tariffsAsList.add(c));
		
		List<Tariff> availableTariffs = new ArrayList<>();
		
		
		for (Tariff tariff : tariffs) {
			
			Calendar date = Calendar.getInstance();
			date.setTime(startDate.getTime());
			
				while (date.compareTo(endDate) < 0) {
					
					Optional <SaleRoomTariff> saleRoomTariff = 
							saleRoomTariffDao.findByTariffIdAndSaleRoomRoomTypeIdAndSaleRoomDate(tariff.getId(), roomTypeId, date);
					
			
					if (!saleRoomTariff.isPresent() || saleRoomTariff.get().getPrice().compareTo(BigDecimal.ZERO) == 0) {
						break;
					}
					
					else {
						date.add(Calendar.DAY_OF_YEAR, 1);
					}
					
					if (date.compareTo(endDate) == 0) {
						availableTariffs.add(tariff);
					}
			}
		}

		return availableTariffs;
		
	}

	
	public List<SaleRoomTariff> findSaleRoomTariffsByFreeRoom(Calendar startDate, Calendar endDate, Long roomTypeId,
			Long tariffId) {

		List<SaleRoomTariff> saleRoomTariffList = new ArrayList<>();
		List<SaleRoomTariff> possibleSaleRoomTariffList = new ArrayList<>();
		
		Calendar date = Calendar.getInstance();
		date.setTime(startDate.getTime());

		while (date.compareTo(endDate) < 0) {

			Optional<SaleRoomTariff> saleRoomTariff = saleRoomTariffDao
					.findByTariffIdAndSaleRoomRoomTypeIdAndSaleRoomDate(tariffId, roomTypeId, date);

			if (!saleRoomTariff.isPresent() || saleRoomTariff.get().getPrice().compareTo(BigDecimal.ZERO) == 0) {
				break;
			}

			else {
				date.add(Calendar.DAY_OF_YEAR, 1);
				possibleSaleRoomTariffList.add(saleRoomTariff.get());
			}

			if (date.compareTo(endDate) == 0) {
				saleRoomTariffList = possibleSaleRoomTariffList;
			}

		}

		return saleRoomTariffList;

	}
}