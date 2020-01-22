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
import es.udc.tfg.backend.model.entities.User;
import es.udc.tfg.backend.model.entities.UserDao;

@Service
@Transactional
public class TariffServiceImpl implements TariffService {

	@Autowired
	private TariffDao tariffDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public Tariff addTariff(Long userId, Tariff tariff) throws DuplicateInstanceException, InstanceNotFoundException {

		Optional<User> existingUser = userDao.findById(userId);
		
		if (!existingUser.isPresent()) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}
		
		if (tariffDao.existsByName(tariff.getName())) {
			throw new DuplicateInstanceException("project.entities.tariff", tariff.getName());
		}

		if (tariffDao.existsByCode(tariff.getCode())) {
			throw new DuplicateInstanceException("project.entities.tariff", tariff.getCode());
		}
		
		
		tariff.setUser(existingUser.get());
		tariffDao.save(tariff);

		return tariff;

	}

	@Override
	public Tariff updateTariff(Long userId, Tariff tariff) throws InstanceNotFoundException, PermissionException {

		Optional<Tariff> existingTariffItem = tariffDao.findById(tariff.getId());

		if (!existingTariffItem.isPresent()) {
			throw new InstanceNotFoundException("project.entities.tariff", tariff.getId());
		}
		
		if (!existingTariffItem.get().getUser().getId().equals(userId)) {
			throw new PermissionException();
		}

		existingTariffItem.get().setName(tariff.getName());
		existingTariffItem.get().setCode(tariff.getCode());
		existingTariffItem.get().setDescription(tariff.getDescription());

		tariffDao.save(existingTariffItem.get());

		return existingTariffItem.get();

	}

	@Override
	public void removeTariff(Long userId, Long tariffId) throws InstanceNotFoundException, PermissionException {
		Optional<Tariff> existingTariffItem = tariffDao.findById(tariffId);

		if (!existingTariffItem.isPresent()) {
			throw new InstanceNotFoundException("project.entities.tariff", tariffId);
		}
		
		if (!existingTariffItem.get().getUser().getId().equals(userId)) {
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
