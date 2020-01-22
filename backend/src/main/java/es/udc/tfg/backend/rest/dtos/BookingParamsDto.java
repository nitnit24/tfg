package es.udc.tfg.backend.rest.dtos;

import java.util.List;

import javax.validation.constraints.NotNull;

import es.udc.tfg.backend.model.entities.BookingRoomSummary;

public class BookingParamsDto {
	
	private Long startDate;
	private Long endDate;
	private List<BookingRoomSummary> bookingRoomSummarys;
	private String name;
	private String surname;
	private String phone;
	private String email;
	private String petition;
	
	public BookingParamsDto() {}
	
	@NotNull
	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	@NotNull
	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	@NotNull
	public List<BookingRoomSummary> getBookingRoomSummarys() {
		return bookingRoomSummarys;
	}


	public void setBookingRoomSummarys(List<BookingRoomSummary> bookingRoomSummarys) {
		this.bookingRoomSummarys = bookingRoomSummarys;
	}

	@NotNull
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}

	@NotNull
	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	@NotNull
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPetition() {
		return petition;
	}

	
	public void setPetition(String petition) {
		this.petition = petition;
	}

}
