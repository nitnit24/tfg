package es.udc.tfg.backend.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.entities.Tariff;
import es.udc.tfg.backend.model.entities.TariffDao;


@Service
@Transactional
public class TariffServiceImpl implements TariffService {

	@Autowired
	private TariffDao tariffDao;

	@Override
	public Tariff addTariff(String tariffName) throws DuplicateInstanceException {

//		if (tariffDao.existsByTypeName(tariffName)) {
//			throw new DuplicateInstanceException("project.entities.user", tariffName);
//		}
		
		Tariff tariff = new Tariff();
		tariff.setName(tariffName);
		
		tariffDao.save(tariff);
		
		return tariff;

	}
	
	@Override
	public Tariff findTariffById(Long id) throws InstanceNotFoundException {
		
		Optional<Tariff> tariff = tariffDao.findById(id);
		
		if (!tariff.isPresent()) {
			throw new InstanceNotFoundException("project.entities.tariff", id);
		}
		
		return tariff.get();

	}

}
