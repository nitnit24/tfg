package es.udc.tfg.backend.rest.controllers;

import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomType;
import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomTypeDto;
import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomTypeDtos;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.services.PermissionException;
import es.udc.tfg.backend.model.services.PriceMinGreaterThanMaxValueException;
import es.udc.tfg.backend.model.services.RoomTypeService;
import es.udc.tfg.backend.rest.common.ErrorsDto;
import es.udc.tfg.backend.rest.dtos.RoomTypeDto;

@RestController
@RequestMapping("/roomTypes")
public class RoomTypeController {

	private final static String INCORRECT_PRICEMIN_GREATER_THAN_PRICEMAX = "project.exception.PriceMinGreaterThanMaxValueException";
	private final static String PERMISSION_EXCEPTION_CODE = "project.exception.PermissionException";
	@Autowired
	private MessageSource messageSource;
	
	
	@Autowired
	private RoomTypeService roomTypeService;

	@ExceptionHandler(PermissionException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorsDto handlePermissionExceptionException(PermissionException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(PERMISSION_EXCEPTION_CODE, null,
				PERMISSION_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
		
	}
	
	@ExceptionHandler(PriceMinGreaterThanMaxValueException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handlePriceMinGreaterThanMaxValueException(PriceMinGreaterThanMaxValueException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(INCORRECT_PRICEMIN_GREATER_THAN_PRICEMAX, null,
				INCORRECT_PRICEMIN_GREATER_THAN_PRICEMAX, locale);

		return new ErrorsDto(errorMessage);
		
	}
	
	@PostMapping("/addRoomType")
	public RoomTypeDto addRoomType(@Validated({ RoomTypeDto.AllValidations.class })  @RequestBody RoomTypeDto roomTypeDto,
			@RequestAttribute Long userId)
			throws DuplicateInstanceException, InstanceNotFoundException, PriceMinGreaterThanMaxValueException {
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
					throws InstanceNotFoundException, PermissionException, PriceMinGreaterThanMaxValueException {
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
