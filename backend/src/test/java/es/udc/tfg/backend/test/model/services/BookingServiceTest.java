package es.udc.tfg.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import es.udc.tfg.backend.model.entities.Booking;
import es.udc.tfg.backend.model.entities.BookingDao;
import es.udc.tfg.backend.model.entities.BookingRoomSummary;
import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.SaleRoom;
import es.udc.tfg.backend.model.entities.SaleRoomTariff;
import es.udc.tfg.backend.model.entities.SaleRoomTariffDao;
import es.udc.tfg.backend.model.entities.State;
import es.udc.tfg.backend.model.entities.Tariff;
import es.udc.tfg.backend.model.entities.User;
import es.udc.tfg.backend.model.services.Block;
import es.udc.tfg.backend.model.services.BookingService;
import es.udc.tfg.backend.model.services.FreeRoomsLessThanRoomTypeQuantityException;
import es.udc.tfg.backend.model.services.PriceNotBetweenMinAndMaxValueException;
import es.udc.tfg.backend.model.services.RoomTypeService;
import es.udc.tfg.backend.model.services.SaleRoomService;
import es.udc.tfg.backend.model.services.SaleRoomTariffService;
import es.udc.tfg.backend.model.services.TariffService;
import es.udc.tfg.backend.model.services.ThereAreNotEnoughtFreeRoomsException;
import es.udc.tfg.backend.model.services.UserService;

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
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private UserService userService;

	private User signUpUser(String userName) {
		
		User user = new User(userName, "password", "hotelName", "address", userName + "@" + userName + ".com", "666666666");
		
		try {
			userService.signUp(user);
		} catch (DuplicateInstanceException e) {
			throw new RuntimeException(e);
		}
		
		return user;
		
	}
	
	private Tariff createTariff(User user, String name, String code,String description) throws DuplicateInstanceException {
		return new Tariff(user, name, code, description);
	}
	
	private RoomType createRoomType(User user, String name,String description, int capacity, int quantity, BigDecimal minPrice, BigDecimal maxPrice) 
			throws DuplicateInstanceException {
		return new RoomType(user, name, description, capacity, quantity, minPrice, maxPrice);
	}
	
	
	
	@Test
	public void testFindFreeRooms() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException, FreeRoomsLessThanRoomTypeQuantityException {
		User user = signUpUser("user");
		
		Tariff newTariff = createTariff(user, "name", "CODE", "description");
		Tariff tariff = tariffService.addTariff(user.getId(), newTariff);

		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(),roomType);

		int freeRooms = 4;
		BigDecimal price = new BigDecimal(90);
		
		List<RoomType> freeRoomTypes = new ArrayList<>();
		List<RoomType> freeRoomTypes2 = new ArrayList<>();
		
		//Day1
		Calendar date = Calendar.getInstance();
		saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
//		SaleRoomTariff saleRoomTariff = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
//				roomType.getId(), date);
//
//		
//		//Day2
		Calendar date2 = Calendar.getInstance();
		date2.add(Calendar.DAY_OF_YEAR, 1);
		saleRoomService.addSaleRoom(roomType.getId(), date2, freeRooms);
//		SaleRoomTariff saleRoomTariff2 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
//				roomType.getId(), date);
//		
//		//Day3
		Calendar date3 = Calendar.getInstance();
		date3.add(Calendar.DAY_OF_YEAR, 2);
		saleRoomService.addSaleRoom(roomType.getId(), date3, freeRooms);
