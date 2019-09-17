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
import es.udc.tfg.backend.model.services.TariffService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class TariffServiceTest {

	private final Long NON_EXISTENT_ID = new Long(-1);

	@Autowired
	private TariffService tariffService;

	private Tariff createTariff(String name, String code) throws DuplicateInstanceException {
		return new Tariff(name, code);
	}

	@Test
	public void testAddTariffAndFind() throws DuplicateInstanceException, InstanceNotFoundException {

		Tariff tariff = createTariff("name", "CODE");
		tariffService.addTariff(tariff);

		Tariff tariffFind = tariffService.findTariffById(tariff.getId());

		assertEquals(tariff, tariffFind);
	}

	@Test(expected = DuplicateInstanceException.class)
	public void testAddTariffNameDuplicate() throws DuplicateInstanceException {

		Tariff tariff = createTariff("name", "CODE");
		tariffService.addTariff(tariff);

		Tariff tariff2 = createTariff("name", "CODE2");
		tariffService.addTariff(tariff2);
	}

	@Test(expected = DuplicateInstanceException.class)
	public void testAddTariffCodeDuplicate() throws DuplicateInstanceException {

		Tariff tariff = createTariff("name", "CODE");
		tariffService.addTariff(tariff);

		Tariff tariff2 = createTariff("name2", "CODE");
		tariffService.addTariff(tariff2);
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testTariffNotFound() throws DuplicateInstanceException, InstanceNotFoundException {

		tariffService.findTariffById(NON_EXISTENT_ID);

	}

	@Test
	public void testAddTariffAndUpdate() throws DuplicateInstanceException, InstanceNotFoundException {

		Tariff tariff = createTariff("name", "CODE");
		tariffService.addTariff(tariff);

		String nameNew = "nameNew";
		String codeNew = "CODENEW";

		tariff.setName(nameNew);
		tariff.setCode(codeNew);

		Tariff tariffUpdate = tariffService.updateTariff(tariff);

		assertEquals(tariff.getId(), tariffUpdate.getId());
		assertEquals(nameNew, tariffUpdate.getName());
		assertEquals(codeNew, tariffUpdate.getCode());

	}

	@Test
	public void testFindAllTariffs() throws DuplicateInstanceException {
		Tariff tariff1 = createTariff("name", "CODE");
		tariffService.addTariff(tariff1);

		Tariff tariff2 = createTariff("name2", "CODE2");
		tariffService.addTariff(tariff2);

		assertEquals(Arrays.asList(tariff1, tariff2), tariffService.findAllTariffs());
	}

}
