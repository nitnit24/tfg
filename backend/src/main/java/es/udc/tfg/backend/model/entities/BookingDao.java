package es.udc.tfg.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingDao extends PagingAndSortingRepository<Booking, Long>, CustomizedBookingDao {

	Optional<Booking> findByLocatorAndKey(String locator, String key);
	
	Optional<Booking> findByLocator(String locator);
	
}