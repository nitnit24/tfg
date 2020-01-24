package es.udc.tfg.backend.model.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RoomType {

	private Long id;
	private User user;
	private String image;
	private String name;
	private String description;
	private int capacity;
	private int quantity;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;

	public RoomType() {
	}

	public RoomType(User user, String image, String name, String description, int capacity, int quantity, BigDecimal minPrice, BigDecimal maxPrice) {
		this.user = user;
		this.image = image;
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.quantity = quantity;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	
	public RoomType(Long id, String image, String name, String description, int capacity, int quantity, BigDecimal minPrice, BigDecimal maxPrice) {
		this.id = id;
		this.image = image;
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

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name= "userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
