package es.udc.tfg.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.User;
import es.udc.tfg.backend.model.services.IncorrectLoginException;
import es.udc.tfg.backend.model.services.IncorrectPasswordException;
import es.udc.tfg.backend.model.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {
	
	private final Long NON_EXISTENT_ID = new Long(-1);
	
	@Autowired
	private UserService userService;
	
	private User createUser(String userName) {
		return new User(userName, "password", "hotelName", "address", userName + "@" + userName + ".com", "666666666");
	}
	
	@Test
	public void testSignUpAndLoginFromId() throws DuplicateInstanceException, InstanceNotFoundException {
		
		User user = createUser("user");
		
		userService.signUp(user);
		
		User loggedInUser = userService.loginFromId(user.getId());
		
		assertEquals(user, loggedInUser);
		assertEquals(User.RoleType.USER, user.getRole());
		
	}
	
	@Test(expected = DuplicateInstanceException.class)
	public void testSignUpDuplicatedUserName() throws DuplicateInstanceException {
		
		User user = createUser("user");
		
		userService.signUp(user);
		userService.signUp(user);
		
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testloginFromNonExistentId() throws InstanceNotFoundException {		
		userService.loginFromId(NON_EXISTENT_ID);
	}
	
	@Test
	public void testLogin() throws DuplicateInstanceException, IncorrectLoginException {
		
		User user = createUser("user");
		String clearPassword = user.getPassword();
				
		userService.signUp(user);
		
		User loggedInUser = userService.login(user.getUserName(), clearPassword);
		
		assertEquals(user, loggedInUser);
		
	}
	
	@Test(expected = IncorrectLoginException.class)
	public void testLoginWithIncorrectPassword() throws DuplicateInstanceException, IncorrectLoginException {
		
		User user = createUser("user");
		String clearPassword = user.getPassword();
		
		userService.signUp(user);
		userService.login(user.getUserName(), 'X' + clearPassword);
		
	}
	
	@Test(expected = IncorrectLoginException.class)
	public void testLoginWithNonExistentUserName() throws IncorrectLoginException {
		userService.login("X", "Y");
	}
	
	@Test
	public void testUpdateProfile() throws InstanceNotFoundException, DuplicateInstanceException {
		
		User user = createUser("user");
		
		userService.signUp(user);
		
		user.setHotelName('X' + user.getHotelName());
		user.setAddress('X' + user.getAddress());
		user.setEmail('X' + user.getEmail());
		user.setPhone('X' + user.getPhone());
		
		userService.updateProfile(user.getId(), 'X' + user.getHotelName(), 'X' + user.getAddress(),
			'X' + user.getEmail(), 'X' + user.getPhone());
		
		User updatedUser = userService.loginFromId(user.getId());
		
		assertEquals(user, updatedUser);
		
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testUpdateProfileWithNonExistentId() throws InstanceNotFoundException {		
		userService.updateProfile(NON_EXISTENT_ID, "X", "X", "X", "X");
	}
	
	@Test
	public void testChangePassword() throws DuplicateInstanceException, InstanceNotFoundException,
		IncorrectPasswordException, IncorrectLoginException {
		
		User user = createUser("user");
		String oldPassword = user.getPassword();
		String newPassword = 'X' + oldPassword;
		
		userService.signUp(user);
		userService.changePassword(user.getId(), oldPassword, newPassword);
		userService.login(user.getUserName(), newPassword);
		
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testChangePasswordWithNonExistentId() throws InstanceNotFoundException, IncorrectPasswordException {
		userService.changePassword(NON_EXISTENT_ID, "X", "Y");
	}
	
	@Test(expected = IncorrectPasswordException.class)
	public void testChangePasswordWithIncorrectPassword() throws DuplicateInstanceException, InstanceNotFoundException,
		IncorrectPasswordException {
		
		User user = createUser("user");
		String oldPassword = user.getPassword();
		String newPassword = 'X' + oldPassword;
		
		userService.signUp(user);
		userService.changePassword(user.getId(), 'Y' + oldPassword, newPassword);
		
	}

}
