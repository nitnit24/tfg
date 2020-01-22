package es.udc.tfg.backend.model.entities;

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

public class SendEmail {

	private static String USER_NAME = "roomnit@gmail.com"; 
	private static String PASSWORD = "roomtfg1."; 
	
	public SendEmail() {

	}

	public final static void sendMsgBooking(Booking booking) throws UnsupportedEncodingException, IOException {

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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(booking.getEmail()));
			
			//Seteamos el asunto
			message.setSubject("Su reserva " + booking.getLocator());
			
			String userName= "Bonaire";
			String userAddress = "Direccion inventada, A Coruña 15005, España";
			String userPhone= "+34 999999999";
			String userEmail= "user@user.com";
			
			String msg = "<b><h2 style=\"text-align:center;\" >¡Gracias " + booking.getName() + "!</h2></b>";
			 msg += "<b><h1 style=\"text-align:center;\" > Confirmación de reserva </h1></b>";
			 msg += " <b> <h2 style=\"text-align:center;color:MediumBlue;\" >" + userName + "</h2></b>";
			 msg += "<p style=\"text-align:center;\" >" + userAddress ;
			 msg += "<br/> <b> Teléfono: </b>" + userPhone;
			 msg += " <br/> <a href=\"mailto:" + userEmail + " \">Enviar e-mail al alojamiento</a>";
			
			msg += "<div style=\"text-align:center;\">";
			msg += " <br/> <table  align=\"center\" >";
			msg += "<tr>";
			msg += " <td><b> Titular de la reserva: " + "<b> </td>"; 
			msg += "<td> " + booking.getName() + " " + booking.getSurName() + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg +="<td> <b>  Fecha de entrada: " + " </b> </td>";
			msg += "<td>" + booking.getStartDate().get(Calendar.DATE) + "-" + 
					booking.getStartDate().get(Calendar.MONTH)+ 1 +"-" + booking.getStartDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg +="<td> <b>  Fecha de Salida: " + " </b> </td>";
			msg += "<td>" + booking.getEndDate().get(Calendar.DATE) + "-" + 
					booking.getEndDate().get(Calendar.MONTH) + 1 +"-" + booking.getEndDate().get(Calendar.YEAR) + "</td>";
			msg += "</tr>";
			msg += "<tr>";
			msg += "<td><b> Duración de la estancia: " + " </b> </td>";
			msg += "<td>" +  booking.getDuration() + " noches </td>";
			msg += "</tr>";
			msg += "</table>";
			msg += " </div>";
			
			msg += "<br/>";
			msg += "<div style=\"text-align:center;background-color:Lavender;\">";
			msg +="<b>  <br/> <h2 style=\"text-align:center;\" > Habitaciones reservadas: </h2> </b>";
			msg += " <br/> <table  align=\"center\">";
			for (BookingRoom bookingRoom : booking.getBookingRooms()) {
				msg += "<tr>";
				msg += "<td>" + "<b>" + bookingRoom.getQuantity() + "</b>" + " x ";
				msg += "<b>" + bookingRoom.getRoomTypeName() + "</b>" + " en ";
				msg += "<b>" + bookingRoom.getTariffName() + "</b> </td>" ;
				msg += "<td>" + "<b> ";
				msg +=  bookingRoom.getRoomTotalPrice() + " &#8364;" + "</b> </td>";
				msg += "</tr>";
			}
			
			msg += "<br/><br/> <h2 style=\"text-align:center;\" >Precio total: " + "<b>" + booking.getTotalPrice().toString() + " &#8364;"  + "</b> </h2>";
			msg += "</div>";
			
			msg+= "<br/> <br/> <h4 style=\"text-align:center;\" >Puede acceder para ver su reserva completa indicando los siguientes datos: </h4>";
			msg += "<h3 style=\"text-align:center;\" > Localizador : " +  "<b>" + booking.getLocator() + "</b> ";
			msg += "<br/> Clave: " + "<b>"+  booking.getKey() + "</b> </h3> ";
	
			msg += "<div style=\"text-align:center;\">";
			msg +=  "<a style=\\\"text-align:center;\\\" href=\'http://localhost:3000/booking/booking-find'><button>Acceder</button></a>";
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