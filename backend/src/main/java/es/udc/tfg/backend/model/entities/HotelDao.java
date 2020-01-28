package es.udc.tfg.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelDao extends PagingAndSortingRepository<Hotel, Long> {
	
	boolean existsByUserName(String userName);

	Optional<Hotel> findByUserName(String userName);
	
}
