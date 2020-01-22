package es.udc.tfg.backend.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tariff {

	private Long id;
	private String name;
	private String code;
	private String description;

	public Tariff() {
	}

	public Tariff(String name, String code, String description) {
		this.name = name;
		this.code = code;
		this.description = description;
	}
	
	public Tariff(Long id, String name, String code, String description) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "tariffName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "tariffCode")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "tariffDescription")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
