package es.udc.tfg.backend.rest.controllers;


import static es.udc.tfg.backend.rest.dtos.SaleRoomConversor.toSaleRoomDto;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.services.RoomTypeService;
import es.udc.tfg.backend.model.services.SaleRoomService;
import es.udc.tfg.backend.rest.dtos.AddToSaleRoomParamsDto;
import es.udc.tfg.backend.rest.dtos.RoomTypeDto;
import es.udc.tfg.backend.rest.dtos.SaleRoomDto;

@RestController
@RequestMapping("/dailyPanel")
public class DailyPanelController {

	@Autowired
	private SaleRoomService saleRoomService;

	@PostMapping("/addSaleRoom")
	public SaleRoomDto addSaleRoom(@Validated @RequestBody AddToSaleRoomParamsDto params) throws InstanceNotFoundException, DuplicateInstanceException {
		return toSaleRoomDto(saleRoomService.addSaleRoom(params.getIdRoomType(), params.getDate(), params.getFreeRooms()));
	}
	
	

}
