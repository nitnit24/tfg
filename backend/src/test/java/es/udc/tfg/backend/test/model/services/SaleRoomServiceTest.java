package es.udc.tfg.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
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
import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.SaleRoom;
import es.udc.tfg.backend.model.entities.SaleRoomDao;
import es.udc.tfg.backend.model.services.RoomTypeService;
import es.udc.tfg.backend.model.services.SaleRoomService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class SaleRoomServiceTest {
	private final Long NON_EXISTENT_ID = new Long(-1);

	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private SaleRoomService saleRoomService;
	
	@Autowired
	private SaleRoomDao saleRoomDao;

	private RoomType createRoomType(String name, int capacity, BigDecimal minPrice, BigDecimal maxPrice) 
			throws DuplicateInstanceException {
		return new RoomType(name, capacity, minPrice, maxPrice);
	}

	@Test
	public void testAdd() throws DuplicateInstanceException, InstanceNotFoundException {

		RoomType roomType = createRoomType("name", 2, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType);
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.MILLISECOND, 0);
		today.set(Calendar.SECOND, 0);
		
		int freeRooms = 4;
		
		SaleRoom saleRoom = saleRoomService.addSaleRoom(roomType.getId(), today, freeRooms);
		
		Optional<SaleRoom> saleRoomFind = saleRoomDao.findById(saleRoom.getIdSaleRoom());

		assertEquals(saleRoom.getIdSaleRoom(), saleRoomFind.get().getIdSaleRoom());
		assertEquals(saleRoom.getDate(), saleRoomFind.get().getDate());
		assertEquals(saleRoom.getFreeRooms(), saleRoomFind.get().getFreeRooms());
		assertEquals(saleRoom.getRoomType(), saleRoomFind.get().getRoomType());
		
		int newfreeRooms = 1;
		
		SaleRoom saleRoomUpdated = saleRoomService.addSaleRoom(roomType.getId(), today, newfreeRooms);
		
		assertEquals(saleRoom.getIdSaleRoom(), saleRoomUpdated.getIdSaleRoom());
		assertEquals(saleRoom.getDate(), saleRoomUpdated.getDate());
		assertEquals(saleRoom.getFreeRooms(), saleRoomUpdated.getFreeRooms());
		assertEquals(saleRoom.getRoomType(), saleRoomUpdated.getRoomType());
		
	}
	
	@Test
	public void testAddAndfindByRoomTypeIdAndDate() throws DuplicateInstanceException, InstanceNotFoundException {

		RoomType roomType = createRoomType("name", 2, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType);
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.MILLISECOND, 0);
		today.set(Calendar.SECOND, 0);
		
		int freeRooms = 4;
		
		SaleRoom saleRoom = saleRoomService.addSaleRoom(roomType.getId(), today, freeRooms);
		
		SaleRoom saleRoomFind = saleRoomService.findByRoomTypeIdAndDate(roomType.getId(), today);

		assertEquals(saleRoom.getIdSaleRoom(), saleRoomFind.getIdSaleRoom());
		assertEquals(saleRoom.getDate(), saleRoomFind.getDate());
		assertEquals(saleRoom.getFreeRooms(), saleRoomFind.getFreeRooms());
		assertEquals(saleRoom.getRoomType(), saleRoomFind.getRoomType());
	}

	


}