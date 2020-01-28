package es.udc.tfg.backend.model.services;

import java.util.List;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.RoomType;

public interface RoomTypeService {

	RoomType addRoomType(Long hotelId, RoomType roomType) throws DuplicateInstanceException, InstanceNotFoundException;
	
	RoomType findRoomTypeById(Long id) throws InstanceNotFoundException;

	RoomType updateRoomType(Long hotelId, RoomType roomType) throws InstanceNotFoundException, PermissionException;

	void removeRoomType(Long hotelId, Long roomTypeId) throws InstanceNotFoundException, PermissionException;

	List<RoomType> findAllRoomTypes();

}