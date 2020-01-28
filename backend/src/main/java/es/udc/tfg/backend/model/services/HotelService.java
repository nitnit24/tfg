package es.udc.tfg.backend.model.services;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.Hotel;

public interface HotelService {
	
	void signUp(Hotel hotel) throws DuplicateInstanceException;
	
	Hotel login(String hotelName, String password) throws IncorrectLoginException;
	
	Hotel loginFromId(Long id) throws InstanceNotFoundException;
	
	Hotel updateProfile(Long id, String image, String hotelName, String address, String email, String phone) throws InstanceNotFoundException;
	
	void changePassword(Long id, String oldPassword, String newPassword)
		throws InstanceNotFoundException, IncorrectPasswordException;

}
