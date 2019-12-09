package es.udc.tfg.backend.model.services;

import java.math.BigDecimal;
import java.util.Calendar;

@SuppressWarnings("serial")
public class PriceNotBetweenMinAndMaxValueException extends Exception {
    
	private Calendar date;
    private BigDecimal min;
	private BigDecimal max;
	
	public PriceNotBetweenMinAndMaxValueException(Calendar date, BigDecimal min, BigDecimal max) {
	
		this.date = date;
		this.min = min;
		this.max = max;
	}

	public Calendar getDate() {
		return date;
	}

	public BigDecimal getMin() {
		return min;
	}

	public BigDecimal getMax() {
		return max;
	}
    
}
