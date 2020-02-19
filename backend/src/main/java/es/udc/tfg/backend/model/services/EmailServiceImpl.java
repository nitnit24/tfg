package es.udc.tfg.backend.model.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.backend.model.entities.Booking;
import es.udc.tfg.backend.model.entities.BookingRoom;
import es.udc.tfg.backend.model.entities.Hotel;
import es.udc.tfg.backend.model.entities.RoomType;

@Service
@Transactional
public class EmailServiceImpl implements EmailService{

	private static String USER_NAME = "roomnit@gmail.com"; 
	private static String PASSWORD = "roomtfg1."; 

	
	@Override
	public void sendMsgBookingToClient(Booking booking, Hotel hotel) throws UnsupportedEncodingException, IOException {

		// creando la instancia de properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}
		});

		try {
			// Creamos un nuevo mensaje, y le pasamos nuestra sesión iniciada en el paso
			// anterior.
			Message message = new MimeMessage(session);

			// Seteamos la dirección desde la cual enviaremos el mensaje
			message.setFrom(new InternetAddress("roomnit@gmail.com"));

			// Seteamos el destino de nuestro mensaje
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(booking.getEmail()));

			// Seteamos el asunto
			message.setSubject("Su reserva " + booking.getLocator());

			String hotelName = hotel.getHotelName();
			String hotelAddress = hotel.getAddress();
			String hotelPhone = hotel.getPhone();
			String hotelEmail = hotel.getEmail();
			String image = hotel.getImage();

			String msg = "<b><h2 style=\"text-align:center;\" >¡Gracias " + booking.getName() + "!</h2></b>";
			msg += "<b><h1 style=\"text-align:center;\" > Confirmación de reserva </h1></b>";
