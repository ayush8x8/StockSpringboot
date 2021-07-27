package com.phase3ayush.ayush.controller;

//public class User1Controller {
//
//}

//package com.stockexchange.phase3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import org.apache.tomcat.jni.Buffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phase3ayush.ayush.dao.User1Repository;
import com.phase3ayush.ayush.entities.Company;
import com.phase3ayush.ayush.entities.User1;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@CrossOrigin
public class User1Controller {

	@Autowired
	User1Repository userRepo;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;

//	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/setuserapi", method = RequestMethod.POST)

	public String Stringreactuserapi(@RequestBody User1 user) throws AddressException, MessagingException, IOException {
		
		User1 user1= userDetailsService.save(user);
//		User1 usrsaved = userRepo.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Responded", "UserController");
		headers.add("Access-Control-Allow-Origin", "*");
		sendemail(user1.getId());
		return user.toString();

	}
	
	

	public void sendemail(Long userid) throws AddressException, MessagingException, IOException {

		User1 user = userRepo.getById(userid);
		Email from = new Email("gpathak161999@gmail.com");
	    String subject = "Sending with SendGrid is Fun";
	    Email to = new Email(user.getEmail());
	    Content content = new Content("text/html", "<h1><a href =\"https://ayushstockmarketspring.herokuapp.com/confirmuser/" + userid + "/\"> Click to confirm </a></h1>");
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid(System.getenv("SD_KEY"));
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (Exception ex) {
	      throw ex;
	    }
		
//		User1 user = userRepo.getById(userid);
//
//		final String username = "ayushtb7@gmail.com";
//		final String password = "springboot7A#";
//
//		Properties prop = new Properties();
//		prop.put("mail.smtp.host", "smtp.gmail.com");
//		prop.put("mail.smtp.port", "587");
//		prop.put("mail.smtp.auth", "true");
//		prop.put("mail.smtp.starttls.enable", "true"); // TLS
//
//		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
//			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//				return new javax.mail.PasswordAuthentication(username, password);
//			}
//		});
//
//		try {
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("sftrainerram@gmail.com"));
//			// message.setRecipients(
//			// Message.RecipientType.TO,
//			// InternetAddress.parse("sftrainerram@gmail.com")
//			// );
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
//			message.setSubject("USer confirmation email");
//			// message.setText("Dear Mail Crawler,"
//			// + "\n\n Please do not spam my email!");
//			message.setContent(
//					"<h1><a href =\"http://127.0.0.1:8084/confirmuser/" + userid + "/\"> Click to confirm </a></h1>",
//					"text/html");
//			Transport.send(message);
//
//			System.out.println("Done");
//
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
	}

	@RequestMapping(value = "/confirmuser/{userid}", method = RequestMethod.GET)
	public String welcomepage(@PathVariable Long userid) {
		Optional<User1> userlist = userRepo.findById(userid);
		// do a null check for home work
		User1 usr = new User1();
		usr = userRepo.getById(userid);
		usr.setConfirmed(true);
		userRepo.save(usr);
		return "User confirmed" + usr.getName();
	}
	
	@PostMapping("/addUser1")
	public int addUserDetails(@RequestBody User1 user) {
		userRepo.save(user);
		return 101;
	}
	
	@GetMapping("/getUsers")
	public List<User1> getUserDetails() {
		List<User1> users = new ArrayList<User1>();
		userRepo.findAll().forEach(userguy -> users.add(userguy));
		return users;
	}
	
	@PostMapping("/getUserByNameAndPass")
	public Object getUserDetailsByNameAndPass(@RequestBody Map<String, String> object) {
		User1 user1 =  userRepo.findByNameAndPassword(object.get("name"),object.get("password"));
		Map<String, String> map = new HashMap<String, String>();
		if(user1 == null) {
			map.put("response", "not found");
			return map;
		}
		else {
			if(user1.getConfirmed() == true) {
				return user1;
			}
			else {
				map.put("response", "not confirmed");
				return map;
			}
		}
	}
	
	
	@GetMapping("/getUserByName")
	public User1 getUserDetailsByName(@RequestParam String userName) {
		return userRepo.findByName(userName);
	}
	
	@PutMapping("/updateUser")
	public int updateUserDetails(@RequestBody User1 user, Long id) {
		userRepo.save(user);
		return 202;
	}

}
