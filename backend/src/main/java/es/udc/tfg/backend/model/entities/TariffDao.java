package es.udc.tfg.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TariffDao extends PagingAndSortingRepository<Tariff, Long> {}
