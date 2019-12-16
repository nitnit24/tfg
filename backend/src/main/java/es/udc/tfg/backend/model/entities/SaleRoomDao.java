package es.udc.tfg.backend.model.entities;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SaleRoomDao extends PagingAndSortingRepository<SaleRoom, Long> {

	Optional<SaleRoom> findByRoomTypeIdAndDate(Long rooomType, Calendar date);
	
	Optional<List<SaleRoom>> findByDateAndFreeRoomsGreaterThan(Calendar date, int rooms );
	
	Optional<List<SaleRoom>> findByDate(Calendar date);
}