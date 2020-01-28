package es.udc.tfg.backend.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticatedUserDto {
	
	private String serviceToken;
	private HotelDto userDto;

	public AuthenticatedUserDto() {}
	
	public AuthenticatedUserDto(String serviceToken, HotelDto userDto) {
		
		this.serviceToken = serviceToken;
		this.userDto = userDto;
		
	}

	public String getServiceToken() {
		return serviceToken;
	}

	public void setServiceToken(String serviceToken) {
		this.serviceToken = serviceToken;
	}

	@JsonProperty("user")
	public HotelDto getUserDto() {
		return userDto;
	}

	public void setUserDto(HotelDto userDto) {
		this.userDto = userDto;
	}

}
