package com.example.demo.Main;

import com.example.demo.DAO.ProdutoDAO;
import com.example.demo.Modelo.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class SpringEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEstoqueApplication.class, args);
	}


	public void run(String... args) {
		Estoque estoque = new EstoqueBuilder().setProdutoDAO().createEstoque();
		Estoque.menu();
	}
}

