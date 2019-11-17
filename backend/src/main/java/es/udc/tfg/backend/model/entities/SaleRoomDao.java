package es.udc.tfg.backend.model.entities;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SaleRoomDao extends PagingAndSortingRepository<SaleRoom, Long> {

	Optional<SaleRoom> findByIdAndDate(Long rooomType, Calendar date);
	
}