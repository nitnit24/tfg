package es.udc.tfg.backend.model.services;

import java.util.List;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.RoomType;

public interface RoomTypeService {

	RoomType addRoomType(RoomType roomType) throws DuplicateInstanceException;
	
	RoomType findRoomTypeById(Long id) throws InstanceNotFoundException;

	RoomType updateRoomType(RoomType roomType) throws InstanceNotFoundException;

	void removeRoomType(Long roomTypeId) throws InstanceNotFoundException;

	List<RoomType> findAllRoomTypes();

}