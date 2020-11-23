package com.mrraxa01.projetoSBI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mrraxa01.projetoSBI.services.S3Service;

@SpringBootApplication
public class ProjetoSbiApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoSbiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		s3Service.uploadFile("C:\\temp\\1.jpg");
	}
}
