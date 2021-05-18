package com.orange.proposta.orange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

// LEMBRAR DE DAR UM @EnableFeignClients e adicionar suas dependencias

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class PropostaOrangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaOrangeApplication.class, args);
	}

}
