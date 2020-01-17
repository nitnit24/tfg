package es.udc.tfg.backend.rest.dtos;

import static es.udc.tfg.backend.rest.dtos.TariffConversor.toTariffDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import es.udc.tfg.backend.model.entities.RoomTable;
import es.udc.tfg.backend.model.entities.RoomTableDay;
import es.udc.tfg.backend.model.entities.RoomTableTariff;

public class RoomTableConversor {
	
	private RoomTableConversor() {}
	
	public final static List<RoomTableDto> toRoomTableDtos(List<RoomTable> roomTables) {
		return roomTables.stream().map(o -> toRoomTableDto(o)).collect(Collectors.toList());
	}
	
	
	private final static RoomTableDto toRoomTableDto(RoomTable roomTable) {
		
		List<RoomTableDayDto> roomTableDays = roomTable.getRoomTableDays().stream().map(i -> toRoomTableDayDto(i)).collect(Collectors.toList());
		
		roomTableDays.sort(Comparator.comparing(RoomTableDayDto::getDay));
		
		List<TariffDto> tariffs = roomTable.getTariffs().stream().map(i -> toTariffDto(i)).collect(Collectors.toList());
		
		 return new RoomTableDto(roomTable.getRoomTypeId(), roomTable.getRoomTypeName(),
				 roomTableDays, tariffs);
		 
	}
	
	
	private final static RoomTableDayDto toRoomTableDayDto(RoomTableDay roomTableDay) {
		
		List<RoomTableTariffDto> roomTableTariffs = roomTableDay.getRoomTableTariffs().stream().map(i -> toRoomTableTariffDto(i)).collect(Collectors.toList());
		
		roomTableTariffs.sort(Comparator.comparing(RoomTableTariffDto::getTariffId));
			
		 return new RoomTableDayDto(toMillis(roomTableDay.getDay()), roomTableDay.getFreeRooms(), roomTableTariffs);
		 
	}

private final static RoomTableTariffDto toRoomTableTariffDto(RoomTableTariff roomTableTariff) {
				
		 return new RoomTableTariffDto(roomTableTariff.getTariffId(), roomTableTariff.getPrice());
		 
	}


	private final static long toMillis(Calendar calendar) {
	      TimeZone tz = calendar.getTimeZone();
	      ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
	      LocalDateTime date =  LocalDateTime.ofInstant(calendar.toInstant(), zid);
		return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
	}
	
}
