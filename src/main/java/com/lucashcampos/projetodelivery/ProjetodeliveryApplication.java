package com.lucashcampos.projetodelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucashcampos.projetodelivery.services.S3Service;

@SpringBootApplication
public class ProjetodeliveryApplication implements CommandLineRunner {
	@Autowired
	private S3Service s3service;

	public static void main(String[] args) {
		SpringApplication.run(ProjetodeliveryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3service.uploadFile("E:\\Lucas\\Programação\\Desenvolvimento\\images\\ana.jpg");

	}

}