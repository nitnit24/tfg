package es.udc.tfg.backend.model.services;

import static es.udc.tfg.backend.model.entities.SendEmail.sendMsgBooking;
import static es.udc.tfg.backend.model.entities.SendEmail.sendMsgFreeRoomsZero;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
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
import es.udc.tfg.backend.model.entities.FreeRoomType;
import es.udc.tfg.backend.model.entities.FreeRoomTypeTariffs;
import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.RoomTypeDao;
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
	private RoomTypeDao roomTypeDao;
	
	@Autowired
	private SaleRoomDao saleRoomDao;

	@Autowired
	private SaleRoomTariffDao saleRoomTariffDao;

	@Override
	public List<FreeRoomType> findFreeRooms(Calendar startDate, Calendar endDate, int people, int rooms) {

		// diferents
		Optional<List<SaleRoom>> freeSaleRooms = saleRoomDao.findByDate(startDate);

		List<RoomType> freeRoomTypes = new ArrayList<>();
		List<Integer> freeRooms = new ArrayList<>();
		
		if (freeSaleRooms.isPresent()) {
			for (SaleRoom freeSaleRoom : freeSaleRooms.get()) {

				Calendar date = Calendar.getInstance();
				date.setTime(startDate.getTime());

				while (date.compareTo(endDate) < 0) {
					Optional<SaleRoom> saleRoom = saleRoomDao
							.findByRoomTypeIdAndDate(freeSaleRoom.getRoomType().getId(), date);
					
					if (saleRoom.isPresent()) {
						if (saleRoom.get().getFreeRooms() < rooms) {
							break;
						} else {
							Optional<RoomType> rt = roomTypeDao.findById(saleRoom.get().getRoomType().getId());
							if (rt.get().getCapacity() != people){
								break;
							}else {
							date.add(Calendar.DAY_OF_YEAR, 1);
							}
						}

						if (date.compareTo(endDate) == 0) {
							freeRoomTypes.add(saleRoom.get().getRoomType());
							freeRooms.add(saleRoom.get().getFreeRooms());
						}
					}
					else {
						break;
					}
				}
			}
		}
		
		List<FreeRoomType> free = new ArrayList<>();
		
		for (RoomType freeRT : freeRoomTypes) {
			
			List <Tariff> availableTariffs = findTariffsByFreeRoom (startDate, endDate, freeRT.getId());
			
			List<FreeRoomTypeTariffs> freeRoomsTypeTariffs = new ArrayList<>();
			
			
			for (Tariff aTariff : availableTariffs) {

				List<SaleRoomTariff> saleRoomTariffs = findSaleRoomTariffsByFreeRoom(startDate, endDate, freeRT.getId(),
						aTariff.getId());

				BigDecimal totalPrice = BigDecimal.ZERO;

				totalPrice = saleRoomTariffs.stream().map(i -> i.getPrice()).reduce(new BigDecimal(0),
						(a, b) -> a.add(b));

				freeRoomsTypeTariffs.add(new FreeRoomTypeTariffs(aTariff.getId(), aTariff.getName(),
						aTariff.getDescription(), totalPrice, saleRoomTariffs));
			}
			
			int maxFreeRooms = 100000;
			for (int x = 0; x < freeRooms.size(); x++) {
				if (freeRooms.get(x) < maxFreeRooms) {
					maxFreeRooms = freeRooms.get(x);
				}
			}
			
			free.add(new FreeRoomType(freeRT.getId(), freeRT.getName(), freeRT.getDescription(), 
					freeRT.getImage(), freeRT.getCapacity(),maxFreeRooms, freeRoomsTypeTariffs));
		}
		
		return free;
	}
	
	private List<Tariff> findTariffsByFreeRoom(Calendar startDate, Calendar endDate, Long roomTypeId){
			
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

	
	private List<SaleRoomTariff> findSaleRoomTariffsByFreeRoom(Calendar startDate, Calendar endDate, Long roomTypeId,
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

	@Override
	@Transactional (rollbackFor= {ThereAreNotEnoughtFreeRoomsException.class, InstanceNotFoundException.class,
			UnsupportedEncodingException.class, IOException.class})
	public Booking makeBooking(List<BookingRoomSummary> bookingRoomSummarys, Calendar startDate, Calendar endDate,
			String name, String surName, String phone, String email, String petition)
			throws InstanceNotFoundException, ThereAreNotEnoughtFreeRoomsException, UnsupportedEncodingException, IOException{

			Calendar now = Calendar.getInstance();

			int startDay = startDate.get(Calendar.DAY_OF_YEAR);
			int endDay = endDate.get(Calendar.DAY_OF_YEAR);
			int duration = (endDay - startDay);
			State state = State.CONFIRMADA;

			Booking newBooking = bookingDao
					.save(new Booking(now, startDate, duration, endDate, state, name, surName, phone, email, petition));

			String locator = now.get(Calendar.YEAR) + "0" + now.get(Calendar.DAY_OF_WEEK) + "00"
					+ now.get(Calendar.DAY_OF_YEAR) + newBooking.getId().toString();
			newBooking.setLocator(locator);
			String key = createStringRandom();
			newBooking.setKey(key);
			bookingDao.save(newBooking);

			for (BookingRoomSummary bookingRoomSummary : bookingRoomSummarys) {

				List<SaleRoomTariff> SaleRTs = bookingRoomSummary.getSaleRoomTariffs();
				Optional<SaleRoomTariff> saleRT = saleRoomTariffDao.findById(SaleRTs.get(0).getId());

				BookingRoom newBookingRoom = new BookingRoom(bookingRoomSummary.getQuantity(),
						saleRT.get().getSaleRoom().getRoomType().getName(),
						saleRT.get().getSaleRoom().getRoomType().getCapacity(), saleRT.get().getTariff().getName());

				newBooking.addBookingRoom(newBookingRoom);
				bookingRoomDao.save(newBookingRoom);

				for (SaleRoomTariff saleRoomTariff : bookingRoomSummary.getSaleRoomTariffs()) {

					Optional<SaleRoomTariff> saleRoomT = saleRoomTariffDao.findById(saleRoomTariff.getId());
					Optional<SaleRoom> saleRoom = saleRoomDao.findById(saleRoomT.get().getSaleRoom().getIdSaleRoom());

					if (saleRoom.get().getFreeRooms() >= bookingRoomSummary.getQuantity()) {
						
						int oldFreeRooms = saleRoom.get().getFreeRooms();
						saleRoom.get().setFreeRooms(oldFreeRooms - bookingRoomSummary.getQuantity());
						saleRoomDao.save(saleRoom.get());
						
					} else {
						throw new ThereAreNotEnoughtFreeRoomsException();
					}

					BookingDay newBookingDay = new BookingDay(saleRoomTariff.getPrice(), saleRoom.get().getDate(),
							saleRoomTariff);

					newBookingRoom.addBookingDay(newBookingDay);
					bookingDayDao.save(newBookingDay);
					
					if (saleRoom.get().getFreeRooms() == 0) {
						sendMsgFreeRoomsZero(saleRoom.get().getRoomType(), saleRoom.get().getDate());
					}

				}

				BigDecimal roomTotalPrice = newBookingRoom.getBookingDays().stream().map(i -> i.getDayPrice())
						.reduce(new BigDecimal(0), (a, b) -> a.add(b));
				newBookingRoom.setRoomTotalPrice(roomTotalPrice);
				bookingRoomDao.save(newBookingRoom);

			}

			BigDecimal totalPrice = newBooking.getBookingRooms().stream()
					.map(i -> i.getRoomTotalPrice().multiply(new BigDecimal(i.getQuantity())))
					.reduce(new BigDecimal(0), (a, b) -> a.add(b));

			newBooking.setTotalPrice(totalPrice);
			bookingDao.save(newBooking);
			
			sendMsgBooking(newBooking);

			return newBooking;
	
	}
	
	@Override
	@Transactional (rollbackFor= {ThereAreNotEnoughtFreeRoomsException.class, InstanceNotFoundException.class,
			UnsupportedEncodingException.class, IOException.class})
	public Booking updateBooking(List<BookingRoomSummary> bookingRoomSummarys, Calendar startDate, Calendar endDate,
			String locator, String key, String phone, String email, String petition)
			throws InstanceNotFoundException, ThereAreNotEnoughtFreeRoomsException, UnsupportedEncodingException, IOException{

			Optional<Booking> booking = bookingDao.findByLocatorAndKey(locator, key);
			
			if (!booking.isPresent()) {
				throw new InstanceNotFoundException("project.entities.booking", locator);
			}
	
			for (BookingRoom bookingRoomsOld : booking.get().getBookingRooms()) {
				int quantity = bookingRoomsOld.getQuantity();
				for (BookingDay bookingDayOld : bookingRoomsOld.getBookingDays()) {
			
					int roomsOld = bookingDayOld.getSaleRoomTariff().getSaleRoom().getFreeRooms();
					bookingDayOld.getSaleRoomTariff().getSaleRoom().setFreeRooms(roomsOld + quantity);
					bookingDayDao.delete(bookingDayOld);
				}
				bookingRoomDao.delete(bookingRoomsOld);
			}
			
			booking.get().getBookingRooms().clear();
			
			Calendar now = Calendar.getInstance();

			int startDay = startDate.get(Calendar.DAY_OF_YEAR);
			int endDay = endDate.get(Calendar.DAY_OF_YEAR);
			int duration = (endDay - startDay);
			State state = State.MODIFICADA;
			
			booking.get().setPhone(phone);
			booking.get().setEmail(email);
			booking.get().setPetition(petition);
			booking.get().setStartDate(startDate);
			booking.get().setEndDate(endDate);
			booking.get().setDuration(duration);
			booking.get().setState(state);
			booking.get().setUpdateDate(now);
			
			bookingDao.save(booking.get());
			
			for (BookingRoomSummary bookingRoomSummary : bookingRoomSummarys) {

				List<SaleRoomTariff> SaleRTs = bookingRoomSummary.getSaleRoomTariffs();
				Optional<SaleRoomTariff> saleRT = saleRoomTariffDao.findById(SaleRTs.get(0).getId());

				BookingRoom newBookingRoom = new BookingRoom(bookingRoomSummary.getQuantity(),
						saleRT.get().getSaleRoom().getRoomType().getName(),
						saleRT.get().getSaleRoom().getRoomType().getCapacity(), saleRT.get().getTariff().getName());

				booking.get().addBookingRoom(newBookingRoom);
				bookingRoomDao.save(newBookingRoom);

				for (SaleRoomTariff saleRoomTariff : bookingRoomSummary.getSaleRoomTariffs()) {

					Optional<SaleRoomTariff> saleRoomT = saleRoomTariffDao.findById(saleRoomTariff.getId());
					Optional<SaleRoom> saleRoom = saleRoomDao.findById(saleRoomT.get().getSaleRoom().getIdSaleRoom());

					if (saleRoom.get().getFreeRooms() >= bookingRoomSummary.getQuantity()) {
						
						int oldFreeRooms = saleRoom.get().getFreeRooms();
						saleRoom.get().setFreeRooms(oldFreeRooms - bookingRoomSummary.getQuantity());
						saleRoomDao.save(saleRoom.get());
						
					} else {
						throw new ThereAreNotEnoughtFreeRoomsException();
					}

					BookingDay newBookingDay = new BookingDay(saleRoomTariff.getPrice(), saleRoom.get().getDate(),
							saleRoomTariff);

					newBookingRoom.addBookingDay(newBookingDay);
					bookingDayDao.save(newBookingDay);
					
					if (saleRoom.get().getFreeRooms() == 0) {
						sendMsgFreeRoomsZero(saleRoom.get().getRoomType(), saleRoom.get().getDate());
					}

				}

				BigDecimal roomTotalPrice = newBookingRoom.getBookingDays().stream().map(i -> i.getDayPrice())
						.reduce(new BigDecimal(0), (a, b) -> a.add(b));
				newBookingRoom.setRoomTotalPrice(roomTotalPrice);
				bookingRoomDao.save(newBookingRoom);

			}

			BigDecimal totalPrice = booking.get().getBookingRooms().stream()
					.map(i -> i.getRoomTotalPrice().multiply(new BigDecimal(i.getQuantity())))
					.reduce(new BigDecimal(0), (a, b) -> a.add(b));

			booking.get().setTotalPrice(totalPrice);
			bookingDao.save(booking.get());
			
			//sendMsgBooking(newBooking);

			return booking.get();
	
	}
	
	@Override
	public Booking findByLocator (String locator) throws InstanceNotFoundException {
		
		Optional<Booking> booking = bookingDao.findByLocator(locator);

		if (!booking.isPresent()) {
			throw new InstanceNotFoundException("project.entities.booking", locator );
		}

		return booking.get();
		
	}
	
	@Override
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
	
	@Override
	public Block<Booking> findBookings(String dataType, Calendar startDate, Calendar endDate, String keywords, int page, int size) {
		
		Slice<Booking> slice = bookingDao.find(dataType, startDate, endDate, keywords, page, size);
		
		return new Block<>(slice.getContent(), slice.hasNext());
		
	}
}