//			msg+= "<img src=\"" + image + "\"/>";
			msg += " <b> <h2 style=\"text-align:center;color:MediumBlue;\" > Hotel " + hotelName + "</h2></b>";
			msg += "<p style=\"text-align:center;\" >" + hotelAddress;
			msg += "<br/> <b> Teléfono: </b>" + hotelPhone;
			msg += " <br/> <a href=\"mailto:" + hotelEmail + " \">Enviar e-mail al alojamiento</a>";

			msg += "<div style=\"text-align:center;\">";
			msg += " <br/> <table  align=\"center\" >";
			msg += "<tr>";
			msg += " <td><b> Titular de la reserva: " + "<b> </td>";
			msg += "<td> " + booking.getName() + " " + booking.getSurName() + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td> <b>  Fecha de entrada: " + " </b> </td>";
			msg += "<td>" + booking.getStartDate().get(Calendar.DATE) + "-" + booking.getStartDate().get(Calendar.MONTH)
					+ 1 + "-" + booking.getStartDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td> <b>  Fecha de Salida: " + " </b> </td>";
			msg += "<td>" + booking.getEndDate().get(Calendar.DATE) + "-" + booking.getEndDate().get(Calendar.MONTH) + 1
					+ "-" + booking.getEndDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td><b> Duración de la estancia: " + " </b> </td>";
			msg += "<td>" + booking.getDuration() + " noches </td>";
			msg += "</tr>";
			msg += "</table>";
			msg += " </div>";

			msg += "<br/>";
			msg += "<div style=\"text-align:center;background-color:Lavender;\">";
			msg += "<b>  <br/> <h2 style=\"text-align:center;\" > Habitaciones reservadas: </h2> </b>";
			msg += " <br/> <table  align=\"center\">";
			for (BookingRoom bookingRoom : booking.getBookingRooms()) {
				msg += "<tr>";
				msg += "<td>" + "<b>" + bookingRoom.getQuantity() + "</b>" + " x ";
				msg += "<b>" + bookingRoom.getRoomTypeName() + "</b>" + " en ";
				msg += "<b>" + bookingRoom.getTariffName() + "</b> </td>";
				msg += "<td>" + "<b> ";
				msg += bookingRoom.getRoomTotalPrice() + " &#8364;" + "</b> </td>";
				msg += "</tr>";
			}

			msg += "<br/><br/> <h2 style=\"text-align:center;\" >Precio total: " + "<b>"
					+ booking.getTotalPrice().toString() + " &#8364;" + "</b> </h2>";
			msg += "</div>";

			msg += "<br/> <br/> <h4 style=\"text-align:center;\" >Puede acceder para ver su reserva completa indicando los siguientes datos: </h4>";
			msg += "<h3 style=\"text-align:center;\" > Localizador : " + "<b>" + booking.getLocator() + "</b> ";
			msg += "<br/> Clave: " + "<b>" + booking.getKey() + "</b> </h3> ";

			msg += "<div style=\"text-align:center;\">";
			msg += "<a style=\\\"text-align:center;\\\" href=\'http://localhost:3000/booking/booking-find'><button>Acceder</button></a>";
			msg += "</div>";

			// Y por ultimo el texto.
			message.setText(msg);
			message.setContent(msg, "text/html");

			// Esta orden envía el mensaje
			Transport.send(message);

		} catch (MessagingException e) {

			throw new RuntimeException(e);
		}

	}
	
	@Override
	public void sendMsgBookingToHotel(Booking booking, Hotel hotel) throws UnsupportedEncodingException, IOException {

		// creando la instancia de properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}
		});

		try {
			// Creamos un nuevo mensaje, y le pasamos nuestra sesión iniciada en el paso
			// anterior.
			Message message = new MimeMessage(session);

			// Seteamos la dirección desde la cual enviaremos el mensaje
			message.setFrom(new InternetAddress("roomnit@gmail.com"));

			// Seteamos el destino de nuestro mensaje
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(hotel.getEmail()));

			// Seteamos el asunto
			message.setSubject("Su reserva " + booking.getLocator());

			String hotelName = hotel.getHotelName();
			String hotelAddress = hotel.getAddress();
			String hotelPhone = hotel.getPhone();
			String hotelEmail = hotel.getEmail();
			String image = hotel.getImage();

			String msg = "<b><h2 style=\"text-align:center;\" >¡Gracias " + booking.getName() + "!</h2></b>";
			msg += "<b><h1 style=\"text-align:center;\" > Confirmación de reserva </h1></b>";
			msg += "<h3 style=\"text-align:center;\" > Localizador : " + "<b>" + booking.getLocator() + "</b> ";
			msg += " <b> <h2 style=\"text-align:center;color:MediumBlue;\" > Hotel " + hotelName + "</h2></b>";
			msg += "<p style=\"text-align:center;\" >" + hotelAddress;
			msg += "<br/> <b> Teléfono: </b>" + hotelPhone;
			msg += " <br/> <a href=\"mailto:" + hotelEmail + " \">Enviar e-mail al alojamiento</a>";

			msg += "<div style=\"text-align:center;\">";
			msg += " <br/> <table  align=\"center\" >";
			msg += "<tr>";
			msg += " <td><b> Titular de la reserva: " + "<b> </td>";
			msg += "<td> " + booking.getName() + " " + booking.getSurName() + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td> <b>  Fecha de entrada: " + " </b> </td>";
			msg += "<td>" + booking.getStartDate().get(Calendar.DATE) + "-" + booking.getStartDate().get(Calendar.MONTH)
					+ 1 + "-" + booking.getStartDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td> <b>  Fecha de Salida: " + " </b> </td>";
			msg += "<td>" + booking.getEndDate().get(Calendar.DATE) + "-" + booking.getEndDate().get(Calendar.MONTH) + 1
					+ "-" + booking.getEndDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td><b> Duración de la estancia: " + " </b> </td>";
			msg += "<td>" + booking.getDuration() + " noches </td>";
			msg += "</tr>";
			msg += "</table>";
			msg += " </div>";

			msg += "<br/>";
			msg += "<div style=\"text-align:center;background-color:Lavender;\">";
			msg += "<b>  <br/> <h2 style=\"text-align:center;\" > Habitaciones reservadas: </h2> </b>";
			msg += " <br/> <table  align=\"center\">";
			for (BookingRoom bookingRoom : booking.getBookingRooms()) {
				msg += "<tr>";
				msg += "<td>" + "<b>" + bookingRoom.getQuantity() + "</b>" + " x ";
				msg += "<b>" + bookingRoom.getRoomTypeName() + "</b>" + " en ";
				msg += "<b>" + bookingRoom.getTariffName() + "</b> </td>";
				msg += "<td>" + "<b> ";
				msg += bookingRoom.getRoomTotalPrice() + " &#8364;" + "</b> </td>";
				msg += "</tr>";
			}

			msg += "<br/><br/> <h2 style=\"text-align:center;\" >Precio total: " + "<b>"
					+ booking.getTotalPrice().toString() + " &#8364;" + "</b> </h2>";
			msg += "</div>";

			// Y por ultimo el texto.
			message.setText(msg);
			message.setContent(msg, "text/html");

			// Esta orden envía el mensaje
			Transport.send(message);

		} catch (MessagingException e) {

			throw new RuntimeException(e);
		}

	}
	
	@Override
	public void sendMsgUpdateBookingToClient(Booking booking, Hotel hotel) throws UnsupportedEncodingException, IOException {

		// creando la instancia de properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}
		});

		try {
			// Creamos un nuevo mensaje, y le pasamos nuestra sesión iniciada en el paso
			// anterior.
			Message message = new MimeMessage(session);

			// Seteamos la dirección desde la cual enviaremos el mensaje
			message.setFrom(new InternetAddress("roomnit@gmail.com"));

			// Seteamos el destino de nuestro mensaje
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(booking.getEmail()));

			// Seteamos el asunto
			message.setSubject("Su reserva modificada " + booking.getLocator());

			String hotelName = hotel.getHotelName();
			String hotelAddress = hotel.getAddress();
			String hotelPhone = hotel.getPhone();
			String hotelEmail = hotel.getEmail();
			String image = hotel.getImage();
			
			String msg = "<b><h2 style=\"text-align:center;\" >¡Gracias " + booking.getName() + "!</h2></b>";
			msg += "<b><h1 style=\"text-align:center;\" > Confirmación de modificación reserva </h1></b>";
