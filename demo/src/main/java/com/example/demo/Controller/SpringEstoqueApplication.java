package com.example.demo.Controller;

import com.example.demo.service.EstoqueService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstoqueService.class, args);
	}
}
