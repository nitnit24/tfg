package es.udc.tfg.backend.model.entities;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
@Entity
public class SaleRoom {

	private RoomType roomType;
	private Calendar date;
	private int freeRooms;

	public SaleRoom() {
	}

	public SaleRoom(RoomType roomType, Calendar date, int freeRooms) {
		this.roomType = roomType;
		this.date = date;
		this.freeRooms = freeRooms;

	}

	@OneToOne(optional=false)
	@PrimaryKeyJoinColumn
	@JoinColumn(name= "roomTypeId")
	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType RoomType) {
		this.roomType = RoomType;
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

}