//			msg+= "<img src=\" " + image + "\">";
			msg += " <b> <h2 style=\"text-align:center;color:MediumBlue;\" >" + hotelName + "</h2></b>";
			msg += "<p style=\"text-align:center;\" >" + hotelAddress;
			msg += "<br/> <b> Teléfono: </b>" + hotelPhone;
			msg += " <br/> <a href=\"mailto:" + hotelEmail + " \">Enviar e-mail al alojamiento</a>";

			msg += "<div style=\"text-align:center;\">";
			msg += " <br/> <table  align=\"center\" >";
			msg += "<tr>";
			msg += " <td><b> Titular de la reserva: " + "<b> </td>";
			msg += "<td> " + booking.getName() + " " + booking.getSurName() + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td> <b>  Fecha de entrada: " + " </b> </td>";
			msg += "<td>" + booking.getStartDate().get(Calendar.DATE) + "-" + booking.getStartDate().get(Calendar.MONTH)
					+ 1 + "-" + booking.getStartDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td> <b>  Fecha de Salida: " + " </b> </td>";
			msg += "<td>" + booking.getEndDate().get(Calendar.DATE) + "-" + booking.getEndDate().get(Calendar.MONTH) + 1
					+ "-" + booking.getEndDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td><b> Duración de la estancia: " + " </b> </td>";
			msg += "<td>" + booking.getDuration() + " noches </td>";
			msg += "</tr>";
			msg += "</table>";
			msg += " </div>";

			msg += "<br/>";
			msg += "<div style=\"text-align:center;background-color:Lavender;\">";
			msg += "<b>  <br/> <h2 style=\"text-align:center;\" > Habitaciones reservadas: </h2> </b>";
			msg += " <br/> <table  align=\"center\">";
			for (BookingRoom bookingRoom : booking.getBookingRooms()) {
				msg += "<tr>";
				msg += "<td>" + "<b>" + bookingRoom.getQuantity() + "</b>" + " x ";
				msg += "<b>" + bookingRoom.getRoomTypeName() + "</b>" + " en ";
				msg += "<b>" + bookingRoom.getTariffName() + "</b> </td>";
				msg += "<td>" + "<b> ";
				msg += bookingRoom.getRoomTotalPrice() + " &#8364;" + "</b> </td>";
				msg += "</tr>";
			}

			msg += "<br/><br/> <h2 style=\"text-align:center;\" >Precio total: " + "<b>"
					+ booking.getTotalPrice().toString() + " &#8364;" + "</b> </h2>";
			msg += "</div>";

			msg += "<br/> <br/> <h4 style=\"text-align:center;\" >Puede acceder para ver su reserva completa indicando los siguientes datos: </h4>";
			msg += "<h3 style=\"text-align:center;\" > Localizador : " + "<b>" + booking.getLocator() + "</b> ";
			msg += "<br/> Clave: " + "<b>" + booking.getKey() + "</b> </h3> ";

			msg += "<div style=\"text-align:center;\">";
			msg += "<a style=\\\"text-align:center;\\\" href=\'http://localhost:3000/booking/booking-find'><button>Acceder</button></a>";
			msg += "</div>";

			// Y por ultimo el texto.
			message.setText(msg);
			message.setContent(msg, "text/html");

			// Esta orden envía el mensaje
			Transport.send(message);

		} catch (MessagingException e) {

			throw new RuntimeException(e);
		}

	}
	
	@Override
	public void sendMsgUpdateBookingToHotel(Booking booking, Hotel hotel) throws UnsupportedEncodingException, IOException {

		// creando la instancia de properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}
		});

		try {
			// Creamos un nuevo mensaje, y le pasamos nuestra sesión iniciada en el paso
			// anterior.
			Message message = new MimeMessage(session);

			// Seteamos la dirección desde la cual enviaremos el mensaje
			message.setFrom(new InternetAddress("roomnit@gmail.com"));

			// Seteamos el destino de nuestro mensaje
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(hotel.getEmail()));

			// Seteamos el asunto
			message.setSubject("Su reserva modificada " + booking.getLocator());

			String hotelName = hotel.getHotelName();
			String hotelAddress = hotel.getAddress();
			String hotelPhone = hotel.getPhone();
			String hotelEmail = hotel.getEmail();
			String image = hotel.getImage();
			
			String msg = "<b><h2 style=\"text-align:center;\" >¡Gracias " + booking.getName() + "!</h2></b>";
			msg += "<b><h1 style=\"text-align:center;\" > Confirmación de modificación reserva </h1></b>";
			msg += "<h3 style=\"text-align:center;\" > Localizador : " + "<b>" + booking.getLocator() + "</b> ";
			msg += " <b> <h2 style=\"text-align:center;color:MediumBlue;\" >" + hotelName + "</h2></b>";
			msg += "<p style=\"text-align:center;\" >" + hotelAddress;
			msg += "<br/> <b> Teléfono: </b>" + hotelPhone;
			msg += " <br/> <a href=\"mailto:" + hotelEmail + " \">Enviar e-mail al alojamiento</a>";

			msg += "<div style=\"text-align:center;\">";
			msg += " <br/> <table  align=\"center\" >";
			msg += "<tr>";
			msg += " <td><b> Titular de la reserva: " + "<b> </td>";
			msg += "<td> " + booking.getName() + " " + booking.getSurName() + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td> <b>  Fecha de entrada: " + " </b> </td>";
			msg += "<td>" + booking.getStartDate().get(Calendar.DATE) + "-" + booking.getStartDate().get(Calendar.MONTH)
					+ 1 + "-" + booking.getStartDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td> <b>  Fecha de Salida: " + " </b> </td>";
			msg += "<td>" + booking.getEndDate().get(Calendar.DATE) + "-" + booking.getEndDate().get(Calendar.MONTH) + 1
					+ "-" + booking.getEndDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td><b> Duración de la estancia: " + " </b> </td>";
			msg += "<td>" + booking.getDuration() + " noches </td>";
			msg += "</tr>";
			msg += "</table>";
			msg += " </div>";

			msg += "<br/>";
			msg += "<div style=\"text-align:center;background-color:Lavender;\">";
			msg += "<b>  <br/> <h2 style=\"text-align:center;\" > Habitaciones reservadas: </h2> </b>";
			msg += " <br/> <table  align=\"center\">";
			for (BookingRoom bookingRoom : booking.getBookingRooms()) {
				msg += "<tr>";
				msg += "<td>" + "<b>" + bookingRoom.getQuantity() + "</b>" + " x ";
				msg += "<b>" + bookingRoom.getRoomTypeName() + "</b>" + " en ";
				msg += "<b>" + bookingRoom.getTariffName() + "</b> </td>";
				msg += "<td>" + "<b> ";
				msg += bookingRoom.getRoomTotalPrice() + " &#8364;" + "</b> </td>";
				msg += "</tr>";
			}

			msg += "<br/><br/> <h2 style=\"text-align:center;\" >Precio total: " + "<b>"
					+ booking.getTotalPrice().toString() + " &#8364;" + "</b> </h2>";
			msg += "</div>";

			// Y por ultimo el texto.
			message.setText(msg);
			message.setContent(msg, "text/html");

			// Esta orden envía el mensaje
			Transport.send(message);

		} catch (MessagingException e) {

			throw new RuntimeException(e);
		}

	}
	
	@Override
	public void sendMsgCancelBookingToClient(Booking booking, Hotel hotel) throws UnsupportedEncodingException, IOException {

		// creando la instancia de properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}
		});

		try {
			// Creamos un nuevo mensaje, y le pasamos nuestra sesión iniciada en el paso
			// anterior.
			Message message = new MimeMessage(session);

			// Seteamos la dirección desde la cual enviaremos el mensaje
			message.setFrom(new InternetAddress("roomnit@gmail.com"));

			// Seteamos el destino de nuestro mensaje
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(booking.getEmail()));

			// Seteamos el asunto
			message.setSubject("Su reserva modificada " + booking.getLocator());

			String hotelName = hotel.getHotelName();
			String hotelAddress = hotel.getAddress();
			String hotelPhone = hotel.getPhone();
			String hotelEmail = hotel.getEmail();
			String image = hotel.getImage();
			
			String msg = "<b><h2 style=\"text-align:center;\" >¡Gracias " + booking.getName() + "!</h2></b>";
			msg += "<b><h1 style=\"text-align:center;\" > Confirmación de cancelarión reserva </h1></b>";
