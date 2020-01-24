package es.udc.tfg.backend.rest.controllers;

import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomType;
import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomTypeDto;
import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomTypeDtos;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.services.PermissionException;
import es.udc.tfg.backend.model.services.RoomTypeService;
import es.udc.tfg.backend.rest.dtos.RoomTypeDto;

@RestController
@RequestMapping("/roomTypes")
public class RoomTypeController {

	@Autowired
	private RoomTypeService roomTypeService;

	@PostMapping("/addRoomType")
	public RoomTypeDto addRoomType(@Validated({ RoomTypeDto.AllValidations.class })  @RequestBody RoomTypeDto roomTypeDto,
			@RequestAttribute Long userId)
			throws DuplicateInstanceException, InstanceNotFoundException {
		return toRoomTypeDto(roomTypeService.addRoomType(userId, toRoomType(roomTypeDto)));
	}

	    
	@GetMapping("/roomTypes")
	public List<RoomTypeDto> findAllRoomTypes() {
		return toRoomTypeDtos(roomTypeService.findAllRoomTypes());
	}
	

	@PutMapping("/{id}")
	public RoomTypeDto updateRoomType(@PathVariable("id") Long id,
			 @RequestBody RoomTypeDto roomTypeDto,
			 @RequestAttribute Long userId) 
					throws InstanceNotFoundException, PermissionException {
		return toRoomTypeDto(roomTypeService.updateRoomType(userId, toRoomType(roomTypeDto)));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeRoomType(@PathVariable("id") Long id,  @RequestAttribute Long userId)
			throws InstanceNotFoundException, PermissionException {
		roomTypeService.removeRoomType(userId, id);
	}

	@GetMapping("/{id}")
	public RoomTypeDto findRoomTypeById(@PathVariable("id") Long id) throws InstanceNotFoundException 	 {
		return toRoomTypeDto(roomTypeService.findRoomTypeById(id));
	}
}
