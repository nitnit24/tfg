package es.udc.tfg.backend.model.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@IdClass(SaleRoomPk.class)
public class SaleRoom {
	
	private Long id;
	private Calendar date;
	private int freeRooms;
	private RoomType roomType;

	public SaleRoom() {
	}

	public SaleRoom(Long id, Calendar date, int freeRooms,  RoomType roomType) {
		this.id = id;
		this.date = date;
		this.freeRooms = freeRooms;
		this.roomType = roomType;
	}

	
	@Id
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Id
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
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "id", nullable = false , insertable=false , updatable=false)
	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType RoomType) {
		this.roomType = RoomType;
	}


}
