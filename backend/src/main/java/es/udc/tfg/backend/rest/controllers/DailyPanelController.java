package es.udc.tfg.backend.rest.controllers;


import static es.udc.tfg.backend.rest.dtos.SaleRoomConversor.toSaleRoomDto;
import static es.udc.tfg.backend.rest.dtos.SaleRoomTariffConversor.toSaleRoomTariffDto;
import static es.udc.tfg.backend.rest.dtos.RoomTableConversor.toRoomTableDtos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.services.PriceNotBetweenMinAndMaxValueException;
import es.udc.tfg.backend.model.services.SaleRoomService;
import es.udc.tfg.backend.model.services.SaleRoomTariffService;
import es.udc.tfg.backend.rest.dtos.AddToSaleRoomParamsDto;
import es.udc.tfg.backend.rest.dtos.AddToSaleRoomTariffParamsDto;
import es.udc.tfg.backend.rest.dtos.RoomTableDto;
import es.udc.tfg.backend.rest.dtos.SaleRoomDto;
import es.udc.tfg.backend.rest.dtos.SaleRoomTariffDto;

@RestController
@RequestMapping("/dailyPanel")
public class DailyPanelController {

	@Autowired
	private SaleRoomService saleRoomService;
	
	@Autowired
	private SaleRoomTariffService saleRoomTariffService;

	@GetMapping("/findDailyPanel")
	public List<RoomTableDto> findSaleRoom( 
			@RequestParam Long date)
					throws InstanceNotFoundException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return toRoomTableDtos(saleRoomTariffService.findDailyPanel(calendar));
	}
	
	@PostMapping("/addSaleRoom")
	public SaleRoomDto addSaleRoom(@Validated @RequestBody AddToSaleRoomParamsDto params) throws InstanceNotFoundException, DuplicateInstanceException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(params.getDate());
		return toSaleRoomDto(saleRoomService.addSaleRoom(params.getIdRoomType(), calendar, params.getFreeRooms()));
	}
	

	@GetMapping("/findSaleRoom")
	public SaleRoomDto findSaleRoom(
			@RequestParam Long roomTypeId , 
			@RequestParam Long date)
					throws InstanceNotFoundException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return toSaleRoomDto(saleRoomService.findByRoomTypeIdAndDate(roomTypeId, calendar));
	}
	
	@PostMapping("/uploadSaleRoomTariff")
	public SaleRoomTariffDto uploadSaleRoomTariff(@Validated @RequestBody AddToSaleRoomTariffParamsDto params) throws InstanceNotFoundException, DuplicateInstanceException, PriceNotBetweenMinAndMaxValueException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(params.getDate());
		return toSaleRoomTariffDto(saleRoomTariffService.uploadSaleRoomTariff(params.getPrice(), params.getTariffId(), params.getRoomTypeId(), calendar));
	}
	

	@GetMapping("/findSaleRoomTariff")
	public SaleRoomTariffDto findSaleRoomTariff(
			@RequestParam Long tariffId , 
			@RequestParam Long roomTypeId , 
			@RequestParam Long date) 
					throws InstanceNotFoundException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return toSaleRoomTariffDto(saleRoomTariffService.findByTariffIdAnRoomTypeIdAndDate(tariffId, roomTypeId, calendar));
	}

}
