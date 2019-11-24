package es.udc.tfg.backend.rest.controllers;


import static es.udc.tfg.backend.rest.dtos.SaleRoomConversor.toSaleRoomDto;
import static es.udc.tfg.backend.rest.dtos.SaleRoomTariffConversor.toSaleRoomTariffDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
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
	public SaleRoomDto findSaleRoom(@Validated @RequestBody FindToSaleRoomParamsDto params) throws InstanceNotFoundException {
		return toSaleRoomDto(saleRoomService.findByRoomTypeIdAndDate(params.getIdRoomType(), params.getDate()));
	}
	
	@PostMapping("/addSaleRoomTariff")
	public SaleRoomTariffDto addSaleRoomTariff(@Validated @RequestBody AddToSaleRoomTariffParamsDto params) throws InstanceNotFoundException, DuplicateInstanceException {
		return toSaleRoomTariffDto(saleRoomTariffService.uploadSaleRoomTariff(params.getPrice(), params.getTariffId(), params.getRoomTypeId(), params.getDate()));
	}
	
	@GetMapping("/findSaleRoomTariff")
	public SaleRoomTariffDto findSaleRoomTariff(@Validated @RequestBody FindToSaleRoomTariffParamsDto params) throws InstanceNotFoundException {
		return toSaleRoomTariffDto(saleRoomTariffService.findByTariffIdAnRoomTypeIdAndDate(params.getTariffId(), params.getRoomTypeId(), params.getDate()));
	} 
	

}
