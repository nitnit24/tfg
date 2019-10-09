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

	public Tariff() {
	}

	public Tariff(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
	public Tariff(Long id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
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

}
