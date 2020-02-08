package es.udc.tfg.backend.rest.dtos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import es.udc.tfg.backend.model.entities.Booking;
import es.udc.tfg.backend.model.entities.BookingDay;
import es.udc.tfg.backend.model.entities.BookingRoom;

public class BookingConversor {
	
	private BookingConversor() {}
	
	public final static List<BookingSummaryDto> toBookingSummaryDtos(List<Booking> bookings) {
		return bookings.stream().map(o -> toBookingSummaryDto(o)).collect(Collectors.toList());
	}
	
	public final static BookingDto toBookingDto(Booking booking) {
		
		List<BookingRoomDto> bookingRooms = booking.getBookingRooms().stream().map(i -> toBookingRoomDto(i)).collect(Collectors.toList());
		
		bookingRooms.sort(Comparator.comparing(BookingRoomDto::getId));
		
		return new BookingDto(booking.getId(), booking.getKey(), booking.getLocator(), bookingRooms, toMillis(booking.getDate()), 
				toMillis(booking.getStartDate()), booking.getDuration(), toMillis(booking.getEndDate()), booking.getState(), toMillis(booking.getCancelDate()), 
				toMillis(booking.getUpdateDate()), booking.getName(), booking.getSurName(),booking.getPhone(), booking.getEmail(), booking.getPetition(),
				booking.getTotalPrice());
		
	}
	
	private final static BookingSummaryDto toBookingSummaryDto(Booking booking) {
		List<BookingRoomDto> bookingRooms = booking.getBookingRooms().stream().map(i -> toBookingRoomDto(i)).collect(Collectors.toList());
		
		bookingRooms.sort(Comparator.comparing(BookingRoomDto::getId));
		
		 return new BookingSummaryDto(booking.getHotel().getId(), booking.getLocator(), bookingRooms, toMillis(booking.getDate()),  booking.getName(),
				 booking.getSurName(), toMillis(booking.getStartDate()),
				 toMillis(booking.getEndDate()), booking.getState(), booking.getTotalPrice());
		 
	}
	
	private final static BookingRoomDto toBookingRoomDto(BookingRoom bookingRoom) {

		List<BookingDayDto> bookingDays = bookingRoom.getBookingDays().stream().map(i -> toBookingDayDto(i)).collect(Collectors.toList());
		
		bookingDays.sort(Comparator.comparing(BookingDayDto::getDay));
		
		return new BookingRoomDto(bookingRoom.getId(), bookingRoom.getQuantity(), bookingRoom.getRoomTotalPrice(), bookingRoom.getRoomTypeName(),
				bookingRoom.getRoomTypeCapacity(), bookingRoom.getTariffName(), bookingRoom.getBooking().getId(),
				bookingDays);
		
	}
	
	private final static BookingDayDto toBookingDayDto(BookingDay bookingDay) {
		
		return new BookingDayDto(bookingDay.getId(),  bookingDay.getDayPrice(), toMillis(bookingDay.getDay()), bookingDay.getSaleRoomTariff().getId(),
				bookingDay.getBookingRoom().getId());
		
	}
	
	
	private final static Long toMillis(Calendar calendar) {
		if (calendar != null) {
	      TimeZone tz = calendar.getTimeZone();
	      ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
	      LocalDateTime date =  LocalDateTime.ofInstant(calendar.toInstant(), zid);
		return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
		}
		else {
			return null;
		}
	}
	


}
