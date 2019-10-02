package es.udc.tfg.backend.rest.controllers;

import static es.udc.tfg.backend.rest.dtos.TariffConversor.toTariff;
import static es.udc.tfg.backend.rest.dtos.TariffConversor.toTariffDto;
import static es.udc.tfg.backend.rest.dtos.TariffConversor.toTariffDtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.tfg.backend.model.common.exceptions.DuplicateInstanceException;
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



}
