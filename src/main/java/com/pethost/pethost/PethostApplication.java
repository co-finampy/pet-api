package com.pethost.pethost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PethostApplication {

	public static void main(String[] args) {
		SpringApplication.run(PethostApplication.class, args);
		System.out.println("Api Rodando dog do bem");
	}
}