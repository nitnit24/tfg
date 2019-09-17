package es.udc.tfg.backend.model.services;

import java.util.List;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.Tariff;

public interface TariffService {

	Tariff addTariff(Tariff tariff) throws DuplicateInstanceException;

	Tariff updateTariff(Tariff tariff) throws InstanceNotFoundException;

	void removeTariff(Long tariffId) throws InstanceNotFoundException;

	Tariff findTariffById(Long id) throws InstanceNotFoundException;

	List<Tariff> findAllTariffs();
}
