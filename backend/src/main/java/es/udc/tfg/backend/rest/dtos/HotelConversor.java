package es.udc.tfg.backend.rest.dtos;

import es.udc.tfg.backend.model.entities.Hotel;

public class HotelConversor {
	
	private HotelConversor() {}
	
	public final static HotelDto toHotelDto(Hotel user) {
		return new HotelDto(user.getId(), user.getUserName(), user.getImage(), user.getHotelName(), user.getAddress(), user.getEmail(), 
			user.getPhone(), user.getRole().toString());
	}
	
	public final static Hotel toHotel(HotelDto userDto) {
		
		return new Hotel(userDto.getUserName(), userDto.getPassword(), userDto.getImage(), userDto.getHotelName(), userDto.getAddress(),
			userDto.getEmail(), userDto.getPhone());
	}
	
	public final static AuthenticatedUserDto toAuthenticatedUserDto(String serviceToken, Hotel user) {
		
		return new AuthenticatedUserDto(serviceToken, toHotelDto(user));
		
	}

}