//		SaleRoomTariff saleRoomTariff3 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
//				roomType.getId(), date);
//		
		//Day4 
		Calendar date4 = Calendar.getInstance();
		date4.add(Calendar.DAY_OF_YEAR, 3);
		saleRoomService.addSaleRoom(roomType.getId(), date4, freeRooms);
		
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
		RoomType roomType2 = createRoomType(user, "namedos","description", 2, 10, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(),roomType2);
		
		//Day1
		saleRoomService.addSaleRoom(roomType2.getId(), date, freeRooms);
		saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		
		//Day2
		saleRoomService.addSaleRoom(roomType2.getId(), date2, freeRooms);
		saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		
		//Day3
		saleRoomService.addSaleRoom(roomType2.getId(), date3, freeRooms);
		saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
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
	public void testFindTariffByFreeRoom() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException, FreeRoomsLessThanRoomTypeQuantityException {
		User user = signUpUser("user");
		
		Tariff newTariff = createTariff(user, "name", "CODE", "description");
		Tariff tariff = tariffService.addTariff(user.getId(), newTariff);
		Tariff newTariff2 = createTariff(user, "name2", "CODE2", "description");
		Tariff tariff2 = tariffService.addTariff(user.getId(), newTariff2);

		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(),roomType);

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
	
	
	@Test
	public void tesSaleRoomTariffsByFreeRoom() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException, FreeRoomsLessThanRoomTypeQuantityException {
		User user = signUpUser("user");
		
		Tariff newTariff = createTariff(user, "name", "CODE", "description");
		Tariff tariff = tariffService.addTariff(user.getId(), newTariff);
		Tariff newTariff2 = createTariff(user, "name2", "CODE2", "description");
		Tariff tariff2 = tariffService.addTariff(user.getId(), newTariff2);

		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(), roomType);

		int freeRooms = 4;
		BigDecimal price = new BigDecimal(90);
		
		List<SaleRoomTariff> saleRoomTariffs = new ArrayList<>();
		List<SaleRoomTariff> saleRoomTariffs2 = new ArrayList<>();
		
		//Day1
		Calendar date = Calendar.getInstance();
		saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
		SaleRoomTariff saleRoomTariff1 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		SaleRoomTariff saleRoomTariff2 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff2.getId(),
				roomType.getId(), date);
		
		//Day2
		Calendar date2 = Calendar.getInstance();
		date2.add(Calendar.DAY_OF_YEAR, 1);
		saleRoomService.addSaleRoom(roomType.getId(), date2, freeRooms);
		SaleRoomTariff saleRoomTariff3 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date2);
		
		saleRoomTariffs.add(saleRoomTariff1);
		saleRoomTariffs2.add(saleRoomTariff2);
		saleRoomTariffs.add(saleRoomTariff3);
		
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
		
		
		List<SaleRoomTariff> foundSaleRoomTariff = bookingService.findSaleRoomTariffsByFreeRoom(startDate, endDate, roomType.getId(), tariff.getId());
				
		assertEquals(saleRoomTariffs, foundSaleRoomTariff);
		assertEquals(saleRoomTariffs.size(), foundSaleRoomTariff.size());
		
		
		endDate.add(Calendar.DAY_OF_YEAR, -1);
		List<SaleRoomTariff> foundSaleRoomTariff2 = bookingService.findSaleRoomTariffsByFreeRoom(startDate, endDate, roomType.getId(), tariff2.getId());
		
		assertEquals(saleRoomTariffs2, foundSaleRoomTariff2);
		assertEquals(saleRoomTariffs2.size(), foundSaleRoomTariff2.size());
		
		
	}
	
	@Test
	public void testMakeBooking() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException, FreeRoomsLessThanRoomTypeQuantityException, ThereAreNotEnoughtFreeRoomsException, UnsupportedEncodingException, IOException {
		User user = signUpUser("user");
		Tariff newTariff = createTariff(user, "name", "CODE", "description");
		Tariff tariff = tariffService.addTariff(user.getId(), newTariff);

		RoomType roomType = createRoomType(user, "name","description", 2, 20, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(), roomType);

		Calendar date = Calendar.getInstance();

		int freeRooms = 10;
		BigDecimal price = new BigDecimal(90);

		List<SaleRoomTariff> saleRoomTariffs = new ArrayList<>();

		//BookingDay1
		SaleRoom saleRoom1 = saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
		SaleRoomTariff saleRoomTariff = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		saleRoomTariffs.add(saleRoomTariff);
		
		
		//BookingDay2
		Calendar date2 = Calendar.getInstance();
		date2.add(Calendar.DAY_OF_YEAR, 1);
		SaleRoom saleRoom2 =saleRoomService.addSaleRoom(roomType.getId(), date2, freeRooms);
		SaleRoomTariff saleRoomTariff2 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date2);
		saleRoomTariffs.add(saleRoomTariff2);
		
		//BookingDay3
		Calendar date3 = Calendar.getInstance();
		date3.add(Calendar.DAY_OF_YEAR, 2);
		SaleRoom saleRoom3 = saleRoomService.addSaleRoom(roomType.getId(), date3, 6);
		SaleRoomTariff saleRoomTariff3 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date3);
		saleRoomTariffs.add(saleRoomTariff3);
		
		//BookingRooms
