package es.udc.tfg.backend.model.entities;

import java.util.Calendar;

import org.springframework.data.domain.Slice;

public interface CustomizedBookingDao {
	
	Slice<Booking> find(Calendar minDate, Calendar maxDate, String text, int page, int size);

}
