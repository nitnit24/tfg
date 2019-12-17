package es.udc.tfg.backend.rest.controllers;


import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomTypeDtos;
import static es.udc.tfg.backend.rest.dtos.TariffConversor.toTariffDtos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.tfg.backend.model.services.BookingService;
import es.udc.tfg.backend.rest.dtos.RoomTypeDto;
import es.udc.tfg.backend.rest.dtos.TariffDto;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	

	@GetMapping("/findFreeRooms")
	public List<RoomTypeDto> findFreeRooms(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam int people , 
			@RequestParam int rooms){
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		return toRoomTypeDtos(bookingService.findFreeRooms(startCalendar, endCalendar, people, rooms));
	}
	
	@GetMapping("/findTariffsByFreeRoom")
	public List<TariffDto> findTariffsByFreeRoom(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam Long roomTypeId){
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		return toTariffDtos(bookingService.findTariffsByFreeRoom(startCalendar, endCalendar, roomTypeId));
	}

}
