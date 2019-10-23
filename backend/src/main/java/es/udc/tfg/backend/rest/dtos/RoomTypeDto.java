package es.udc.tfg.backend.rest.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoomTypeDto {

	public interface AllValidations {
	}

	private Long id;
	private String name;
	private int capacity;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;

	public RoomTypeDto() {
	}

	public RoomTypeDto(Long id, String name, int capacity, BigDecimal minPrice, BigDecimal maxPrice) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull(groups = { AllValidations.class })
	@Size(min = 1, max = 60, groups = { AllValidations.class })
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull(groups = { AllValidations.class })
	@Min(value=1,  groups = { AllValidations.class })
	public int getCapacity() {
		return capacity;
	}

	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Min(value=0,  groups = { AllValidations.class })
	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	@Min(value=0,  groups = { AllValidations.class })
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	

}