package kz.kolesa.parsingkolesa;

import kz.kolesa.parsingkolesa.service.ParseTask;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParsingKolesaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ParsingKolesaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ParseTask.parseKolesa();
	}
}
