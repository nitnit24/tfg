package es.udc.tfg.backend.rest.controllers;

import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomType;
import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomTypeDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.services.RoomTypeService;
import es.udc.tfg.backend.model.services.SaleRoomService;
import es.udc.tfg.backend.rest.dtos.RoomTypeDto;

@RestController
@RequestMapping("/dailyPanel")
public class DailyPanelController {

	@Autowired
	private SaleRoomService saleRoomService;

//	@PostMapping("/addSaleRoom")
//	public RoomTypeDto addSaleRoom(@Validated({ RoomTypeDto.AllValidations.class }) @RequestBody RoomTypeDto roomTypeDto)
//			throws DuplicateInstanceException {
//		return toRoomTypeDto(roomTypeService.addRoomType(toRoomType(roomTypeDto)));
//	}
}
