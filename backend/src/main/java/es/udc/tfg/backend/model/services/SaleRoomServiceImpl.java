package es.udc.tfg.backend.model.services;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.RoomTypeDao;
import es.udc.tfg.backend.model.entities.SaleRoom;
import es.udc.tfg.backend.model.entities.SaleRoomDao;

@Service
@Transactional
public class SaleRoomServiceImpl implements SaleRoomService {

	@Autowired
	private SaleRoomDao saleRoomDao;
	
	@Autowired
	private RoomTypeDao roomTypeDao;

	@Override
	public SaleRoom addSaleRoom(Long roomTypeId, Calendar date, int freeRooms) throws DuplicateInstanceException, InstanceNotFoundException {

		Optional<RoomType> roomType = roomTypeDao.findById(roomTypeId);

		if (!roomType.isPresent()) {
			throw new InstanceNotFoundException("project.entities.roomType", roomTypeId);
		}
		
		Optional<SaleRoom> saleRoom = saleRoomDao.findByRoomTypeIdAndDate(roomTypeId, date);
		
		if(!saleRoom.isPresent()) {
			SaleRoom newSaleRoom = new SaleRoom(date, freeRooms, roomType.get());
			
			saleRoomDao.save(newSaleRoom);
			
			return newSaleRoom;
			
		}else {
			saleRoom.get().setFreeRooms(freeRooms);
			saleRoomDao.save(saleRoom.get());
			
			return saleRoom.get();
		}
		
		
	}
	
	
	@Override
	public SaleRoom findByRoomTypeIdAndDate(Long idRoomType, Calendar date) throws InstanceNotFoundException {
		
		Optional<SaleRoom> saleRoom = saleRoomDao.findByRoomTypeIdAndDate(idRoomType, date);

		if (!saleRoom.isPresent()) {
			throw new InstanceNotFoundException("project.entities.roomType", idRoomType);
		}

		return saleRoom.get();

	}



}