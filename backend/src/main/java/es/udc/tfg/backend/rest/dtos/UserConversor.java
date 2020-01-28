package es.udc.tfg.backend.rest.dtos;

import es.udc.tfg.backend.model.entities.User;

public class UserConversor {
	
	private UserConversor() {}
	
	public final static UserDto toUserDto(User user) {
		return new UserDto(user.getId(), user.getUserName(), user.getImage(), user.getHotelName(), user.getAddress(), user.getEmail(), 
			user.getPhone(), user.getRole().toString());
	}
	
	public final static User toUser(UserDto userDto) {
		
		return new User(userDto.getUserName(), userDto.getPassword(), userDto.getImage(), userDto.getHotelName(), userDto.getAddress(),
			userDto.getEmail(), userDto.getPhone());
	}
	
	public final static AuthenticatedUserDto toAuthenticatedUserDto(String serviceToken, User user) {
		
		return new AuthenticatedUserDto(serviceToken, toUserDto(user));
		
	}

}
