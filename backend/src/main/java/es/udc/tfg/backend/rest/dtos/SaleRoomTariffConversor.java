package es.udc.tfg.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.tfg.backend.model.entities.SaleRoomTariff;

public class SaleRoomTariffConversor {

	private SaleRoomTariffConversor() {
	}

	public final static SaleRoomTariffDto toSaleRoomTariffDto(SaleRoomTariff saleRoomTariff) {
		return new SaleRoomTariffDto(saleRoomTariff.getId(), saleRoomTariff.getPrice(), saleRoomTariff.getTariff().getId(), 
				saleRoomTariff.getSaleRoom().getIdSaleRoom());
	}
	
	public final static List<SaleRoomTariffDto> toSaleRoomTariffDtos(List<SaleRoomTariff> saleRoomTariffs) {
		return saleRoomTariffs.stream().map(c -> toSaleRoomTariffDto(c)).collect(Collectors.toList());
	}



}