//			msg+= "<img src=\" " + image + "\">";
			msg += " <b> <h2 style=\"text-align:center;color:MediumBlue;\" >" + hotelName + "</h2></b>";
			msg += "<p style=\"text-align:center;\" >" + hotelAddress;
			msg += "<br/> <b> Teléfono: </b>" + hotelPhone;
			msg += " <br/> <a href=\"mailto:" + hotelEmail + " \">Enviar e-mail al alojamiento</a>";

			msg += "<div style=\"text-align:center;\">";
			msg += " <br/> <table  align=\"center\" >";
			msg += "<tr>";
			msg += " <td><b> Titular de la reserva: " + "<b> </td>";
			msg += "<td> " + booking.getName() + " " + booking.getSurName() + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td> <b>  Fecha de entrada: " + " </b> </td>";
			msg += "<td>" + booking.getStartDate().get(Calendar.DATE) + "-" + booking.getStartDate().get(Calendar.MONTH)
					+ 1 + "-" + booking.getStartDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td> <b>  Fecha de Salida: " + " </b> </td>";
			msg += "<td>" + booking.getEndDate().get(Calendar.DATE) + "-" + booking.getEndDate().get(Calendar.MONTH) + 1
					+ "-" + booking.getEndDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td><b> Duración de la estancia: " + " </b> </td>";
			msg += "<td>" + booking.getDuration() + " noches </td>";
			msg += "</tr>";
			msg += "</table>";
			msg += " </div>";

			msg += "<br/>";
			msg += "<div style=\"text-align:center;background-color:Lavender;\">";
			msg += "<b>  <br/> <h2 style=\"text-align:center;\" > Habitaciones reservadas: </h2> </b>";
			msg += " <br/> <table  align=\"center\">";
			for (BookingRoom bookingRoom : booking.getBookingRooms()) {
				msg += "<tr>";
				msg += "<td>" + "<b>" + bookingRoom.getQuantity() + "</b>" + " x ";
				msg += "<b>" + bookingRoom.getRoomTypeName() + "</b>" + " en ";
				msg += "<b>" + bookingRoom.getTariffName() + "</b> </td>";
				msg += "<td>" + "<b> ";
				msg += bookingRoom.getRoomTotalPrice() + " &#8364;" + "</b> </td>";
				msg += "</tr>";
			}

			msg += "<br/><br/> <h2 style=\"text-align:center;\" >Precio total: " + "<b>"
					+ booking.getTotalPrice().toString() + " &#8364;" + "</b> </h2>";
			msg += "</div>";

			msg += "<br/> <br/> <h4 style=\"text-align:center;\" >Puede acceder para ver su reserva completa indicando los siguientes datos: </h4>";
			msg += "<h3 style=\"text-align:center;\" > Localizador : " + "<b>" + booking.getLocator() + "</b> ";
			msg += "<br/> Clave: " + "<b>" + booking.getKey() + "</b> </h3> ";

			msg += "<div style=\"text-align:center;\">";
			msg += "<a style=\\\"text-align:center;\\\" href=\'http://localhost:3000/booking/booking-find'><button>Acceder</button></a>";
			msg += "</div>";

			// Y por ultimo el texto.
			message.setText(msg);
			message.setContent(msg, "text/html");

			// Esta orden envía el mensaje
			Transport.send(message);

		} catch (MessagingException e) {

			throw new RuntimeException(e);
		}

	}
	
	@Override
	public void sendMsgCancelBookingToHotel(Booking booking, Hotel hotel) throws UnsupportedEncodingException, IOException {

		// creando la instancia de properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}
		});

		try {
			// Creamos un nuevo mensaje, y le pasamos nuestra sesión iniciada en el paso
			// anterior.
			Message message = new MimeMessage(session);

			// Seteamos la dirección desde la cual enviaremos el mensaje
			message.setFrom(new InternetAddress("roomnit@gmail.com"));

			// Seteamos el destino de nuestro mensaje
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(hotel.getEmail()));

			// Seteamos el asunto
			message.setSubject("Su reserva modificada " + booking.getLocator());

			String hotelName = hotel.getHotelName();
			String hotelAddress = hotel.getAddress();
			String hotelPhone = hotel.getPhone();
			String hotelEmail = hotel.getEmail();
			String image = hotel.getImage();
			
			String msg = "<b><h2 style=\"text-align:center;\" >¡Gracias " + booking.getName() + "!</h2></b>";
			msg += "<b><h1 style=\"text-align:center;\" > Confirmación de cancelación reserva </h1></b>";
			msg += "<h3 style=\"text-align:center;\" > Localizador : " + "<b>" + booking.getLocator() + "</b> ";
			msg += " <b> <h2 style=\"text-align:center;color:MediumBlue;\" >" + hotelName + "</h2></b>";
			msg += "<p style=\"text-align:center;\" >" + hotelAddress;
			msg += "<br/> <b> Teléfono: </b>" + hotelPhone;
			msg += " <br/> <a href=\"mailto:" + hotelEmail + " \">Enviar e-mail al alojamiento</a>";

			msg += "<div style=\"text-align:center;\">";
			msg += " <br/> <table  align=\"center\" >";
			msg += "<tr>";
			msg += " <td><b> Titular de la reserva: " + "<b> </td>";
			msg += "<td> " + booking.getName() + " " + booking.getSurName() + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td> <b>  Fecha de entrada: " + " </b> </td>";
			msg += "<td>" + booking.getStartDate().get(Calendar.DATE) + "-" + booking.getStartDate().get(Calendar.MONTH)
					+ 1 + "-" + booking.getStartDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td> <b>  Fecha de Salida: " + " </b> </td>";
			msg += "<td>" + booking.getEndDate().get(Calendar.DATE) + "-" + booking.getEndDate().get(Calendar.MONTH) + 1
					+ "-" + booking.getEndDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td><b> Duración de la estancia: " + " </b> </td>";
			msg += "<td>" + booking.getDuration() + " noches </td>";
			msg += "</tr>";
			msg += "</table>";
			msg += " </div>";

			msg += "<br/>";
			msg += "<div style=\"text-align:center;background-color:Lavender;\">";
			msg += "<b>  <br/> <h2 style=\"text-align:center;\" > Habitaciones reservadas: </h2> </b>";
			msg += " <br/> <table  align=\"center\">";
			for (BookingRoom bookingRoom : booking.getBookingRooms()) {
				msg += "<tr>";
				msg += "<td>" + "<b>" + bookingRoom.getQuantity() + "</b>" + " x ";
				msg += "<b>" + bookingRoom.getRoomTypeName() + "</b>" + " en ";
				msg += "<b>" + bookingRoom.getTariffName() + "</b> </td>";
				msg += "<td>" + "<b> ";
				msg += bookingRoom.getRoomTotalPrice() + " &#8364;" + "</b> </td>";
				msg += "</tr>";
			}

			msg += "<br/><br/> <h2 style=\"text-align:center;\" >Precio total: " + "<b>"
					+ booking.getTotalPrice().toString() + " &#8364;" + "</b> </h2>";
			msg += "</div>";

			// Y por ultimo el texto.
			message.setText(msg);
			message.setContent(msg, "text/html");

			// Esta orden envía el mensaje
			Transport.send(message);

		} catch (MessagingException e) {

			throw new RuntimeException(e);
		}

	}
	
	@Override
	public void sendMsgFreeRoomsZero(RoomType roomType, Calendar date, Hotel hotel) throws UnsupportedEncodingException, IOException {

		//creando la instancia de properties
		 Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.starttls.enable", "true");
		 props.put("mail.smtp.host", "smtp.gmail.com");
		 props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}
		});

		try {
			//Creamos un nuevo mensaje, y le pasamos nuestra sesión iniciada en el paso anterior.
			 Message message = new MimeMessage(session);
			 
			//Seteamos la dirección desde la cual enviaremos el mensaje
			 message.setFrom(new InternetAddress("roomnit@gmail.com"));
			 
			//Seteamos el destino de nuestro mensaje
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(hotel.getEmail())); 
			
			//Seteamos el asunto
			message.setSubject("Alerta: no quedan habitaciones libres");
			
	
			
			String msg = "<b><h1 style=\"text-align:center;\" >  &#10071; Alerta  </h1></b>";
			 msg += "<h3 style=\"text-align:center;\" > No quedan habitaciones libres de tipo ";
			 msg += " <b>" + roomType.getName() + " </b>";
			 msg += " para el día ";
			 msg += " <b>" + date.get(Calendar.DATE) + "-" + date.get(Calendar.MONTH)
						+ 1 + "-" + date.get(Calendar.YEAR) + " </b>";
			 msg += "</h3> ";
		
			 msg += "<div style=\"text-align:center;\">";
			 msg += "<a style=\\\"text-align:center;\\\" href=\'http://localhost:3000/dailyPanel/dailyPanel-management'><button>Revisa tu Panel Diario</button></a>";
			 msg += "</div>";
			
			//Y por ultimo el texto.
			message.setText(msg);
			message.setContent(msg, "text/html");
			
			//Esta orden envía el mensaje
			 Transport.send(message);
			 
	
			 }
		catch (MessagingException e) {

			throw new RuntimeException(e);
			 }

	}
	
	
}