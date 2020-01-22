package es.udc.tfg.backend.model.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoomType {

	private Long id;
	private String name;
	private String description;
	private int capacity;
	private int quantity;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;

	public RoomType() {
	}

	public RoomType(String name, String description, int capacity, int quantity, BigDecimal minPrice, BigDecimal maxPrice) {
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.quantity = quantity;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	
	public RoomType(Long id, String name, String description, int capacity, int quantity, BigDecimal minPrice, BigDecimal maxPrice) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.quantity = quantity;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
