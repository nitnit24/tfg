package es.udc.tfg.backend.model.entities;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.Transient;

public class Reserve {

	private Long idReserve;
	private Long code;
	private Calendar date;
	private Calendar starDate;
	private Calendar endDate;
	private String name;
	private String surName;
	private int phone;
	private String email;
	private Set<ReserveItem> items = new HashSet<>();
	private BigDecimal total;
	
	
	public Long getIdReserve() {
		return idReserve;
	}
	public void setIdReserve(Long idReserve) {
		this.idReserve = idReserve;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Calendar getStarDate() {
		return starDate;
	}
	public void setStarDate(Calendar starDate) {
		this.starDate = starDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<ReserveItem> getItems() {
		return items;
	}
	public void setItems(Set<ReserveItem> items) {
		this.items = items;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	

}
