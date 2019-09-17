package es.udc.tfg.backend.rest.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;



public class TariffDto {

	public interface AllValidations {
	}


	private Long id;
	private String name;
	private String code;
	private BigDecimal price;

	public TariffDto() {
	}

	public TariffDto(Long id, String name, String code, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.price = price;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull(groups={AllValidations.class})
	@Size(min=1, max=60, groups={AllValidations.class})
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(groups={AllValidations.class})
	@Size(min=1, max=6, groups={AllValidations.class})
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Digits(fraction = 2, integer = 4, groups={AllValidations.class})
	@Min(value = 0, groups={AllValidations.class})
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
