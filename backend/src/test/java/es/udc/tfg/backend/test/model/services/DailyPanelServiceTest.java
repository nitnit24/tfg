package es.udc.tfg.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
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
import es.udc.tfg.backend.model.entities.Hotel;
import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.SaleRoom;
import es.udc.tfg.backend.model.entities.SaleRoomDao;
import es.udc.tfg.backend.model.entities.SaleRoomTariff;
import es.udc.tfg.backend.model.entities.SaleRoomTariffDao;
import es.udc.tfg.backend.model.entities.Tariff;
import es.udc.tfg.backend.model.services.DailyPanelService;
import es.udc.tfg.backend.model.services.FreeRoomsLessThanRoomTypeQuantityException;
import es.udc.tfg.backend.model.services.HotelService;
import es.udc.tfg.backend.model.services.PriceMinGreaterThanMaxValueException;
import es.udc.tfg.backend.model.services.PriceNotBetweenMinAndMaxValueException;
import es.udc.tfg.backend.model.services.RoomTypeService;
import es.udc.tfg.backend.model.services.TariffService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class DailyPanelServiceTest {
	private final Long NON_EXISTENT_ID = new Long(-1);

	@Autowired
	private TariffService tariffService;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private DailyPanelService dailyPanelService;
	
	@Autowired
	private SaleRoomDao saleRoomDao;
	
	@Autowired
	private SaleRoomTariffDao saleRoomTariffDao;
	
	@Autowired
	private HotelService userService;

	private Hotel signUpUser(String userName) {
		
		Hotel user = new Hotel(userName, "password", null,  "hotelName", "address", userName + "@" + userName + ".com", "666666666");
		
		try {
			userService.signUp(user);
		} catch (DuplicateInstanceException e) {
			throw new RuntimeException(e);
		}
		
		return user;
		
	}

	private Tariff createTariff(Hotel user, String name, String code, String description) throws DuplicateInstanceException {
		return new Tariff(user, name, code, description);
	}
	
	private RoomType createRoomType(Hotel user, String name,String description, int capacity, int quantity, BigDecimal minPrice, BigDecimal maxPrice) 
			throws DuplicateInstanceException {
		return new RoomType(user,null,  name,description, capacity,quantity, minPrice, maxPrice);
	}
	
	@Test
	public void testAddSaleRoomTariff() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException, FreeRoomsLessThanRoomTypeQuantityException, PriceMinGreaterThanMaxValueException {
		Hotel user = signUpUser("user");
		Tariff newTariff = createTariff(user, "name", "CODE", "description");
		Tariff tariff = tariffService.addTariff(user.getId(), newTariff);

		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(), roomType);

		Calendar today = Calendar.getInstance();

		int freeRooms = 4;

		SaleRoom saleRoom = dailyPanelService.addSaleRoom(roomType.getId(), today, freeRooms);

		BigDecimal price = new BigDecimal(90);

		SaleRoomTariff saleRoomTariff = dailyPanelService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), today);

		Optional<SaleRoomTariff> saleRoomTariffFound = saleRoomTariffDao.findById(saleRoomTariff.getId());

		assertEquals(price, saleRoomTariffFound.get().getPrice());
		assertEquals(tariff, saleRoomTariffFound.get().getTariff());
		assertEquals(saleRoom, saleRoomTariffFound.get().getSaleRoom());
		assertEquals(saleRoomTariff.getId(), saleRoomTariffFound.get().getId());

	}
	
