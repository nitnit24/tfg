package es.udc.tfg.backend.rest.controllers;

import static es.udc.tfg.backend.rest.dtos.TariffConversor.toTariff;
import static es.udc.tfg.backend.rest.dtos.TariffConversor.toTariffDto;
import static es.udc.tfg.backend.rest.dtos.TariffConversor.toTariffDtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.tfg.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.tfg.backend.model.services.TariffService;
import es.udc.tfg.backend.rest.dtos.TariffDto;

@RestController
@RequestMapping("/tariffs")
public class TariffController {

	@Autowired
	private TariffService tariffService;

	@PostMapping("/addTariff")
	public TariffDto addTariff(@Validated({ TariffDto.AllValidations.class }) @RequestBody TariffDto tariffDto)
			throws DuplicateInstanceException {
		return toTariffDto(tariffService.addTariff(toTariff(tariffDto)));
	}
	
	@GetMapping("/tariffs")
	public List<TariffDto> findAllTariffs() {
		return toTariffDtos(tariffService.findAllTariffs());
	}
	

	@PutMapping("/{id}")
	public TariffDto updateTariffs(@PathVariable("id") Long id,
			 @RequestBody TariffDto tariffDto) 
					throws InstanceNotFoundException {
		return toTariffDto(tariffService.updateTariff(toTariff(tariffDto)));
	}

//	@DeleteMapping("/{id}")
//	public removeTariffs(@RequestAttribute Long tariffId, @PathVariable("id") Long id) 
//					throws InstanceNotFoundException {
//		return tariffService.removeTariff(id);
//	}



}
