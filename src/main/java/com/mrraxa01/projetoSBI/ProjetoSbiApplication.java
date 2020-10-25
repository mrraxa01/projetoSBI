package com.mrraxa01.projetoSBI;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mrraxa01.projetoSBI.domain.Categoria;
import com.mrraxa01.projetoSBI.repositories.CategoriaRepository;

@SpringBootApplication
public class ProjetoSbiApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProjetoSbiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		categoriaRepository.deleteAll();
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		
	}

}
