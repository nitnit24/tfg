package es.udc.tfg.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReserveItemDao extends PagingAndSortingRepository<ReserveItem, Long> {

	boolean existsByName(String name);
	
}
