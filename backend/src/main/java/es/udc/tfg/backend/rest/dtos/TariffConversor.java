package es.udc.tfg.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.tfg.backend.model.entities.Tariff;

public class TariffConversor {

	private TariffConversor() {
	}

	public final static TariffDto toTariffDto(Tariff tariff) {
		return new TariffDto(tariff.getId(), tariff.getName(), tariff.getCode(), tariff.getDescription());
	}

	public final static Tariff toTariff(TariffDto tariffDto) {
		return new Tariff(tariffDto.getId(), tariffDto.getName(), tariffDto.getCode(), tariffDto.getDescription());
	}

	public final static List<TariffDto> toTariffDtos(List<Tariff> tariffs) {
		return tariffs.stream().map(c -> toTariffDto(c)).collect(Collectors.toList());
	}

}
