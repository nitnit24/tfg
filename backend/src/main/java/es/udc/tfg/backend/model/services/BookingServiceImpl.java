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

import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.Booking;
import es.udc.tfg.backend.model.entities.BookingDao;
import es.udc.tfg.backend.model.entities.BookingDay;
import es.udc.tfg.backend.model.entities.BookingDayDao;
import es.udc.tfg.backend.model.entities.BookingRoom;
import es.udc.tfg.backend.model.entities.BookingRoomDao;
import es.udc.tfg.backend.model.entities.BookingRoomSummary;
import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.SaleRoom;
import es.udc.tfg.backend.model.entities.SaleRoomDao;
import es.udc.tfg.backend.model.entities.SaleRoomTariff;
import es.udc.tfg.backend.model.entities.SaleRoomTariffDao;
import es.udc.tfg.backend.model.entities.State;
import es.udc.tfg.backend.model.entities.Tariff;
import es.udc.tfg.backend.model.entities.TariffDao;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private BookingDayDao bookingDayDao;
	
	@Autowired
	private BookingRoomDao bookingRoomDao;
	
	@Autowired
	private TariffDao tariffDao;
	
	
	@Autowired
	private SaleRoomDao saleRoomDao;

	@Autowired
	private SaleRoomTariffDao saleRoomTariffDao;

	
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
	
	private String createStringRandom(){
		char[] elementos={'0','1','2','3','4','5','6','7','8','9' ,'a',
				'b','c','d','e','f','g','h','i','j','k','l','m','n','ñ',
				'o','p','q','r','s','t','u','v','w','x','y','z',
				'A','B','C','D','E','F','G','H','I','J','K','L','M',
				'N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

		char[] conjunto = new char[7];

		for(int i=0;i<7;i++){
			int el = (int)(Math.random()*64);
			conjunto[i] = (char)elementos[el];
		}
		String sRandom = new String(conjunto);
		
		return sRandom;
	}

			
	public Booking makeBooking(List<BookingRoomSummary> bookingRoomSummarys, Calendar startDate, Calendar endDate, String name, 
			String surName, String phone, String email, String petition) throws InstanceNotFoundException {

		Calendar now = Calendar.getInstance();
		
		int startDay = startDate.get(Calendar.DAY_OF_YEAR);
		int endDay = endDate.get(Calendar.DAY_OF_YEAR);
		int duration = (endDay - startDay);
		State state = State.CONFIRMADA;
		
		Booking newBooking = bookingDao.save(new Booking(now, startDate, duration, endDate, state, name, surName,
				phone, email, petition));
		
		String locator = now.get(Calendar.YEAR) +"0"+ now.get(Calendar.MINUTE) + "00"+ now.get(Calendar.DAY_OF_YEAR)+ newBooking.getId().toString() ;
		newBooking.setLocator(locator);
		String key = createStringRandom();
		newBooking.setKey(key);
		bookingDao.save(newBooking);
		
		
		for(BookingRoomSummary bookingRoomSummary : bookingRoomSummarys) {
			
			List<SaleRoomTariff> SaleRTs = bookingRoomSummary.getSaleRoomTariffs();
			Optional<SaleRoomTariff> saleRT = saleRoomTariffDao.findById(SaleRTs.get(0).getId());
			
			BookingRoom newBookingRoom = new BookingRoom(bookingRoomSummary.getQuantity(), saleRT.get().getSaleRoom().getRoomType().getName(),
					saleRT.get().getSaleRoom().getRoomType().getCapacity(), 
					saleRT.get().getTariff().getName());
			
			newBooking.addBookingRoom(newBookingRoom);
			bookingRoomDao.save(newBookingRoom);
		
			for(SaleRoomTariff saleRoomTariff : bookingRoomSummary.getSaleRoomTariffs()) {
				
				Optional<SaleRoomTariff> saleRoomT = saleRoomTariffDao.findById(saleRoomTariff.getId());
				Optional<SaleRoom> saleRoom = saleRoomDao.findById(saleRoomT.get().getSaleRoom().getIdSaleRoom());
				int oldFreeRooms = saleRoom.get().getFreeRooms();
				saleRoom.get().setFreeRooms(oldFreeRooms - bookingRoomSummary.getQuantity());
				saleRoomDao.save(saleRoom.get());
				
				BookingDay newBookingDay = new BookingDay(saleRoomTariff.getPrice(), saleRoom.get().getDate(), saleRoomTariff);
				
				newBookingRoom.addBookingDay(newBookingDay);
				bookingDayDao.save(newBookingDay);
			}
		}
		
		return newBooking;
	}
	
	public Booking findByLocator (String locator) throws InstanceNotFoundException {
		
		Optional<Booking> booking = bookingDao.findByLocator(locator);

		if (!booking.isPresent()) {
			throw new InstanceNotFoundException("project.entities.booking", locator );
		}

		return booking.get();
		
	}
	
	public Booking findByLocatorAndKey(String locator, String key) throws IncorrectFindLocatorKeyException {
		
		Optional<Booking> booking = bookingDao.findByLocatorAndKey(locator, key);

		if (!booking.isPresent()) {
			throw new IncorrectFindLocatorKeyException( locator, key );
		}

		return booking.get();
	}
	
	
	public Booking cancel(String locator, String key) throws InstanceNotFoundException {

		Optional<Booking> existingBookingItem = bookingDao.findByLocatorAndKey(locator, key);

		if (!existingBookingItem.isPresent()) {
			throw new InstanceNotFoundException("project.entities.booking", locator);
		}

		existingBookingItem.get().setState(State.CANCELADA);
		
		Calendar now = Calendar.getInstance();
		existingBookingItem.get().setCancelDate(now);

		bookingDao.save(existingBookingItem.get());

		return existingBookingItem.get();
	}
}