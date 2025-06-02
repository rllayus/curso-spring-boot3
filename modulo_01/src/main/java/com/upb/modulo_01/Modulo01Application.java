package com.upb.modulo_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableJpaRepositories(basePackages = "com.upb.modulo_01.repository")
@EntityScan("com.upb.modulo_01.entity")
@SpringBootApplication(scanBasePackages = "com.upb.modulo_01")
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EnableJpaAuditing
public class Modulo01Application {
	public static void main(String[] args) {
		SpringApplication.run(Modulo01Application.class, args);
	}

}
