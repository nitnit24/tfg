package es.udc.tfg.backend.rest.controllers;

import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomType;
import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomTypeDto;
import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomTypeDtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.services.RoomTypeService;
import es.udc.tfg.backend.rest.dtos.RoomTypeDto;

@RestController
@RequestMapping("/roomTypes")
public class RoomTypeController {

	@Autowired
	private RoomTypeService roomTypeService;

	@PostMapping("/addRoomType")
	public RoomTypeDto addRoomType(@Validated({ RoomTypeDto.AllValidations.class }) @RequestBody RoomTypeDto roomTypeDto)
			throws DuplicateInstanceException {
		return toRoomTypeDto(roomTypeService.addRoomType(toRoomType(roomTypeDto)));
	}
	
	@GetMapping("/roomTypes")
	public List<RoomTypeDto> findAllRoomTypes() {
		return toRoomTypeDtos(roomTypeService.findAllRoomTypes());
	}
	

	@PutMapping("/{id}")
	public RoomTypeDto updateRoomType(@PathVariable("id") Long id,
			 @RequestBody RoomTypeDto roomTypeDto) 
					throws InstanceNotFoundException {
		return toRoomTypeDto(roomTypeService.updateRoomType(toRoomType(roomTypeDto)));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeRoomType(@PathVariable("id") Long id) throws InstanceNotFoundException {
		roomTypeService.removeRoomType(id);
	}

	@GetMapping("/{id}")
	public RoomTypeDto findRoomTypeById(@PathVariable("id") Long id) throws InstanceNotFoundException 	 {
		return toRoomTypeDto(roomTypeService.findRoomTypeById(id));
	}
}
