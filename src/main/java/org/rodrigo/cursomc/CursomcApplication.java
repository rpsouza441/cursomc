package org.rodrigo.cursomc;

import java.util.Arrays;

import org.rodrigo.cursomc.domain.Categoria;
import org.rodrigo.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication  implements CommandLineRunner{

	@Autowired
	private CategoriaRepository catRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		catRepo.saveAll(Arrays.asList(cat1,cat2));
		
		
	}

}
