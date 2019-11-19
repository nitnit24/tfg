package es.udc.tfg.backend.model.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class SaleRoom {
	
	private Long idSaleRoom;
	private Calendar date;
	private int freeRooms;
	private RoomType roomType;

	public SaleRoom() {
	}

	public SaleRoom(Long idSaleRoom, Calendar date, int freeRooms,  RoomType roomType) {
		this.idSaleRoom = idSaleRoom;
		this.date = date;
		this.freeRooms = freeRooms;
		this.roomType = roomType;
	}

	public SaleRoom(Calendar date, int freeRooms,  RoomType roomType) {
		this.date = date;
		this.freeRooms = freeRooms;
		this.roomType = roomType;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdSaleRoom() {
		return idSaleRoom;
	}

	public void setIdSaleRoom(Long idSaleRoom) {
		this.idSaleRoom = idSaleRoom;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public int getFreeRooms() {
		return freeRooms;
	}

	public void setFreeRooms(int freeRooms) {
		this.freeRooms = freeRooms;
	}
	
	@OneToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name= "idRoomType")
	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType RoomType) {
		this.roomType = RoomType;
	}


}
