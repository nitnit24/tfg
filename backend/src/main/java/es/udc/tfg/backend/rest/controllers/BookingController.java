package es.udc.tfg.backend.rest.controllers;


import static es.udc.tfg.backend.rest.dtos.BookingConversor.toBookingDto;
import static es.udc.tfg.backend.rest.dtos.BookingConversor.toBookingSummaryDtos;
import static es.udc.tfg.backend.rest.dtos.RoomTypeConversor.toRoomTypeDtos;
import static es.udc.tfg.backend.rest.dtos.SaleRoomTariffConversor.toSaleRoomTariffDtos;
import static es.udc.tfg.backend.rest.dtos.TariffConversor.toTariffDtos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.Booking;
import es.udc.tfg.backend.model.services.Block;
import es.udc.tfg.backend.model.services.BookingService;
import es.udc.tfg.backend.model.services.IncorrectFindLocatorKeyException;
import es.udc.tfg.backend.rest.dtos.BlockDto;
import es.udc.tfg.backend.rest.dtos.BookingDto;
import es.udc.tfg.backend.rest.dtos.BookingParamsDto;
import es.udc.tfg.backend.rest.dtos.BookingSummaryDto;
import es.udc.tfg.backend.rest.dtos.RoomTypeDto;
import es.udc.tfg.backend.rest.dtos.SaleRoomTariffDto;
import es.udc.tfg.backend.rest.dtos.TariffDto;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	

	@PostMapping("/makeBooking")
	public BookingDto makeBooking(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@Validated @RequestBody BookingParamsDto params
			) throws InstanceNotFoundException{
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		return toBookingDto(bookingService.makeBooking(params.getBookingRoomSummarys(), startCalendar, endCalendar, params.getName(), params.getSurname(), 
				params.getPhone(), params.getEmail(), params.getPetition()));
	}
	

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
	
	@GetMapping("/findSaleRoomTariffsByFreeRoom")
	public List<SaleRoomTariffDto> findSaleRoomTariffsByFreeRoom(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam Long tariffId,
			@RequestParam Long roomTypeId){
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		return toSaleRoomTariffDtos(bookingService.findSaleRoomTariffsByFreeRoom(startCalendar, endCalendar, roomTypeId, tariffId));
	}

	@GetMapping("/{locator}")
	public BookingDto findBookingByLocator(@PathVariable("locator") String locator) throws InstanceNotFoundException {
		return toBookingDto(bookingService.findByLocator(locator));
	}

	@GetMapping("/{locator}/find")
	public BookingDto findBookingByLocatorAndKey(@PathVariable("locator") String locator, @RequestParam String key)
			throws IncorrectFindLocatorKeyException {
		return toBookingDto(bookingService.findByLocatorAndKey(locator, key));
	}

	@PutMapping("/{locator}/cancel")
	public BookingDto cancelBooking(@PathVariable("locator") String locator,
			@RequestParam String key)	
			throws InstanceNotFoundException {
		return toBookingDto(bookingService.cancel(locator, key));
	}
	
	@GetMapping("/bookings")
	public BlockDto<BookingSummaryDto> findBookings(
		@RequestParam(required=true) String dateType,
		@RequestParam("minDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDate,
		@RequestParam("maxDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDate,
		@RequestParam(required=false) String keywords, 
		@RequestParam(defaultValue="0") int page) {
		
		Calendar minCalendar = Calendar.getInstance();
		minCalendar.setTime(minDate);
		Calendar maxCalendar = Calendar.getInstance();
		maxCalendar.setTime(maxDate);
		
		Block<Booking> bookingBlock = bookingService.findBookings(dateType, minCalendar, maxCalendar, keywords, page, 10);
		
		return new BlockDto<>(toBookingSummaryDtos(bookingBlock.getItems()), bookingBlock.getExistMoreItems());
		
	}

}
