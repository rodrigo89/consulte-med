/**
 * 
 */
package br.com.controller;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.model.Usuario;
import br.com.service.MailService;

/**
 * @author carlosbarbosagomesfilho Sep 3, 2019
 * 
 */
@Controller
@RequestMapping("/mail")
public class MailController {

	private Logger logger = LoggerFactory.getLogger(MailController.class);

	@Autowired
	private MailService service;

	@GetMapping("/signup")
	public String signup() {

		return "Acessando o seu service";
	}

	@GetMapping("/signup-sucesso")
	public String signupSucesso() throws MessagingException {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setText("Hello from Spring Boot Application");
		message.setTo("rodrigog.sobral@gmail.com");
		message.setFrom("rodrigog.sobral@gmail.com");

		try {
			this.service.sendMail(message);
		} catch (MailException e) {
			logger.info("Error" + e.getMessage());
		}

		return "/pages/usuario/usuarios";
	}
	
	@GetMapping("/send")
	public String sendMail() throws MailException, MessagingException {
		
		Usuario usuario = new Usuario();
		usuario.setPrimeiroNome("Carlos Barbosa");
		usuario.setSegundoNome("Barbosa Gomes Filho");
		usuario.setEmail("rodrigog.sobral@gmail.com");
		
		this.service.sendMail(usuario);
		return "/pages/usuario/usuarios";
	}
}
