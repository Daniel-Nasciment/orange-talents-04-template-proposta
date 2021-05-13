package com.orange.proposta.orange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// LEMBRAR DE DAR UM @EnableFeignClients e adicionar suas dependencias

@SpringBootApplication
@EnableFeignClients
public class PropostaOrangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaOrangeApplication.class, args);
	}

}
