package es.udc.tfg.backend.model.entities;

import java.io.Serializable;
import java.util.Calendar;


public class SaleRoomPk implements Serializable{

	private Long id;
	private Calendar date;
	
	public SaleRoomPk() {
	}
	
	public SaleRoomPk(Long id, Calendar date) {
		this.id = id;
		this.date = date;
	}
	
	public Long getId () {
		return id;
	}
	public void setId (Long id) {
		this.id = id;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	
}