//		BookingRoom bookingRoom = new BookingRoom(bookingDays);
//		
//		List<BookingRoom> bookingRooms = new ArrayList<>();
//		bookingRooms.add(bookingRoom);
//		
		//Booking
		int quantity = 1;
		BookingRoomSummary bookingRoomSummary = new BookingRoomSummary(saleRoomTariffs, quantity);
		List<BookingRoomSummary> bookingRoomSummarys = new ArrayList<>();
		bookingRoomSummarys.add(bookingRoomSummary);
		
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_YEAR, 2);
		String name = "name";
		String surName = "surNmae";
		String phone = "666666666";
		String email = "user@user.com";
		String petition = "petition";
		
		int duration = 2;
		Calendar bookingDate = Calendar.getInstance();
		bookingDate.set(Calendar.MILLISECOND, 0);
		bookingDate.set(Calendar.SECOND, 0);
		
		Booking booking = bookingService.makeBooking(bookingRoomSummarys, startDate, endDate, name, surName, phone, email, petition);

		Optional <Booking> foundBooking = bookingDao.findById(booking.getId());
		
		assertEquals(booking.getId(), foundBooking.get().getId());
		assertEquals(booking.getKey(), foundBooking.get().getKey());
		assertEquals(booking.getLocator(), foundBooking.get().getLocator());
		assertEquals(bookingDate, foundBooking.get().getDate());
		assertEquals(startDate, foundBooking.get().getStartDate());
		assertEquals(duration, foundBooking.get().getDuration());
		assertEquals(endDate, foundBooking.get().getEndDate());
		assertEquals(phone, foundBooking.get().getPhone());
		assertEquals(email, foundBooking.get().getEmail());
		assertEquals(petition, foundBooking.get().getPetition());
		assertEquals(State.CONFIRMADA, foundBooking.get().getState());
		
		assertEquals(1,foundBooking.get().getBookingRooms().size());
		assertEquals(3,foundBooking.get().getBookingRooms().iterator().next().getBookingDays().size());
	
		int newfreeRooms = 9;
		
		assertEquals(newfreeRooms, saleRoom1.getFreeRooms());
		assertEquals(newfreeRooms, saleRoom2.getFreeRooms());
		assertEquals(5, saleRoom3.getFreeRooms());
	}
	
	@Test
	public void testMakeBookingAndFindByLocator() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException, FreeRoomsLessThanRoomTypeQuantityException, ThereAreNotEnoughtFreeRoomsException, UnsupportedEncodingException, IOException {
		User user = signUpUser("user");
		Tariff newTariff = createTariff(user, "name", "CODE", "description");
		Tariff tariff = tariffService.addTariff(user.getId(), newTariff);

		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(), roomType);

		Calendar date = Calendar.getInstance();

		int freeRooms = 10;
		BigDecimal price = new BigDecimal(90);

		List<SaleRoomTariff> saleRoomTariffs = new ArrayList<>();

		//BookingDay1
		saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
		SaleRoomTariff saleRoomTariff = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		saleRoomTariffs.add(saleRoomTariff);
		
		
		//BookingDay2
		date.add(Calendar.DAY_OF_YEAR, 1);
		saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
		SaleRoomTariff saleRoomTariff2 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		saleRoomTariffs.add(saleRoomTariff2);
		
		//BookingDay3
		date.add(Calendar.DAY_OF_YEAR, 1);
		saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
		SaleRoomTariff saleRoomTariff3 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		saleRoomTariffs.add(saleRoomTariff3);
		
		//Booking
		int quantity = 2;
		BookingRoomSummary bookingRoomSummary = new BookingRoomSummary(saleRoomTariffs, quantity);
		List<BookingRoomSummary> bookingRoomSummarys = new ArrayList<>();
		bookingRoomSummarys.add(bookingRoomSummary);
		
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_YEAR, 2);
		String name = "name";
		String surName = "surNmae";
		String phone = "666666666";
		String email = "user@user.com";
		String petition = "petition";
		
		int duration = 2;
		Calendar bookingDate = Calendar.getInstance();
		bookingDate.set(Calendar.MILLISECOND, 0);
		bookingDate.set(Calendar.SECOND, 0);
		
		Booking booking = bookingService.makeBooking(bookingRoomSummarys, startDate, endDate, name, surName, phone, email, petition);

		Optional <Booking> foundBooking = bookingDao.findByLocator(booking.getLocator());
		
		assertEquals(booking.getId(), foundBooking.get().getId());
		assertEquals(booking.getKey(), foundBooking.get().getKey());
		assertEquals(booking.getLocator(), foundBooking.get().getLocator());
		assertEquals(bookingDate, foundBooking.get().getDate());
		assertEquals(startDate, foundBooking.get().getStartDate());
		assertEquals(duration, foundBooking.get().getDuration());
		assertEquals(endDate, foundBooking.get().getEndDate());
		assertEquals(phone, foundBooking.get().getPhone());
		assertEquals(email, foundBooking.get().getEmail());
		assertEquals(petition, foundBooking.get().getPetition());
		assertEquals(State.CONFIRMADA, foundBooking.get().getState());
		
		assertEquals(1,foundBooking.get().getBookingRooms().size());
		assertEquals(3,foundBooking.get().getBookingRooms().iterator().next().getBookingDays().size());
	
	}
	
	@Test
	public void testMakeBookingAndFindLocatorAndKey() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException, FreeRoomsLessThanRoomTypeQuantityException, ThereAreNotEnoughtFreeRoomsException, UnsupportedEncodingException, IOException {
		User user = signUpUser("user");
		Tariff newTariff = createTariff(user, "name", "CODE", "description");
		Tariff tariff = tariffService.addTariff(user.getId(), newTariff);

		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(), roomType);

		Calendar date = Calendar.getInstance();

		int freeRooms = 4;
		BigDecimal price = new BigDecimal(90);

		List<SaleRoomTariff> saleRoomTariffs = new ArrayList<>();

		//BookingDay1
		saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
		SaleRoomTariff saleRoomTariff = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		saleRoomTariffs.add(saleRoomTariff);
		
		
		//BookingDay2
		Calendar date2 = Calendar.getInstance();
		date2.add(Calendar.DAY_OF_YEAR, 1);
		saleRoomService.addSaleRoom(roomType.getId(), date2, freeRooms);
		SaleRoomTariff saleRoomTariff2 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date2);
		saleRoomTariffs.add(saleRoomTariff2);
		
		//BookingDay3
		Calendar date3 = Calendar.getInstance();
		date3.add(Calendar.DAY_OF_YEAR, 2);
		saleRoomService.addSaleRoom(roomType.getId(), date3, freeRooms);
		SaleRoomTariff saleRoomTariff3 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date3);
		saleRoomTariffs.add(saleRoomTariff3);
			
		//Booking
		int quantity = 2;
		BookingRoomSummary bookingRoomSummary = new BookingRoomSummary(saleRoomTariffs, quantity);
		List<BookingRoomSummary> bookingRoomSummarys = new ArrayList<>();
		bookingRoomSummarys.add(bookingRoomSummary);
		
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_YEAR, 2);
		String name = "name";
		String surName = "surNmae";
		String phone = "666666666";
		String email = "user@user.com";
		String petition = "petition";
		
		int duration = 2;
		Calendar bookingDate = Calendar.getInstance();
		bookingDate.set(Calendar.MILLISECOND, 0);
		bookingDate.set(Calendar.SECOND, 0);
		
		Booking booking = bookingService.makeBooking(bookingRoomSummarys, startDate, endDate, name, surName, phone, email, petition);

		Optional <Booking> foundBooking = bookingDao.findByLocatorAndKey(booking.getLocator(), booking.getKey());
		
		assertEquals(booking.getId(), foundBooking.get().getId());
		assertEquals(booking.getKey(), foundBooking.get().getKey());
		assertEquals(booking.getLocator(), foundBooking.get().getLocator());
		assertEquals(bookingDate, foundBooking.get().getDate());
		assertEquals(startDate, foundBooking.get().getStartDate());
		assertEquals(duration, foundBooking.get().getDuration());
		assertEquals(endDate, foundBooking.get().getEndDate());
		assertEquals(phone, foundBooking.get().getPhone());
		assertEquals(email, foundBooking.get().getEmail());
		assertEquals(petition, foundBooking.get().getPetition());
		assertEquals(State.CONFIRMADA, foundBooking.get().getState());
		
		assertEquals(1,foundBooking.get().getBookingRooms().size());
		assertEquals(3,foundBooking.get().getBookingRooms().iterator().next().getBookingDays().size());
		
	}
	
	@Test
	public void testMakeBookingAndCancel() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException, FreeRoomsLessThanRoomTypeQuantityException, ThereAreNotEnoughtFreeRoomsException, UnsupportedEncodingException, IOException {
		User user = signUpUser("user");
		
		Tariff newTariff = createTariff(user, "name", "CODE", "description");
		Tariff tariff = tariffService.addTariff(user.getId(), newTariff);

		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(), roomType);

		Calendar date = Calendar.getInstance();

		int freeRooms = 4;
		BigDecimal price = new BigDecimal(90);

		List<SaleRoomTariff> saleRoomTariffs = new ArrayList<>();

		//BookingDay1
		saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
		SaleRoomTariff saleRoomTariff = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		saleRoomTariffs.add(saleRoomTariff);
		
		
		//BookingDay2
		Calendar date2 = Calendar.getInstance();
		date2.add(Calendar.DAY_OF_YEAR, 1);
		saleRoomService.addSaleRoom(roomType.getId(), date2, freeRooms);
		SaleRoomTariff saleRoomTariff2 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date2);
		saleRoomTariffs.add(saleRoomTariff2);
		
		//BookingDay3
		Calendar date3 = Calendar.getInstance();
		date3.add(Calendar.DAY_OF_YEAR, 2);
		saleRoomService.addSaleRoom(roomType.getId(), date3, freeRooms);
		SaleRoomTariff saleRoomTariff3 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date3);
		saleRoomTariffs.add(saleRoomTariff3);
			
		//Booking
		int quantity = 2;
		BookingRoomSummary bookingRoomSummary = new BookingRoomSummary(saleRoomTariffs, quantity);
		List<BookingRoomSummary> bookingRoomSummarys = new ArrayList<>();
		bookingRoomSummarys.add(bookingRoomSummary);
		
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_YEAR, 2);
		String name = "name";
		String surName = "surNmae";
		String phone = "666666666";
		String email = "user@user.com";
		String petition = "petition";

		Calendar bookingDate = Calendar.getInstance();
		bookingDate.set(Calendar.MILLISECOND, 0);
		bookingDate.set(Calendar.SECOND, 0);
		
		Booking booking = bookingService.makeBooking(bookingRoomSummarys, startDate, endDate, name, surName, phone, email, petition);

		Booking cancelBooking = bookingService.cancel(booking.getLocator(), booking.getKey());

		assertEquals(State.CANCELADA, cancelBooking.getState());

	}
	
	
	@Test
	public void testMakeBookingAndFind() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException, FreeRoomsLessThanRoomTypeQuantityException, ThereAreNotEnoughtFreeRoomsException, UnsupportedEncodingException, IOException {
		User user = signUpUser("user");
		
		Tariff newTariff = createTariff(user, "name", "CODE", "description");
		Tariff tariff = tariffService.addTariff(user.getId(), newTariff);

		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(), roomType);

		Calendar date = Calendar.getInstance();

		int freeRooms = 10;
		BigDecimal price = new BigDecimal(90);

		List<SaleRoomTariff> saleRoomTariffs = new ArrayList<>();

		//BookingDay1
		saleRoomService.addSaleRoom(roomType.getId(), date, freeRooms);
		SaleRoomTariff saleRoomTariff = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date);
		saleRoomTariffs.add(saleRoomTariff);
		
		
		//BookingDay2
		Calendar date2 = Calendar.getInstance();
		date2.add(Calendar.DAY_OF_YEAR, 1);
		saleRoomService.addSaleRoom(roomType.getId(), date2, freeRooms);
		SaleRoomTariff saleRoomTariff2 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date2);
		saleRoomTariffs.add(saleRoomTariff2);
		
		//BookingDay3
		Calendar date3 = Calendar.getInstance();
		date3.add(Calendar.DAY_OF_YEAR, 2);
		saleRoomService.addSaleRoom(roomType.getId(), date3, freeRooms);
		SaleRoomTariff saleRoomTariff3 = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), date3);
		saleRoomTariffs.add(saleRoomTariff3);
			
		//Booking
		int quantity = 2;
		BookingRoomSummary bookingRoomSummary = new BookingRoomSummary(saleRoomTariffs, quantity);
		List<BookingRoomSummary> bookingRoomSummarys = new ArrayList<>();
		bookingRoomSummarys.add(bookingRoomSummary);
		
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_YEAR, 2);
		String name = "name";
		String surName = "surName";
		String phone = "666666666";
		String email = "user@user.com";
		String petition = "petition";

		Calendar bookingDate = Calendar.getInstance();
		bookingDate.set(Calendar.MILLISECOND, 0);
		bookingDate.set(Calendar.SECOND, 0);
		
		bookingService.makeBooking(bookingRoomSummarys, startDate, endDate, name, surName, phone, email, petition);

		String name2 = "name2";
		String surName2 = "surName2";
		
		bookingService.makeBooking(bookingRoomSummarys, startDate, endDate, name2, surName2, phone, email, petition);
		
		Calendar minDate = Calendar.getInstance();
		minDate.set(Calendar.MILLISECOND, 0);
		minDate.set(Calendar.SECOND, 0);
		minDate.set(Calendar.MINUTE, 0);
		minDate.set(Calendar.HOUR, 0);
		minDate.add(Calendar.DAY_OF_YEAR, -1);
		Calendar maxDate = Calendar.getInstance();
		maxDate.add(Calendar.DAY_OF_YEAR, 2);
		String keywords ="";
		int page = 0;
		int size = 2;
		String dataType = "Entrada";
		
		Block<Booking>  bookings = bookingService.findBookings(dataType, minDate, maxDate, keywords, page, size);

		assertEquals(2, bookings.getItems().size());
		
		Block<Booking>  bookings2 = bookingService.findBookings(dataType, minDate, maxDate, "name2", page, size);

		assertEquals(1, bookings2.getItems().size());

	}
	
}
