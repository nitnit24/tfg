package es.udc.tfg.backend.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.Hotel;
import es.udc.tfg.backend.model.entities.HotelDao;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private PermissionChecker permissionChecker;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private HotelDao userDao;
	
	@Override
	public void signUp(Hotel hotel) throws DuplicateInstanceException {
		
		if (userDao.existsByUserName(hotel.getUserName())) {
			throw new DuplicateInstanceException("project.entities.user", hotel.getUserName());
		}
			
		hotel.setPassword(passwordEncoder.encode(hotel.getPassword()));
		hotel.setRole(Hotel.RoleType.USER);
		
		userDao.save(hotel);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Hotel login(String userName, String password) throws IncorrectLoginException {
		
		Optional<Hotel> user = userDao.findByUserName(userName);
		
		if (!user.isPresent()) {
			throw new IncorrectLoginException(userName, password);
		}
		
//		if (!passwordEncoder.matches(password, user.get().getPassword())) {
//			throw new IncorrectLoginException(userName, password);
//		}
		
		return user.get();
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Hotel loginFromId(Long id) throws InstanceNotFoundException {
		return permissionChecker.checkUser(id);
	}

	@Override
	public Hotel updateProfile(Long id, String image, String hotelName, String address, String email, String phone) throws InstanceNotFoundException {
		
		Hotel user = permissionChecker.checkUser(id);
		
		user.setImage(image);
		user.setHotelName(hotelName);
		user.setAddress(address);
		user.setEmail(email);
		user.setPhone(phone);
		
		return user;

	}

	@Override
	public void changePassword(Long id, String oldPassword, String newPassword)
		throws InstanceNotFoundException, IncorrectPasswordException {
		
		Hotel user = permissionChecker.checkUser(id);
		
		if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
			throw new IncorrectPasswordException();
		} else {
			user.setPassword(passwordEncoder.encode(newPassword));
		}
		
	}

}
