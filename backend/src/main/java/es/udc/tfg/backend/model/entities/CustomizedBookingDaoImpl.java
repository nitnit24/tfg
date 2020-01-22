package es.udc.tfg.backend.model.entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class CustomizedBookingDaoImpl implements CustomizedBookingDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Slice<Booking> find(String dataType, Calendar minDate, Calendar maxDate, String text, int page, int size){
		
		String[] keywords = text == null ? new String[0] : text.split("\\s");
		String queryString = "SELECT b FROM Booking b";
		
		
		if (dataType != null|| keywords.length > 0) {
			queryString += " WHERE ";
		}

		if (dataType.equals("Reserva")) {
			queryString += " b.date >= :minDate AND b.date <= :maxDate";
		}
		
		if (dataType.equals("Entrada")) {
			queryString += " b.startDate >= :minDate AND b.startDate <= :maxDate";
		}
		
		if(dataType.equals("Salida")) {
			queryString += " b.endDate >= :minDate AND b.endDate <= :maxDate";
		}
		
		if (keywords.length != 0) {
			
			if (dataType != null) {
				queryString += " AND ";
			}
			
			for (int i = 0; i<keywords.length-1; i++) {
				queryString += "LOWER(b.name) LIKE LOWER(:keyword" + i + ") AND ";
			}
			
			queryString += "LOWER(b.name) LIKE LOWER(:keyword" + (keywords.length-1) + ")";
			
		}
		
		queryString += " ORDER BY b.startDate";
		
		Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);
		

		query.setParameter("minDate", minDate);
		query.setParameter("maxDate", maxDate);

		
		if (keywords.length != 0) {
			for (int i = 0; i<keywords.length; i++) {
				query.setParameter("keyword" + i, '%' + keywords[i] + '%');
			}
	
		}
		
		List<Booking> bookings = query.getResultList();
		boolean hasNext = bookings.size() == (size+1);
		
		if (hasNext) {
			bookings.remove(bookings.size()-1);
		}
		
		return new SliceImpl<>(bookings, PageRequest.of(page, size), hasNext);
		
	}

}
