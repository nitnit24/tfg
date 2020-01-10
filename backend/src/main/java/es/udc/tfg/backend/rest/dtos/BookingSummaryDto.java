package es.udc.tfg.backend.rest.dtos;

import java.math.BigDecimal;

public class BookingSummaryDto {
	
	private String locate;
	private long date;
	private String guest;
	private long startDate;
	private long endDate;
	private BigDecimal totalPrice;
	
	public BookingSummaryDto() {}

	public BookingSummaryDto(String locate, long date, String guest, long startDate, long endDate,
			BigDecimal totalPrice) {
		super();
		this.locate = locate;
		this.date = date;
		this.guest = guest;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
	}

	public String getLocate() {
		return locate;
	}

	public void setLocate(String locate) {
		this.locate = locate;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}





}
