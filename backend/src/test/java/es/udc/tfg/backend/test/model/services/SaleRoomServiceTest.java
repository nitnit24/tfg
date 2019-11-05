package es.udc.tfg.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

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

	private RoomType createRoomType(String name, int capacity, BigDecimal minPrice, BigDecimal maxPrice) 
			throws DuplicateInstanceException {
		return new RoomType(name, capacity, minPrice, maxPrice);
	}

	@Test
	public void testAddSaleRoomAndFind() throws DuplicateInstanceException, InstanceNotFoundException {

		RoomType roomType = createRoomType("name", 2, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType);
		
		Calendar today = Calendar.getInstance();
		
		int freeRooms = 4;
		
		saleRoomService.addSaleRoom(roomType.getId(), today, freeRooms);

		SaleRoom saleRoomFind = saleRoomService.findSaleRoomByIdAndDate(roomType.getId(), today);

		assertEquals(roomType, saleRoomFind);
	}

	


}