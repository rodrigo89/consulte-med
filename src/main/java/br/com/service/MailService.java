 /**
 * 
 */
package br.com.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.model.Usuario;

/**
 * @author carlosbarbosagomesfilho
 * Sep 3, 2019
 * 
 */
@Service
public class MailService {

	private JavaMailSender javaMailSender;
	private Logger logger = Logger.getLogger(MailService.class);
	
	
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMail(SimpleMailMessage usuario) throws MailException, MessagingException{
		  MimeMessage mail = javaMailSender.createMimeMessage();

          MimeMessageHelper helper = new MimeMessageHelper( mail );
          helper.setTo( "cbgomesiesp@gmail.com" );
          helper.setSubject( "Teste Envio de e-mail" );
          helper.setText("<p>Hello from Spring Boot Application</p>", true);
          javaMailSender.send(mail);
		
		try {
			javaMailSender.send(mail);
		}catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	public void sendMail(Usuario usuario) throws MailException, MessagingException{

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo( usuario.getEmail());
        message.setFrom("cbarbosagomesfilho@gmail.com");
        message.setSubject( "Teste Envio de e-mail sistema consultemed funcionando" );
        message.setText("Serviço de envio de e-mail funcionando perfeitamente .....");
        javaMailSender.send(message);
	
	}
	
}
