package es.udc.tfg.backend.model.services;

import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.Hotel;

public interface PermissionChecker {
	
	public void checkUserExists(Long userId) throws InstanceNotFoundException;
	
	public Hotel checkUser(Long userId) throws InstanceNotFoundException;
	
}
