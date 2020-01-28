package es.udc.tfg.backend.model.services;

import java.util.List;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.Tariff;

public interface TariffService {

	Tariff addTariff(Long hotelId, Tariff tariff) throws DuplicateInstanceException, InstanceNotFoundException;

	Tariff updateTariff(Long hotelId, Tariff tariff) throws InstanceNotFoundException, PermissionException;

	void removeTariff(Long hotelId, Long tariffId) throws InstanceNotFoundException, PermissionException;

	Tariff findTariffById(Long id) throws InstanceNotFoundException;

	List<Tariff> findAllTariffs();
}
