package org.rodrigo.cursomc.config;

import java.text.ParseException;

import org.rodrigo.cursomc.services.DBService;
import org.rodrigo.cursomc.services.EmailService;
import org.rodrigo.cursomc.services.MockEmailService;
import org.rodrigo.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

//	@Bean
//	public EmailService emailService() {
//		return new SmtpEmailService();
//	}
}
