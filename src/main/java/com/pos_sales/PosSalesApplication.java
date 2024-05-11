package com.pos_sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
// @Import(WebConfig.class)
public class PosSalesApplication {

	@Autowired
	private EmailService senderService;

	public static void main(String[] args) {
		SpringApplication.run(PosSalesApplication.class, args);
	}

	// @EventListener(ApplicationReadyEvent.class)
	// public void sendMail() {
	// senderService.sendResetEmail("trishajoyoballo@gmail.com", "");
	// }

}