//	@Test
//	public void testAddNewSaleRoom() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException {
//
//		Tariff newTariff = createTariff("name", "CODE");
//		Tariff tariff = tariffService.addTariff(newTariff);
//
//		RoomType roomType = createRoomType("name", 2, new BigDecimal(30), new BigDecimal(100));
//		roomTypeService.addRoomType(roomType);
//
//		Calendar today = Calendar.getInstance();
//
//		BigDecimal price = new BigDecimal(90);
//
//		SaleRoomTariff saleRoomTariff = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
//				roomType.getId(), today);
//
//		Optional<SaleRoomTariff> saleRoomTariffFound = saleRoomTariffDao.findById(saleRoomTariff.getId());
//
//		assertEquals(price, saleRoomTariffFound.get().getPrice());
//		assertEquals(tariff, saleRoomTariffFound.get().getTariff());
//		assertEquals(0, saleRoomTariffFound.get().getSaleRoom().getFreeRooms());
//		assertEquals(saleRoomTariff.getId(), saleRoomTariffFound.get().getId());
//
//	}

	@Test(expected = PriceNotBetweenMinAndMaxValueException.class)
	public void testAddPriceNotBetweenMinAndMaxValue() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException, FreeRoomsLessThanRoomTypeQuantityException, PriceMinGreaterThanMaxValueException {
		Hotel user = signUpUser("user");
		
		Tariff newTariff = createTariff(user, "name", "CODE", "description");
		Tariff tariff = tariffService.addTariff(user.getId(), newTariff);

		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(), roomType);

		Calendar today = Calendar.getInstance();

		int freeRooms = 4;

		dailyPanelService.addSaleRoom(roomType.getId(), today, freeRooms);

		BigDecimal price = new BigDecimal(10);

		dailyPanelService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), today);

	}
	
	@Test(expected = PriceNotBetweenMinAndMaxValueException.class)
	public void testAddPriceNotBetweenMinAndMaxValue2() throws DuplicateInstanceException, InstanceNotFoundException, PriceNotBetweenMinAndMaxValueException, FreeRoomsLessThanRoomTypeQuantityException, PriceMinGreaterThanMaxValueException {
		Hotel user = signUpUser("user");
		Tariff newTariff = createTariff(user, "name", "CODE", "description");
		Tariff tariff = tariffService.addTariff(user.getId(),newTariff);

		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(), roomType);

		Calendar today = Calendar.getInstance();

		int freeRooms = 4;

		dailyPanelService.addSaleRoom(roomType.getId(), today, freeRooms);

		BigDecimal price = new BigDecimal(1000);

		dailyPanelService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), today);

	}
	
	
	@Test
	public void testAddAndFindByTariffIdAndSaleRoomRoomTypeIdAndSaleRoomDate() throws InstanceNotFoundException, DuplicateInstanceException, PriceNotBetweenMinAndMaxValueException, FreeRoomsLessThanRoomTypeQuantityException, PriceMinGreaterThanMaxValueException {
		Hotel user = signUpUser("user");
		
		Tariff newTariff = createTariff(user, "name", "CODE", "description");
		Tariff tariff = tariffService.addTariff(user.getId(), newTariff);

		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(user.getId(), roomType);

		Calendar today = Calendar.getInstance();

		int freeRooms = 4;

		SaleRoom saleRoom = dailyPanelService.addSaleRoom(roomType.getId(), today, freeRooms);

		BigDecimal price = new BigDecimal(90);

		SaleRoomTariff saleRoomTariff = dailyPanelService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), today);

		Optional<SaleRoomTariff> saleRoomTariffFound = saleRoomTariffDao
				.findByTariffIdAndSaleRoomRoomTypeIdAndSaleRoomDate(tariff.getId(), roomType.getId(), today);

		assertEquals(price, saleRoomTariffFound.get().getPrice());
		assertEquals(tariff, saleRoomTariffFound.get().getTariff());
		assertEquals(saleRoom, saleRoomTariffFound.get().getSaleRoom());
		assertEquals(saleRoomTariff.getId(), saleRoomTariffFound.get().getId());

	}

	@Test
	public void testAdd() throws DuplicateInstanceException, InstanceNotFoundException, FreeRoomsLessThanRoomTypeQuantityException, PriceMinGreaterThanMaxValueException {
		Hotel user = signUpUser("user");
		
		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType);
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.MILLISECOND, 0);
		today.set(Calendar.SECOND, 0);
		
		int freeRooms = 4;
		
		SaleRoom saleRoom = dailyPanelService.addSaleRoom(roomType.getId(), today, freeRooms);
		
		Optional<SaleRoom> saleRoomFind = saleRoomDao.findById(saleRoom.getIdSaleRoom());

		assertEquals(saleRoom.getIdSaleRoom(), saleRoomFind.get().getIdSaleRoom());
		assertEquals(saleRoom.getDate(), saleRoomFind.get().getDate());
		assertEquals(saleRoom.getFreeRooms(), saleRoomFind.get().getFreeRooms());
		assertEquals(saleRoom.getRoomType(), saleRoomFind.get().getRoomType());
		
	}
	

	@Test
	public void testAddAndUpdate() throws DuplicateInstanceException, InstanceNotFoundException, FreeRoomsLessThanRoomTypeQuantityException, PriceMinGreaterThanMaxValueException {
		Hotel user = signUpUser("user");
		
		RoomType roomType = createRoomType(user, "name","description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType);
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.MILLISECOND, 0);
		today.set(Calendar.SECOND, 0);
		
		int freeRooms = 4;
		
		SaleRoom saleRoom = dailyPanelService.addSaleRoom(roomType.getId(), today, freeRooms);
		
		Optional<SaleRoom> saleRoomFind = saleRoomDao.findById(saleRoom.getIdSaleRoom());

		assertEquals(saleRoom.getIdSaleRoom(), saleRoomFind.get().getIdSaleRoom());
		assertEquals(saleRoom.getDate(), saleRoomFind.get().getDate());
		assertEquals(saleRoom.getFreeRooms(), saleRoomFind.get().getFreeRooms());
		assertEquals(saleRoom.getRoomType(), saleRoomFind.get().getRoomType());
		
		int newfreeRooms = 1;
		
		SaleRoom saleRoomUpdated = dailyPanelService.addSaleRoom(roomType.getId(), today, newfreeRooms);
		
		assertEquals(saleRoom.getIdSaleRoom(), saleRoomUpdated.getIdSaleRoom());
		assertEquals(saleRoom.getDate(), saleRoomUpdated.getDate());
		assertEquals(saleRoom.getFreeRooms(), saleRoomUpdated.getFreeRooms());
		assertEquals(saleRoom.getRoomType(), saleRoomUpdated.getRoomType());
		
	}
	


	@Test(expected = InstanceNotFoundException.class)
	public void testAddRoomTypeNotFound() throws DuplicateInstanceException, InstanceNotFoundException, FreeRoomsLessThanRoomTypeQuantityException {

		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.MILLISECOND, 0);
		today.set(Calendar.SECOND, 0);
		
		int freeRooms = 4;
		
		dailyPanelService.addSaleRoom(NON_EXISTENT_ID , today, freeRooms);

	}


}