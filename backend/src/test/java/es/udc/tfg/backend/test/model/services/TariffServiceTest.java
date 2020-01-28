package es.udc.tfg.backend.test.model.services;

import static org.junit.Assert.assertEquals;

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
import es.udc.tfg.backend.model.entities.Tariff;
import es.udc.tfg.backend.model.entities.Hotel;
import es.udc.tfg.backend.model.services.PermissionException;
import es.udc.tfg.backend.model.services.TariffService;
import es.udc.tfg.backend.model.services.HotelService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class TariffServiceTest {

	private final Long NON_EXISTENT_ID = new Long(-1);

	@Autowired
	private HotelService userService;
	
	@Autowired
	private TariffService tariffService;
	
	private Hotel signUpUser(String userName) {
		
		Hotel user = new Hotel(userName, "password", null, "hotelName", "address", userName + "@" + userName + ".com", "666666666");
		
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
	

	@Test
	public void testAddTariffAndFind() throws DuplicateInstanceException, InstanceNotFoundException {
		Hotel user = signUpUser("user");
		
		Tariff tariff = createTariff(user,"name", "CODE", "description");
		tariffService.addTariff(user.getId(), tariff);

		Tariff tariffFind = tariffService.findTariffById(tariff.getId());

		assertEquals(tariff, tariffFind);
	}

	@Test(expected = DuplicateInstanceException.class)
	public void testAddTariffNameDuplicate() throws DuplicateInstanceException, InstanceNotFoundException {
		Hotel user = signUpUser("user");
		
		Tariff tariff = createTariff(user, "name", "CODE", "description");
		tariffService.addTariff(user.getId(), tariff);

		Tariff tariff2 = createTariff(user, "name", "CODE2", "description");
		tariffService.addTariff(user.getId(), tariff2);
	}

	@Test(expected = DuplicateInstanceException.class)
	public void testAddTariffCodeDuplicate() throws DuplicateInstanceException, InstanceNotFoundException {
		Hotel user = signUpUser("user");
		
		Tariff tariff = createTariff(user, "name", "CODE", "description");
		tariffService.addTariff(user.getId(), tariff);

		Tariff tariff2 = createTariff(user, "name2", "CODE", "description");
		tariffService.addTariff(user.getId(), tariff2);
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testTariffNotFound() throws DuplicateInstanceException, InstanceNotFoundException {

		tariffService.findTariffById(NON_EXISTENT_ID);

	}

	@Test
	public void testAddTariffAndUpdate() throws DuplicateInstanceException, InstanceNotFoundException, PermissionException {
		Hotel user = signUpUser("user");
		Tariff tariff = createTariff(user, "name", "CODE", "description");
		tariffService.addTariff(user.getId(), tariff);

		String nameNew = "nameNew";
		String codeNew = "codeNEW";
		String descriptionNew = "descriptionNEW";

		tariff.setName(nameNew);
		tariff.setCode(codeNew);
		tariff.setDescription(descriptionNew);

		Tariff tariffUpdate = tariffService.updateTariff(user.getId(), tariff);

		assertEquals(tariff.getId(), tariffUpdate.getId());
		assertEquals(nameNew, tariffUpdate.getName());
		assertEquals(codeNew, tariffUpdate.getCode());
		assertEquals(descriptionNew, tariffUpdate.getDescription());
		

	}

	@Test
	public void testFindAllTariffs() throws DuplicateInstanceException, InstanceNotFoundException {
		Hotel user = signUpUser("user");
		
		Tariff tariff1 = createTariff(user, "name", "CODE", "description");
		tariffService.addTariff(user.getId(), tariff1);

		Tariff tariff2 = createTariff(user, "name2", "CODE2", "description");
		tariffService.addTariff(user.getId(), tariff2);

		assertEquals(Arrays.asList(tariff1, tariff2), tariffService.findAllTariffs());
	}

}
