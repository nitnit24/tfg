package es.udc.tfg.backend.rest.controllers;


import static es.udc.tfg.backend.rest.dtos.SaleRoomConversor.toSaleRoomDto;
import static es.udc.tfg.backend.rest.dtos.SaleRoomTariffConversor.toSaleRoomTariffDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.services.PriceNotBetweenMinAndMaxValueException;
import es.udc.tfg.backend.model.services.SaleRoomService;
import es.udc.tfg.backend.model.services.SaleRoomTariffService;
import es.udc.tfg.backend.rest.dtos.AddToSaleRoomParamsDto;
import es.udc.tfg.backend.rest.dtos.AddToSaleRoomTariffParamsDto;
import es.udc.tfg.backend.rest.dtos.FindToSaleRoomParamsDto;
import es.udc.tfg.backend.rest.dtos.FindToSaleRoomTariffParamsDto;
import es.udc.tfg.backend.rest.dtos.SaleRoomDto;
import es.udc.tfg.backend.rest.dtos.SaleRoomTariffDto;

@RestController
@RequestMapping("/dailyPanel")
public class DailyPanelController {

	@Autowired
	private SaleRoomService saleRoomService;
	
	@Autowired
	private SaleRoomTariffService saleRoomTariffService;

	@PostMapping("/addSaleRoom")
	public SaleRoomDto addSaleRoom(@Validated @RequestBody AddToSaleRoomParamsDto params) throws InstanceNotFoundException, DuplicateInstanceException {
		return toSaleRoomDto(saleRoomService.addSaleRoom(params.getIdRoomType(), params.getDate(), params.getFreeRooms()));
	}
	

	@GetMapping("/findSaleRoom")
	public SaleRoomDto findSaleRoom(
			@RequestParam Long roomTypeId , 
			@RequestParam("date") 
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
					throws InstanceNotFoundException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return toSaleRoomDto(saleRoomService.findByRoomTypeIdAndDate(roomTypeId, calendar));
	}
	
	@PostMapping("/uploadSaleRoomTariff")
	public SaleRoomTariffDto uploadSaleRoomTariff(@Validated @RequestBody AddToSaleRoomTariffParamsDto params) throws InstanceNotFoundException, DuplicateInstanceException, PriceNotBetweenMinAndMaxValueException {
		return toSaleRoomTariffDto(saleRoomTariffService.uploadSaleRoomTariff(params.getPrice(), params.getTariffId(), params.getRoomTypeId(), params.getDate()));
	}
	

	@GetMapping("/findSaleRoomTariff")
	public SaleRoomTariffDto findSaleRoomTariff(
			@RequestParam Long tariffId , 
			@RequestParam Long roomTypeId , 
			@RequestParam("date") 
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
					throws InstanceNotFoundException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return toSaleRoomTariffDto(saleRoomTariffService.findByTariffIdAnRoomTypeIdAndDate(tariffId, roomTypeId, calendar));
	}

}
