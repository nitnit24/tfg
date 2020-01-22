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
import es.udc.tfg.backend.model.entities.User;
import es.udc.tfg.backend.model.services.PermissionException;
import es.udc.tfg.backend.model.services.RoomTypeService;
import es.udc.tfg.backend.model.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class RoomTypeServiceTest {
	private final Long NON_EXISTENT_ID = new Long(-1);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomTypeService roomTypeService;

	private RoomType createRoomType(User user, String name, String description, int capacity, int quantity, BigDecimal minPrice, BigDecimal maxPrice) 
			throws DuplicateInstanceException {
		return new RoomType(user, name, description, capacity, quantity,  minPrice, maxPrice);
	}
	
	private User signUpUser(String userName) {
		
		User user = new User(userName, "password", "hotelName", "address", userName + "@" + userName + ".com", "666666666");
		
		try {
			userService.signUp(user);
		} catch (DuplicateInstanceException e) {
			throw new RuntimeException(e);
		}
		
		return user;
		
	}

	@Test
	public void testAddRoomTypeAndFind() throws DuplicateInstanceException, InstanceNotFoundException {
		User user = signUpUser("user");
		
		RoomType roomType = createRoomType(user, "name", "description", 2,10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType);

		RoomType roomTypeFind = roomTypeService.findRoomTypeById(roomType.getId());

		assertEquals(roomType, roomTypeFind);
	}

	@Test(expected = DuplicateInstanceException.class)
	public void testAddRoomTypeNameDuplicate() throws DuplicateInstanceException, InstanceNotFoundException {
		User user = signUpUser("user"); 
		
		RoomType roomType = createRoomType(user, "name", "description", 2, 10,  new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType);

		RoomType roomType2 = createRoomType(user, "name", "description", 2, 10, new BigDecimal (40), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType2);
	}



	@Test(expected = InstanceNotFoundException.class)
	public void testRoomTypeNotFound() throws DuplicateInstanceException, InstanceNotFoundException {

		roomTypeService.findRoomTypeById(NON_EXISTENT_ID);

	}
	
	@Test
	public void testAddRoomTypeAndUpdate() throws DuplicateInstanceException, InstanceNotFoundException, PermissionException {
		User user = signUpUser("user");
		
		RoomType roomType = createRoomType(user, "name", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType);

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

		RoomType roomTypeUpdate = roomTypeService.updateRoomType(user.getId(), roomType);

		assertEquals(roomType.getId(), roomTypeUpdate.getId());
		assertEquals(nameNew, roomTypeUpdate.getName());
		assertEquals(descriptionNew, roomTypeUpdate.getDescription());
		assertEquals(capacityNew, roomTypeUpdate.getCapacity());
		assertEquals(quantityNew, roomTypeUpdate.getQuantity());
		assertEquals(minPrice, roomTypeUpdate.getMinPrice());
		assertEquals(maxPrice, roomTypeUpdate.getMaxPrice());

	}

	@Test
	public void testFindAllRoomTypes() throws DuplicateInstanceException, InstanceNotFoundException {
		User user = signUpUser("user");
		
		RoomType roomType = createRoomType(user, "name", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType);

		RoomType roomType2 = createRoomType(user, "name2", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType2);

		assertEquals(Arrays.asList(roomType, roomType2), roomTypeService.findAllRoomTypes());
	}
	

	@Test(expected = InstanceNotFoundException.class)
	public void testFindRoomTypeAndRemove() throws DuplicateInstanceException, InstanceNotFoundException, PermissionException {
		User user = signUpUser("user");
		
		RoomType roomType = createRoomType(user, "name", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType);

		RoomType roomTypeFind = roomTypeService.findRoomTypeById(roomType.getId());

		roomTypeService.removeRoomType(user.getId(), roomTypeFind.getId());
		roomTypeService.findRoomTypeById(roomTypeFind.getId());
		
	}
	
	@Test
	public void testRemoveAndFindAllRoomTypes() throws DuplicateInstanceException, InstanceNotFoundException, PermissionException {
		User user = signUpUser("user");
		
		RoomType roomType = createRoomType(user, "name", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType);

		RoomType roomType2 = createRoomType(user, "name2", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType2);
		
		RoomType roomType3 = createRoomType(user, "name3", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType3);
		
		RoomType roomType4 = createRoomType(user, "name4", "description", 2, 10, new BigDecimal (30), new BigDecimal (100));
		roomTypeService.addRoomType(user.getId(), roomType4);

		assertEquals(Arrays.asList(roomType, roomType2, roomType3, roomType4), roomTypeService.findAllRoomTypes());
		
		//medio de lista
		roomTypeService.removeRoomType(user.getId(), roomType3.getId());
		assertEquals(Arrays.asList(roomType, roomType2, roomType4), roomTypeService.findAllRoomTypes());
		
		//inicio de lista
		roomTypeService.removeRoomType(user.getId(), roomType.getId());
		assertEquals(Arrays.asList(roomType2, roomType4), roomTypeService.findAllRoomTypes());
		
		//fin de lista
		roomTypeService.removeRoomType(user.getId(), roomType4.getId());
		assertEquals(Arrays.asList(roomType2), roomTypeService.findAllRoomTypes());
		
	}

}