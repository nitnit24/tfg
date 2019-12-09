package es.udc.tfg.backend.rest.dtos;

import es.udc.tfg.backend.model.entities.SaleRoomTariff;

public class SaleRoomTariffConversor {

	private SaleRoomTariffConversor() {
	}

	public final static SaleRoomTariffDto toSaleRoomTariffDto(SaleRoomTariff saleRoomTariff) {
		return new SaleRoomTariffDto(saleRoomTariff.getId(), saleRoomTariff.getPrice(), saleRoomTariff.getTariff().getId(), 
				saleRoomTariff.getSaleRoom().getIdSaleRoom());
	}


}