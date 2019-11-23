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
import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.SaleRoom;
import es.udc.tfg.backend.model.entities.SaleRoomTariff;
import es.udc.tfg.backend.model.entities.SaleRoomTariffDao;
import es.udc.tfg.backend.model.entities.Tariff;
import es.udc.tfg.backend.model.services.RoomTypeService;
import es.udc.tfg.backend.model.services.SaleRoomService;
import es.udc.tfg.backend.model.services.SaleRoomTariffService;
import es.udc.tfg.backend.model.services.TariffService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class SaleRoomTariffServiceTest {
	private final Long NON_EXISTENT_ID = new Long(-1);

	@Autowired
	private TariffService tariffService;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private SaleRoomService saleRoomService;
	
	@Autowired
	private SaleRoomTariffService saleRoomTariffService;
	
	@Autowired
	private SaleRoomTariffDao saleRoomTariffDao;

	private Tariff createTariff(String name, String code) throws DuplicateInstanceException {
		return new Tariff(name, code);
	}
	
	private RoomType createRoomType(String name, int capacity, BigDecimal minPrice, BigDecimal maxPrice) 
			throws DuplicateInstanceException {
		return new RoomType(name, capacity, minPrice, maxPrice);
	}
	
	@Test
	public void testAdd() throws DuplicateInstanceException, InstanceNotFoundException {

		Tariff newTariff = createTariff("name", "CODE");
		Tariff tariff = tariffService.addTariff(newTariff);

		RoomType roomType = createRoomType("name", 2, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(roomType);

		Calendar today = Calendar.getInstance();

		int freeRooms = 4;

		SaleRoom saleRoom = saleRoomService.addSaleRoom(roomType.getId(), today, freeRooms);

		BigDecimal price = new BigDecimal(90);

		SaleRoomTariff saleRoomTariff = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), today);

		Optional<SaleRoomTariff> saleRoomTariffFound = saleRoomTariffDao.findById(saleRoomTariff.getId());

		assertEquals(price, saleRoomTariffFound.get().getPrice());
		assertEquals(tariff, saleRoomTariffFound.get().getTariff());
		assertEquals(saleRoom, saleRoomTariffFound.get().getSaleRoom());
		assertEquals(saleRoomTariff.getId(), saleRoomTariffFound.get().getId());

	}

	@Test
	public void testAddAndFindByTariffIdAndSaleRoomRoomTypeIdAndSaleRoomDate() throws InstanceNotFoundException, DuplicateInstanceException {

		Tariff newTariff = createTariff("name", "CODE");
		Tariff tariff = tariffService.addTariff(newTariff);

		RoomType roomType = createRoomType("name", 2, new BigDecimal(30), new BigDecimal(100));
		roomTypeService.addRoomType(roomType);

		Calendar today = Calendar.getInstance();

		int freeRooms = 4;

		SaleRoom saleRoom = saleRoomService.addSaleRoom(roomType.getId(), today, freeRooms);

		BigDecimal price = new BigDecimal(90);

		SaleRoomTariff saleRoomTariff = saleRoomTariffService.uploadSaleRoomTariff(price, tariff.getId(),
				roomType.getId(), today);

		Optional<SaleRoomTariff> saleRoomTariffFound = saleRoomTariffDao
				.findByTariffIdAndSaleRoomRoomTypeIdAndSaleRoomDate(tariff.getId(), roomType.getId(), today);

		assertEquals(price, saleRoomTariffFound.get().getPrice());
		assertEquals(tariff, saleRoomTariffFound.get().getTariff());
		assertEquals(saleRoom, saleRoomTariffFound.get().getSaleRoom());
		assertEquals(saleRoomTariff.getId(), saleRoomTariffFound.get().getId());

	}

	


}