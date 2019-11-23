package es.udc.tfg.backend.model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SaleRoomTariff {
	
	private Long id;
	private BigDecimal price;
	private Tariff tariff;
	private SaleRoom saleRoom;
	
	public SaleRoomTariff() {
		
	}
	
	public SaleRoomTariff(BigDecimal price, Tariff tariff, SaleRoom saleRoom) {
		this.price = price;
		this.tariff = tariff;
		this.saleRoom = saleRoom;
	}
	
	
	public SaleRoomTariff(Long id, BigDecimal price, Tariff tariff, SaleRoom saleRoom) {
		this.id = id;
		this.price = price;
		this.tariff = tariff;
		this.saleRoom = saleRoom;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name= "idTariff")
	public Tariff getTariff() {
		return tariff;
	}

	public void setTariff(Tariff tariff) {
		this.tariff = tariff;
	}

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name= "idSaleRoom")
	public SaleRoom getSaleRoom() {
		return saleRoom;
	}

	public void setSaleRoom(SaleRoom saleRoom) {
		this.saleRoom = saleRoom;
	}




}
