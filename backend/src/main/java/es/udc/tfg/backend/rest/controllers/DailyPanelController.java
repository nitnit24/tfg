package es.udc.tfg.backend.rest.controllers;


import static es.udc.tfg.backend.rest.dtos.RoomTableConversor.toRoomTableDtos;
import static es.udc.tfg.backend.rest.dtos.SaleRoomConversor.toSaleRoomDto;
import static es.udc.tfg.backend.rest.dtos.SaleRoomTariffConversor.toSaleRoomTariffDto;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.services.DailyPanelService;
import es.udc.tfg.backend.model.services.FreeRoomsLessThanRoomTypeQuantityException;
import es.udc.tfg.backend.model.services.PriceNotBetweenMinAndMaxValueException;
import es.udc.tfg.backend.rest.dtos.AddToSaleRoomParamsDto;
import es.udc.tfg.backend.rest.dtos.AddToSaleRoomTariffParamsDto;
import es.udc.tfg.backend.rest.dtos.RoomTableDto;
import es.udc.tfg.backend.rest.dtos.SaleRoomDto;
import es.udc.tfg.backend.rest.dtos.SaleRoomTariffDto;

@RestController
@RequestMapping("/dailyPanel")
public class DailyPanelController {

	@Autowired
	private DailyPanelService dailyPanelService;

	@GetMapping("/findDailyPanel")
	public List<RoomTableDto> findSaleRoom( 
			@RequestParam Long date)
					throws InstanceNotFoundException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return toRoomTableDtos(dailyPanelService.findDailyPanel(calendar));
	}
	
	@PostMapping("/addSaleRoom")
	public SaleRoomDto addSaleRoom(@Validated @RequestBody AddToSaleRoomParamsDto params) throws InstanceNotFoundException, DuplicateInstanceException, FreeRoomsLessThanRoomTypeQuantityException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(params.getDate());
		return toSaleRoomDto(dailyPanelService.addSaleRoom(params.getIdRoomType(), calendar, params.getFreeRooms()));
	}
	
	
	@PostMapping("/uploadSaleRoomTariff")
	public SaleRoomTariffDto uploadSaleRoomTariff(@Validated @RequestBody AddToSaleRoomTariffParamsDto params) throws InstanceNotFoundException, DuplicateInstanceException, PriceNotBetweenMinAndMaxValueException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(params.getDate());
		return toSaleRoomTariffDto(dailyPanelService.uploadSaleRoomTariff(params.getPrice(), params.getTariffId(), params.getRoomTypeId(), calendar));
	}
	

}
