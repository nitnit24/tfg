package es.udc.tfg.backend.rest.controllers;

import static es.udc.tfg.backend.rest.dtos.HotelConversor.toAuthenticatedUserDto;
import static es.udc.tfg.backend.rest.dtos.HotelConversor.toHotel;
import static es.udc.tfg.backend.rest.dtos.HotelConversor.toHotelDto;

import java.net.URI;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.Hotel;
import es.udc.tfg.backend.model.services.IncorrectLoginException;
import es.udc.tfg.backend.model.services.IncorrectPasswordException;
import es.udc.tfg.backend.model.services.PermissionException;
import es.udc.tfg.backend.model.services.HotelService;
import es.udc.tfg.backend.rest.common.ErrorsDto;
import es.udc.tfg.backend.rest.common.JwtGenerator;
import es.udc.tfg.backend.rest.common.JwtInfo;
import es.udc.tfg.backend.rest.dtos.AuthenticatedUserDto;
import es.udc.tfg.backend.rest.dtos.ChangePasswordParamsDto;
import es.udc.tfg.backend.rest.dtos.LoginParamsDto;
import es.udc.tfg.backend.rest.dtos.HotelDto;

@RestController
@RequestMapping("/users")
public class HotelController {
	
	private final static String INCORRECT_LOGIN_EXCEPTION_CODE = "project.exceptions.IncorrectLoginException";
	private final static String INCORRECT_PASSWORD_EXCEPTION_CODE = "project.exceptions.IncorrectPasswordException";
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	@Autowired
	private HotelService hotelService;
	
	@ExceptionHandler(IncorrectLoginException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleIncorrectLoginException(IncorrectLoginException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(INCORRECT_LOGIN_EXCEPTION_CODE, null,
				INCORRECT_LOGIN_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
		
	}
	
	@ExceptionHandler(IncorrectPasswordException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleIncorrectPasswordException(IncorrectPasswordException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(INCORRECT_PASSWORD_EXCEPTION_CODE, null,
				INCORRECT_PASSWORD_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
		
	}

//	@PostMapping("/signUp")
//	public ResponseEntity<AuthenticatedUserDto> signUp(
//		@Validated({UserDto.AllValidations.class}) @RequestBody UserDto userDto) throws DuplicateInstanceException {
//		
//		Hotel user = toUser(userDto);
//		
//		userService.signUp(user);
//		
//		URI location = ServletUriComponentsBuilder
//			.fromCurrentRequest().path("/{id}")
//			.buildAndExpand(user.getId()).toUri();
//	
//		return ResponseEntity.created(location).body(toAuthenticatedUserDto(generateServiceToken(user), user));
//
//	}
	
	@PostMapping("/login")
	public AuthenticatedUserDto login(@Validated @RequestBody LoginParamsDto params)
		throws IncorrectLoginException {
		
		Hotel hotel = hotelService.login(params.getUserName(), params.getPassword());
			
		return toAuthenticatedUserDto(generateServiceToken(hotel), hotel);
		
	}
	
	@PostMapping("/loginFromServiceToken")
	public AuthenticatedUserDto loginFromServiceToken(@RequestAttribute Long userId, 
		@RequestAttribute String serviceToken) throws InstanceNotFoundException {
		
		Hotel hotel = hotelService.loginFromId(userId);
		
		return toAuthenticatedUserDto(serviceToken, hotel);
		
	}

	@PutMapping("/{id}")
	public HotelDto updateProfile(@RequestAttribute Long userId, @PathVariable("id") Long id, 
		@Validated({HotelDto.UpdateValidations.class}) @RequestBody HotelDto hotelDto) 
		throws InstanceNotFoundException, PermissionException {
				
		if (!id.equals(userId)) {
			throw new PermissionException();
		}
		
		return toHotelDto(hotelService.updateProfile(id, hotelDto.getImage(), hotelDto.getHotelName(), hotelDto.getAddress(),
			hotelDto.getEmail(), hotelDto.getPhone()));
		
	}
	
	@PostMapping("/{id}/changePassword")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(@RequestAttribute Long userId, @PathVariable Long id,
		@Validated @RequestBody ChangePasswordParamsDto params)
		throws PermissionException, InstanceNotFoundException, IncorrectPasswordException {
		
		if (!id.equals(userId)) {
			throw new PermissionException();
		}
		
		hotelService.changePassword(id, params.getOldPassword(), params.getNewPassword());
		
	}
	
	private String generateServiceToken(Hotel user) {
		
		JwtInfo jwtInfo = new JwtInfo(user.getId(), user.getUserName(), user.getRole().toString());
		
		return jwtGenerator.generate(jwtInfo);
		
	}
	
}
