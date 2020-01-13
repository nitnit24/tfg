package es.udc.tfg.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

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
import es.udc.tfg.backend.model.services.RoomTypeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class RoomTypeServiceTest {
	private final Long NON_EXISTENT_ID = new Long(-1);

	@Autowired
	private RoomTypeService roomTypeService;

	private RoomType createRoomType(String name, String description, int capacity, int quantity, BigDecimal minPrice, BigDecimal maxPrice) 
			throws DuplicateInstanceException {
		return new RoomType(name, description, capacity, quantity,  minPrice, maxPrice);
	}

	@Test
	public void testAddRoomTypeAndFind() throws DuplicateInstanceException, InstanceNotFoundException {

		RoomType roomType = createRoomType("name", "description", 2,10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType);

		RoomType roomTypeFind = roomTypeService.findRoomTypeById(roomType.getId());

		assertEquals(roomType, roomTypeFind);
	}

	@Test(expected = DuplicateInstanceException.class)
	public void testAddRoomTypeNameDuplicate() throws DuplicateInstanceException {

		RoomType roomType = createRoomType("name", "description", 2, 10,  new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType);

		RoomType roomType2 = createRoomType("name", "description", 2, 10, new BigDecimal (40), new BigDecimal (100));
		roomTypeService.addRoomType(roomType2);
	}



	@Test(expected = InstanceNotFoundException.class)
	public void testRoomTypeNotFound() throws DuplicateInstanceException, InstanceNotFoundException {

		roomTypeService.findRoomTypeById(NON_EXISTENT_ID);

	}
	
	@Test
	public void testAddRoomTypeAndUpdate() throws DuplicateInstanceException, InstanceNotFoundException {

		RoomType roomType = createRoomType("name", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType);

		String nameNew = "nameNew";
		String descriptionNew = "descriptionNew";
		int capacityNew = 3;
		int quantityNew = 8;
		BigDecimal minPrice = new BigDecimal (35);
		BigDecimal maxPrice = new BigDecimal (105);
		
		roomType.setName(nameNew);
		roomType.setDescription(descriptionNew);
		roomType.setCapacity(capacityNew);
		roomType.setQuantity(quantityNew);
		roomType.setMinPrice(minPrice);
		roomType.setMaxPrice(maxPrice);

		RoomType roomTypeUpdate = roomTypeService.updateRoomType(roomType);

		assertEquals(roomType.getId(), roomTypeUpdate.getId());
		assertEquals(nameNew, roomTypeUpdate.getName());
		assertEquals(descriptionNew, roomTypeUpdate.getDescription());
		assertEquals(capacityNew, roomTypeUpdate.getCapacity());
		assertEquals(quantityNew, roomTypeUpdate.getQuantity());
		assertEquals(minPrice, roomTypeUpdate.getMinPrice());
		assertEquals(maxPrice, roomTypeUpdate.getMaxPrice());

	}

	@Test
	public void testFindAllRoomTypes() throws DuplicateInstanceException {
		RoomType roomType = createRoomType("name", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType);

		RoomType roomType2 = createRoomType("name2", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType2);

		assertEquals(Arrays.asList(roomType, roomType2), roomTypeService.findAllRoomTypes());
	}
	

	@Test(expected = InstanceNotFoundException.class)
	public void testFindRoomTypeAndRemove() throws DuplicateInstanceException, InstanceNotFoundException {

		RoomType roomType = createRoomType("name", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType);

		RoomType roomTypeFind = roomTypeService.findRoomTypeById(roomType.getId());

		roomTypeService.removeRoomType(roomTypeFind.getId());
		roomTypeService.findRoomTypeById(roomTypeFind.getId());
		
	}
	
	@Test
	public void testRemoveAndFindAllRoomTypes() throws DuplicateInstanceException, InstanceNotFoundException {
		RoomType roomType = createRoomType("name", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType);

		RoomType roomType2 = createRoomType("name2", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType2);
		
		RoomType roomType3 = createRoomType("name3", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType3);
		
		RoomType roomType4 = createRoomType("name4", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(roomType4);

		assertEquals(Arrays.asList(roomType, roomType2, roomType3, roomType4), roomTypeService.findAllRoomTypes());
		
		//medio de lista
		roomTypeService.removeRoomType(roomType3.getId());
		assertEquals(Arrays.asList(roomType, roomType2, roomType4), roomTypeService.findAllRoomTypes());
		
		//inicio de lista
		roomTypeService.removeRoomType(roomType.getId());
		assertEquals(Arrays.asList(roomType2, roomType4), roomTypeService.findAllRoomTypes());
		
		//fin de lista
		roomTypeService.removeRoomType(roomType4.getId());
		assertEquals(Arrays.asList(roomType2), roomTypeService.findAllRoomTypes());
		
	}

}