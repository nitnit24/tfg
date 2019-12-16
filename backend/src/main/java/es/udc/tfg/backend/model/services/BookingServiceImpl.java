package es.udc.tfg.backend.model.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.backend.model.entities.RoomType;
import es.udc.tfg.backend.model.entities.SaleRoom;
import es.udc.tfg.backend.model.entities.SaleRoomDao;
import es.udc.tfg.backend.model.entities.SaleRoomTariffDao;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

//	@Autowired
//	private BookingDao bookingDao;
//	
//	@Autowired
//	private BookingDayDao bookingDayDao;
//	
//	@Autowired
//	private BookingRoomDao bookingRoomDao;
	
	@Autowired
	private SaleRoomDao saleRoomDao;

	@Autowired
	private SaleRoomTariffDao saleRoomTariffDao;

	
//	@Override
//	public Booking addBooking(List<BookingRoom> bookingRooms, Calendar startDate, Calendar endDate, String name,
//			String surName, String phone, String email, String petition) throws InstanceNotFoundException {
//
//		for (BookingRoom bookingRoom : bookingRooms) {
//			List<BookingDay> bookingDays = bookingRoom.getBookingDays();
//			
//			for (BookingDay bookingDay: bookingDays) {
//				Long saleRoomTariffId = bookingDay.getSaleRoomTariff().getId();
//				
//				Optional<SaleRoomTariff> saleRoomTariff = saleRoomTariffDao.findById(saleRoomTariffId);
//
//				if (!saleRoomTariff.isPresent()) {
//					throw new InstanceNotFoundException("project.entities.saleRoomTariff", saleRoomTariffId);
//				}
//				else {
//					BookingDay newBookingDay = new BookingDay(saleRoomTariff.get().getPrice(), saleRoomTariff.get());
//					bookingDayDao.save(newBookingDay);
//				}
//			}
//			BookingRoom newBookingRoom= new BookingRoom(bookingDays);
//			bookingRoomDao.save(newBookingRoom);	
//		}
//
//		Calendar now = Calendar.getInstance();
//		
//		Booking newBooking = new Booking(bookingRooms, now, startDate, endDate, name, surName,
//				phone, email, petition);
//		
//		return newBooking;
//
//	}
	
	public List<RoomType> findFreeRoomTypes(Calendar startDate, Calendar endDate, int people, int rooms) {

		// diferents
		Optional<List<SaleRoom>> freeSaleRooms = saleRoomDao.findByDate(startDate);

		List<RoomType> freeRoomTypes = new ArrayList<>();


		for (SaleRoom freeSaleRoom : freeSaleRooms.get()) {
			
			Calendar date = Calendar.getInstance();
			date.setTime(startDate.getTime());

//			if (date.compareTo(endDate) == 0) {
//				
//				Optional<SaleRoom> saleRoom = saleRoomDao
//						.findByRoomTypeIdAndDate(freeSaleRoom.getRoomType().getId(), date);
//
//				freeRoomTypes.add(saleRoom.get().getRoomType());
//				
//			} else {
				while (date.compareTo(endDate) < 0) {
					Optional<SaleRoom> saleRoom = saleRoomDao
							.findByRoomTypeIdAndDate(freeSaleRoom.getRoomType().getId(), date);

					if (saleRoom.get().getFreeRooms() < rooms) {
						break;
					}
					else {
						date.add(Calendar.DAY_OF_YEAR, 1);
					}
					
					if (date.compareTo(endDate) == 0) {
						freeRoomTypes.add(saleRoom.get().getRoomType());
					}
//				}
			
			}
		}

		return freeRoomTypes;
	}


}