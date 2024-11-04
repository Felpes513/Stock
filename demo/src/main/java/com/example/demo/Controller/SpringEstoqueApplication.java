package com.example.demo.Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo.Repository")
public class SpringEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEstoqueApplication.class, args);
	}
}
