package es.udc.tfg.backend.rest.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TariffDto {

	public interface AllValidations {
	}

	private Long id;
	private String name;
	private String code;
	private String description;

	public TariffDto() {
	}

	public TariffDto(Long id, String name, String code, String description) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;

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
	@Size(min = 1, max = 6, groups = { AllValidations.class })
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Size(min = 0, max = 240, groups = { AllValidations.class })
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
