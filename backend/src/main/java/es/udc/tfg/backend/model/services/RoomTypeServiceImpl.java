package es.udc.tfg.backend.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.RoomTypeDao;

@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	private RoomTypeDao roomTypeDao;

	@Override
	public RoomType addRoomType(RoomType roomType) throws DuplicateInstanceException {

		if (roomTypeDao.existsByName(roomType.getName())) {
			throw new DuplicateInstanceException("project.entities.roomType", roomType.getName());
		}

		roomTypeDao.save(roomType);

		return roomType;

	}
	
	@Override
	public RoomType findRoomTypeById(Long id) throws InstanceNotFoundException {

		Optional<RoomType> roomType = roomTypeDao.findById(id);

		if (!roomType.isPresent()) {
			throw new InstanceNotFoundException("project.entities.roomType", id);
		}

		return roomType.get();

	}
	
	@Override
	public RoomType updateRoomType(RoomType roomType) throws InstanceNotFoundException {

		Optional<RoomType> existingRoomTypeItem = roomTypeDao.findById(roomType.getId());

		if (!existingRoomTypeItem.isPresent()) {
			throw new InstanceNotFoundException("project.entities.roomType", roomType.getId());
		}

		existingRoomTypeItem.get().setName(roomType.getName());
		existingRoomTypeItem.get().setDescription(roomType.getDescription());
		existingRoomTypeItem.get().setCapacity(roomType.getCapacity());
		existingRoomTypeItem.get().setQuantity(roomType.getQuantity());
		existingRoomTypeItem.get().setMinPrice(roomType.getMinPrice());
		existingRoomTypeItem.get().setMaxPrice(roomType.getMaxPrice());
		roomTypeDao.save(existingRoomTypeItem.get());

		return existingRoomTypeItem.get();

	}
	
	@Override
	public void removeRoomType(Long roomTypeId) throws InstanceNotFoundException {
		Optional<RoomType> existingRoomTypeItem = roomTypeDao.findById(roomTypeId);

		if (!existingRoomTypeItem.isPresent()) {
			throw new InstanceNotFoundException("project.entities.roomType", roomTypeId);
		}

		roomTypeDao.delete(existingRoomTypeItem.get());
	}
	
	@Override
	public List<RoomType> findAllRoomTypes() {

		Iterable<RoomType> roomTypes = roomTypeDao.findAll(new Sort(Sort.Direction.ASC, "id"));
		List<RoomType> roomTypesAsList = new ArrayList<>();

		roomTypes.forEach(c -> roomTypesAsList.add(c));

		return roomTypesAsList;
	}

}