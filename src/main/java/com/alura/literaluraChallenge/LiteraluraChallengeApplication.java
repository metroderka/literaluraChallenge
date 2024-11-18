package com.alura.literaluraChallenge;

import com.alura.literaluraChallenge.principal.Inicio;
import com.alura.literaluraChallenge.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class LiteraluraChallengeApplication implements CommandLineRunner {
    @Autowired
	private ILibroRepository  repository;
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Inicio inicio = new Inicio(repository);
		inicio.muestraElMenu();

	}
}

