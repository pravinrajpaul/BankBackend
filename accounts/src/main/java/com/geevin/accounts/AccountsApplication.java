package com.geevin.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "Auditor")
@OpenAPIDefinition(
		info = @Info(
			title = "Specification for Accounts services",
			description = "Has all the details of Accounts services",
			version = "v0.1",
			license = @License(
					name = "MIT 2.0",
					url = "https://www.geevin.co.in"
			),
			contact = @Contact(
					name = "Pravin R",
					email = "pravinrajpaul@gmail.com",
					url = "https://www.geevin.co.in"
			)
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
