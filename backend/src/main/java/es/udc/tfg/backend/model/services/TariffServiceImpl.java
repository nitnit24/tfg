package es.udc.tfg.backend.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.Tariff;
import es.udc.tfg.backend.model.entities.TariffDao;
import es.udc.tfg.backend.model.entities.Hotel;
import es.udc.tfg.backend.model.entities.HotelDao;

@Service
@Transactional
public class TariffServiceImpl implements TariffService {

	@Autowired
	private TariffDao tariffDao;
	
	@Autowired
	private HotelDao userDao;

	@Override
	public Tariff addTariff(Long hotelId, Tariff tariff) throws DuplicateInstanceException, InstanceNotFoundException {

		Optional<Hotel> existingUser = userDao.findById(hotelId);
		
		if (!existingUser.isPresent()) {
			throw new InstanceNotFoundException("project.entities.user", hotelId);
		}
		
		if (tariffDao.existsByName(tariff.getName())) {
			throw new DuplicateInstanceException("project.entities.tariff", tariff.getName());
		}

		if (tariffDao.existsByCode(tariff.getCode())) {
			throw new DuplicateInstanceException("project.entities.tariff", tariff.getCode());
		}
		
		
		tariff.setHotel(existingUser.get());
		tariffDao.save(tariff);

		return tariff;

	}

	@Override
	public Tariff updateTariff(Long hotelId, Tariff tariff) throws InstanceNotFoundException, PermissionException {

		Optional<Tariff> existingTariffItem = tariffDao.findById(tariff.getId());

		if (!existingTariffItem.isPresent()) {
			throw new InstanceNotFoundException("project.entities.tariff", tariff.getId());
		}
		
		if (!existingTariffItem.get().getHotel().getId().equals(hotelId)) {
			throw new PermissionException();
		}

		existingTariffItem.get().setName(tariff.getName());
		existingTariffItem.get().setCode(tariff.getCode());
		existingTariffItem.get().setDescription(tariff.getDescription());

		tariffDao.save(existingTariffItem.get());

		return existingTariffItem.get();

	}

	@Override
	public void removeTariff(Long hotelId, Long tariffId) throws InstanceNotFoundException, PermissionException {
		Optional<Tariff> existingTariffItem = tariffDao.findById(tariffId);

		if (!existingTariffItem.isPresent()) {
			throw new InstanceNotFoundException("project.entities.tariff", tariffId);
		}
		
		if (!existingTariffItem.get().getHotel().getId().equals(hotelId)) {
			throw new PermissionException();
		}

		tariffDao.delete(existingTariffItem.get());
	}

	@Override
	public Tariff findTariffById(Long id) throws InstanceNotFoundException {

		Optional<Tariff> tariff = tariffDao.findById(id);

		if (!tariff.isPresent()) {
			throw new InstanceNotFoundException("project.entities.tariff", id);
		}

		return tariff.get();

	}

	@Override
	public List<Tariff> findAllTariffs() {

		Iterable<Tariff> tariffs = tariffDao.findAll(new Sort(Sort.Direction.ASC, "id"));
		List<Tariff> tariffsAsList = new ArrayList<>();

		tariffs.forEach(c -> tariffsAsList.add(c));

		return tariffsAsList;
	}

}
