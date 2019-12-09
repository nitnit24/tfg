package es.udc.tfg.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReserveDao extends PagingAndSortingRepository<Reserve, Long> {

	boolean existsByName(String name);
	
}