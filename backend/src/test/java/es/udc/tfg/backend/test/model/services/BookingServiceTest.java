package es.udc.tfg.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
//import es.udc.tfg.backend.model.entities.Booking;
//import es.udc.tfg.backend.model.entities.BookingDao;
//import es.udc.tfg.backend.model.entities.BookingDay;
//import es.udc.tfg.backend.model.entities.BookingRoom;
import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.SaleRoom;
import es.udc.tfg.backend.model.entities.SaleRoomTariff;
import es.udc.tfg.backend.model.entities.SaleRoomTariffDao;
import es.udc.tfg.backend.model.entities.Tariff;
import es.udc.tfg.backend.model.services.BookingService;
import es.udc.tfg.backend.model.services.PriceNotBetweenMinAndMaxValueException;
import es.udc.tfg.backend.model.services.RoomTypeService;
import es.udc.tfg.backend.model.services.SaleRoomService;
import es.udc.tfg.backend.model.services.SaleRoomTariffService;
import es.udc.tfg.backend.model.services.TariffService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BookingServiceTest {


	@Autowired
	private TariffService tariffService;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private SaleRoomService saleRoomService;
	
	@Autowired
	private SaleRoomTariffService saleRoomTariffService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private SaleRoomTariffDao saleRoomTariffDao;
	
//	@Autowired
//	private BookingDao bookingDao;

	private Tariff createTariff(String name, String code) throws DuplicateInstanceException {
		return new Tariff(name, code);
	}
	
	private RoomType createRoomType(String name, int capacity, BigDecimal minPrice, BigDecimal maxPrice) 
			throws DuplicateInstanceException {
		return new RoomType(name, capacity, minPrice, maxPrice);
	}
	
	
	
	@Test
	public void testFindFreeRooms() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException {

		Tariff newTariff = createTariff("name", "CODE");
		Tariff tariff = tariffService.addTariff(newTariff);

		RoomType roomType = createRoomType("name", 2, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(roomType);

		int freeRooms = 4;
		BigDecimal price = new BigDecimal(90);
		
		List<RoomType> freeRoomTypes = new ArrayList<>();
		List<RoomType> freeRoomTypes2 = new ArrayList<>();
		
		//Day1
		Calendar date = Calendar.getInstance();
		SaleRoom saleRoom = saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
//		SaleRoomTariff saleRoomTariff = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
//				roomType.getId(), date);
//
//		
//		//Day2
		Calendar date2 = Calendar.getInstance();
		date2.add(Calendar.DAY_OF_YEAR, 1);
		SaleRoom saleRoom2 = saleRoomService.addSaleRoom(roomType.getId(), date2, freeRooms);
//		SaleRoomTariff saleRoomTariff2 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
//				roomType.getId(), date);
//		
//		//Day3
		Calendar date3 = Calendar.getInstance();
		date3.add(Calendar.DAY_OF_YEAR, 2);
		SaleRoom saleRoom3 = saleRoomService.addSaleRoom(roomType.getId(), date3, freeRooms);
//		SaleRoomTariff saleRoomTariff3 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
//				roomType.getId(), date);
//		
		//Day4 
		Calendar date4 = Calendar.getInstance();
		date4.add(Calendar.DAY_OF_YEAR, 3);
		SaleRoom saleRoom4 = saleRoomService.addSaleRoom(roomType.getId(), date4, freeRooms);
		
		freeRoomTypes.add(roomType);
		freeRoomTypes2.add(roomType);
		
		//Find
		Calendar startDate = Calendar.getInstance();
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		startDate.set(Calendar.SECOND, 0);
		
		Calendar endDate = Calendar.getInstance();
		endDate.set(Calendar.HOUR_OF_DAY, 0);
		endDate.set(Calendar.MINUTE, 0);
		endDate.set(Calendar.MILLISECOND, 0);
		endDate.set(Calendar.SECOND, 0);
		endDate.add(Calendar.DAY_OF_YEAR, 2);
		
		int rooms = 2;
		int people = 2;
		
		
		// all
		List<RoomType> foundFreeRoomTypes = bookingService.findFreeRooms(startDate, endDate, people, rooms);
				
		assertEquals(freeRoomTypes, foundFreeRoomTypes);
		assertEquals(freeRoomTypes.size(), foundFreeRoomTypes.size());
		
		//limit rooms
		List<RoomType> foundFreeRoomTypes2 = bookingService.findFreeRooms(startDate, endDate, people, 4);
		assertEquals(freeRoomTypes, foundFreeRoomTypes2);
		assertEquals(freeRoomTypes.size(), foundFreeRoomTypes2.size());
		
		//middle
		startDate.add(Calendar.DAY_OF_YEAR, 1);
		List<RoomType> foundFreeRoomTypes3 = bookingService.findFreeRooms(startDate, endDate, people, rooms);
		assertEquals(freeRoomTypes, foundFreeRoomTypes3);
		assertEquals(freeRoomTypes.size(), foundFreeRoomTypes3.size());
		
		//last night
		startDate.add(Calendar.DAY_OF_YEAR, 2); //+1 +2
		endDate.add(Calendar.DAY_OF_YEAR, 2); // +2 +2
		List<RoomType> foundFreeRoomTypes10 = bookingService.findFreeRooms(startDate, endDate, people, rooms);
		assertEquals(freeRoomTypes, foundFreeRoomTypes10);
		assertEquals(freeRoomTypes.size(), foundFreeRoomTypes10.size());
		
		
		//another room
		RoomType roomType2 = createRoomType("namedos", 2, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(roomType2);
		
		//Day1
		SaleRoom saleRoom5 = saleRoomService.addSaleRoom(roomType2.getId(), date, freeRooms);
		SaleRoomTariff saleRoomTariff = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);

		
		//Day2
		SaleRoom saleRoom6 = saleRoomService.addSaleRoom(roomType2.getId(), date2, freeRooms);
		SaleRoomTariff saleRoomTariff2 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		
		//Day3
		SaleRoom saleRoom7 = saleRoomService.addSaleRoom(roomType2.getId(), date3, freeRooms);
		SaleRoomTariff saleRoomTariff3 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		
		freeRoomTypes2.add(roomType2);
		
		//Find
		Calendar startDate2 = Calendar.getInstance();
		startDate2.set(Calendar.HOUR_OF_DAY, 0);
		startDate2.set(Calendar.MINUTE, 0);
		startDate2.set(Calendar.MILLISECOND, 0);
		startDate2.set(Calendar.SECOND, 0);
		
		Calendar endDate2 = Calendar.getInstance();
		endDate2.set(Calendar.HOUR_OF_DAY, 0);
		endDate2.set(Calendar.MINUTE, 0);
		endDate2.set(Calendar.MILLISECOND, 0);
		endDate2.set(Calendar.SECOND, 0);
		endDate2.add(Calendar.DAY_OF_YEAR, 1);
		
		List<RoomType> foundFreeRoomTypes4 = bookingService.findFreeRooms(startDate2, endDate2, people, rooms);
		
		assertEquals(freeRoomTypes2, foundFreeRoomTypes4);
		assertEquals(freeRoomTypes2.size(), foundFreeRoomTypes4.size());
		
	}
	
	
	
	@Test
	public void testFindTariffByFreeRoom() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException {

		Tariff newTariff = createTariff("name", "CODE");
		Tariff tariff = tariffService.addTariff(newTariff);
		Tariff newTariff2 = createTariff("name2", "CODE2");
		Tariff tariff2 = tariffService.addTariff(newTariff2);

		RoomType roomType = createRoomType("name", 2, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(roomType);

		int freeRooms = 4;
		BigDecimal price = new BigDecimal(90);
		
		List<Tariff> availableTariffs = new ArrayList<>();
		List<Tariff> availableTariffs2 = new ArrayList<>();
		
		//Day1
		Calendar date = Calendar.getInstance();
		saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
		saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		saleRoomTariffService.uploadSaleRoomTariff(price, tariff2.getId(),
				roomType.getId(), date);
		
		//Day2
		Calendar date2 = Calendar.getInstance();
		date2.add(Calendar.DAY_OF_YEAR, 1);
		saleRoomService.addSaleRoom(roomType.getId(), date2, freeRooms);
		saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date2);
		
		availableTariffs.add(tariff);
		availableTariffs2.add(tariff);
		availableTariffs2.add(tariff2);
		
		//Find
		Calendar startDate = Calendar.getInstance();
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		startDate.set(Calendar.SECOND, 0);
		
		Calendar endDate = Calendar.getInstance();
		endDate.set(Calendar.HOUR_OF_DAY, 0);
		endDate.set(Calendar.MINUTE, 0);
		endDate.set(Calendar.MILLISECOND, 0);
		endDate.set(Calendar.SECOND, 0);
		endDate.add(Calendar.DAY_OF_YEAR, 2);
		
		
		List<Tariff> foundTariffs = bookingService.findTariffsByFreeRoom(startDate, endDate, roomType.getId());
				
		assertEquals(availableTariffs, foundTariffs);
		assertEquals(availableTariffs.size(), foundTariffs.size());
		
		
		endDate.add(Calendar.DAY_OF_YEAR, -1);
		List<Tariff> foundTariffs2 = bookingService.findTariffsByFreeRoom(startDate, endDate, roomType.getId());
		
		assertEquals(availableTariffs2, foundTariffs2);
		assertEquals(availableTariffs2.size(), foundTariffs2.size());
		
		
	}
	
//	@Test
//	public void testAdd() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException {

//		Tariff newTariff = createTariff("name", "CODE");
//		Tariff tariff = tariffService.addTariff(newTariff);
//
//		RoomType roomType = createRoomType("name", 2, new BigDecimal(30), new BigDecimal(100));
//		roomTypeService.addRoomType(roomType);
//
//		Calendar date = Calendar.getInstance();
//
//		int freeRooms = 4;
//		BigDecimal price = new BigDecimal(90);
//
//		List<BookingDay> bookingDays = new ArrayList<>();
//
//		//BookingDay1
//		SaleRoom saleRoom = saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
//		SaleRoomTariff saleRoomTariff = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
//				roomType.getId(), date);
//		BookingDay bookingDay = new BookingDay(saleRoomTariff.getPrice(), saleRoomTariff);
//		bookingDays.add(bookingDay);
//		
//		
//		//BookingDay2
//		date.add(Calendar.DAY_OF_YEAR, 1);
//		SaleRoom saleRoom2 = saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
//		SaleRoomTariff saleRoomTariff2 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
//				roomType.getId(), date);
//		BookingDay bookingDay2 = new BookingDay(saleRoomTariff2.getPrice(), saleRoomTariff2);
//		bookingDays.add(bookingDay2);
//		
//		//BookingDay3
//		date.add(Calendar.DAY_OF_YEAR, 1);
//		SaleRoom saleRoom3 = saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
//		SaleRoomTariff saleRoomTariff3 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
//				roomType.getId(), date);
//		BookingDay bookingDay3 = new BookingDay(saleRoomTariff3.getPrice(), saleRoomTariff3);
//		bookingDays.add(bookingDay3);
//		
//		//BookingRooms
//		BookingRoom bookingRoom = new BookingRoom(bookingDays);
//		
//		List<BookingRoom> bookingRooms = new ArrayList<>();
//		bookingRooms.add(bookingRoom);
//		
//		//Booking
//		Calendar startDate = Calendar.getInstance();
//		Calendar endDate = Calendar.getInstance();
//		endDate.add(Calendar.DAY_OF_YEAR, 2);
//		String name = "name";
//		String surName = "surNmae";
//		String phone = "666666666";
//		String email = "user@user.com";
//		String petition = "petition";
//				
//		Booking booking = bookingService.addBooking(bookingRooms, startDate, endDate, name, surName, phone, email, petition);
//
//		Optional <Booking> foundBooking = bookingDao.findById(booking.getId());
//		
//		assertEquals(booking.getId(), foundBooking.get().getId());
//		assertEquals(bookingRooms, foundBooking.get().getBookingRooms());
//		assertEquals(startDate, foundBooking.get().getStarDate());
//		assertEquals(endDate, foundBooking.get().getEndDate());
//		assertEquals(phone, foundBooking.get().getPhone());
//		assertEquals(email, foundBooking.get().getEmail());
//		assertEquals(petition, foundBooking.get().getPetition());
//		
		
//	}
	
}
