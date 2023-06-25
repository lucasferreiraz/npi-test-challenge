package com.challenge.npi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "API de Gerenciamento de Sócios e Dependentes",
		version = "1.0",
		description = "Documentando uma API básica de gerenciamento de sócios e dependentes",
		contact = @Contact(name = "Lucas Ferreira", email = "ferreiralucas090@gmail.com", url = "https://github.com/lucasferreiraz")
	)
)
public class NpiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NpiApplication.class, args);
	}

}
