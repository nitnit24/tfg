package es.udc.tfg.backend.model.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
	
		SaleRoom newSaleRoom = new SaleRoom(roomType.get().getId(), date, freeRooms, roomType.get());
		saleRoomDao.save(newSaleRoom);
		
		return newSaleRoom;
	
	}
	
	@Override
	public SaleRoom findByIdAndDate(Long id, Calendar date) throws InstanceNotFoundException {

		
		Optional<SaleRoom> saleRoom = saleRoomDao.findByIdAndDate(id, date);

		if (!saleRoom.isPresent()) {
			throw new InstanceNotFoundException("project.entities.saleRoom", id);
		}

		return saleRoom.get();

	}
//	
//	@Override
//	public SaleRoom updateSaleRoom(SaleRoom saleRoom) throws InstanceNotFoundException {
//
//		Optional<SaleRoom> existingSaleRoomItem = saleRoomDao.findByIdRoomTypeAndDate(saleRoom.getIdRoomType(),
//													saleRoom.getDate());
//
//		if (!existingSaleRoomItem.isPresent()) {
//			throw new InstanceNotFoundException("project.entities.saleRoom", saleRoom.getIdRoomType());
//		}
//
//		existingSaleRoomItem.get().setFreeRooms(saleRoom.getFreeRooms());
//
//		saleRoomDao.save(existingSaleRoomItem.get());
//
//		return existingSaleRoomItem.get();
//
//	}
	
	@Override
	public List<Calendar> findDate()  {

		List<Calendar> calendarList = new ArrayList<>();
		
		Calendar today = Calendar.getInstance();
		
		for (int i = 1; i <= 15; i++) {
			  calendarList.add(today);
			  today.add(Calendar.DAY_OF_YEAR,1);
			}
		
		return calendarList;
	}

}