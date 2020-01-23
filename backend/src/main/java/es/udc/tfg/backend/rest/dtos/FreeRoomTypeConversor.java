package es.udc.tfg.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.tfg.backend.model.entities.FreeRoomType;
import es.udc.tfg.backend.model.entities.FreeRoomTypeTariffs;
import static es.udc.tfg.backend.rest.dtos.SaleRoomTariffConversor.toSaleRoomTariffDto;

public class FreeRoomTypeConversor {
	
	private FreeRoomTypeConversor() {}
	
	public final static List<FreeRoomTypeDto> toFreeRoomTypeDtos(List<FreeRoomType> freeRoomTypes) {
		return freeRoomTypes.stream().map(o -> toFreeRoomTypeDto(o)).collect(Collectors.toList());
	}
	
	
	private final static FreeRoomTypeDto toFreeRoomTypeDto(FreeRoomType freeRoomType) {
		
		List<FreeRoomTypeTariffsDto> freeRoomTypeTariffs = freeRoomType.getFreeRoomTypeTariffs().stream().map(
										i -> toFreeRoomTypeTariffsDto(i)).collect(Collectors.toList());
		
	 return new FreeRoomTypeDto(freeRoomType.getRoomTypeId(), freeRoomType.getRoomTypeName(), freeRoomType.getRoomTypeDescription(),
				 freeRoomType.getCapacity(), freeRoomType.getMaxFreeRooms(), freeRoomTypeTariffs);
		 
	}
	
	
	private final static FreeRoomTypeTariffsDto toFreeRoomTypeTariffsDto(FreeRoomTypeTariffs freeRoomTypeTariffs) {
		
		List<SaleRoomTariffDto> saleRoomTariffs = freeRoomTypeTariffs.getSaleRoomTariffs().stream().map(
				i -> toSaleRoomTariffDto(i)).collect(Collectors.toList());
			
		 return new FreeRoomTypeTariffsDto(freeRoomTypeTariffs.getTariffId(), freeRoomTypeTariffs.getTariffName(),
				 freeRoomTypeTariffs.getTariffDescription(), freeRoomTypeTariffs.getTotalPrice(), 
				 saleRoomTariffs);
		 
		 
	}

}
