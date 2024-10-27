package com.example.demo.Controller;

import com.example.demo.Modelo.Produto;
import com.example.demo.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringEstoqueApplication implements CommandLineRunner {

	@Autowired
	private EstoqueService estoqueService;

	private static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(SpringEstoqueApplication.class, args);
	}

	@Override
	public void run(String... args) {
		menu();
	}

	private void menu() {
		while (true) {
			System.out.println("---------------------------------------------------");
			System.out.println("------------------Bem vindo ao Estoque-------------");
			System.out.println("---------------------------------------------------");
			System.out.println("**** Selecione uma operação que deseja realizar ***");
			System.out.println("|   Opção 1 -- Cadastrar  |");
			System.out.println("|   Opção 2 -- Listar     |");
			System.out.println("|   Opção 3 -- Comprar    |");
			System.out.println("|   Opção 4 -- Carrinho   |");
			System.out.println("|   Opção 5 -- Excluir    |");
			System.out.println("|   Opção 6 -- Atualizar  |");
			System.out.println("|   Opção 7 -- Sair       |");

			int option = input.nextInt();

			switch (option) {
				case 1:
					cadastrarProdutos();
					break;
				case 2:
					listarProdutos();
					break;
				case 3:
					comprarProdutos();
					break;
				case 4:
					verCarrinho();
					break;
				case 5:
					deletarProduto();
					break;
				case 6:
					atualizarProduto();
					break;
				case 7:
					System.out.println("Volte Sempre :)");
					System.exit(0);
				default:
					System.out.println("Opção inválida");
					break;
			}
		}
	}

	private void cadastrarProdutos() {
		System.out.print("Nome do produto: ");
		String nome = input.next();
		System.out.print("Preço do produto: ");
		double preco = input.nextDouble();
		System.out.print("Quantidade: ");
		int quantidade = input.nextInt();

		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setPreco(preco);
		produto.setQuantidade(quantidade);

		estoqueService.cadastrarProduto(produto);
		System.out.println("Produto cadastrado com sucesso.");
	}

	private void listarProdutos() {
		System.out.println("Lista de produtos:");
		estoqueService.listarProdutos().forEach(produto ->
				System.out.println("ID: " + produto.getId() + ", Nome: " + produto.getNome() + ", Preço: " + produto.getPreco() + ", Quantidade: " + produto.getQuantidade())
		);
	}

	private void comprarProdutos() {
		System.out.print("ID do produto para adicionar ao carrinho: ");
		int idProduto = input.nextInt();
		estoqueService.adicionarProdutoAoCarrinho(idProduto);
		System.out.println("Produto adicionado ao carrinho.");
	}

	private void verCarrinho() {
		System.out.println("Itens no carrinho:");
		double total = estoqueService.finalizarCompras();
		System.out.println("Valor total da compra: " + total);
	}

	private void deletarProduto() {
		System.out.print("ID do produto a ser deletado: ");
		int idProduto = input.nextInt();
		estoqueService.deletarProduto(idProduto);
		System.out.println("Produto deletado.");
	}

	private void atualizarProduto() {
		System.out.print("ID do produto a ser atualizado: ");
		int idProduto = input.nextInt();
		System.out.print("Novo nome: ");
		String nome = input.next();
		System.out.print("Novo preço: ");
		double preco = input.nextDouble();
		System.out.print("Nova quantidade: ");
		int quantidade = input.nextInt();

		Produto produtoAtualizado = new Produto();
		produtoAtualizado.setNome(nome);
		produtoAtualizado.setPreco(preco);
		produtoAtualizado.setQuantidade(quantidade);

		estoqueService.atualizarProduto(idProduto, produtoAtualizado);
		System.out.println("Produto atualizado com sucesso.");
	}
}
