package es.udc.tfg.backend.model.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class RoomType {

	private Long id;
	private String name;
	private int capacity;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;

	public RoomType() {
	}

	public RoomType(String name, int capacity, BigDecimal minPrice, BigDecimal maxPrice) {
		this.name = name;
		this.capacity = capacity;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	
	public RoomType(Long id, String name, int capacity, BigDecimal minPrice, BigDecimal maxPrice) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "typeName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	

}
