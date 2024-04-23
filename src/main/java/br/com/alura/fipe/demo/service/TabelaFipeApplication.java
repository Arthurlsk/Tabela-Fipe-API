package br.com.alura.fipe.demo.service;

import br.com.alura.fipe.demo.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
//Esse CommandLineRunner vai transformar
public class
TabelaFipeApplication implements CommandLineRunner {

	//Esse método run
	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeApplication.class, args);

	}

	@Override
	//Nesse de baixo, que esse aqui vai ser o novo método run e também será nosso main!
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();


	}

}
