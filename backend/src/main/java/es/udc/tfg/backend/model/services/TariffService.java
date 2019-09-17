package es.udc.tfg.backend.model.services;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.Tariff;

public interface TariffService {

	Tariff addTariff(String tariffName) throws DuplicateInstanceException;

	Tariff findTariffById(Long id) throws InstanceNotFoundException;
}
