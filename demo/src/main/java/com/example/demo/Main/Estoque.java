package com.example.demo.Main;

import com.example.demo.DAO.ProdutoDAO;
import com.example.demo.Modelo.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class Estoque {
	private static final Scanner input = new Scanner(System.in);
	private final ProdutoDAO produtoDAO;
	private static Map<Produto, Integer> carrinho;

	@Autowired
	public Estoque(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
		carrinho = new HashMap<>();
	}

	public static void menu() {
		System.out.println("---------------------------------------------------");
		System.out.println("------------------Bem vindo ao Estoque-------------");
		System.out.println("---------------------------------------------------");
		System.out.println("**** Selecione uma operação que deseja realizar ***");
		System.out.println("|   Opção 1 -- Cadastrar Produto  |");
		System.out.println("|   Opção 2 -- Listar Produtos    |");
		System.out.println("|   Opção 3 -- Atualizar Produto   |");
		System.out.println("|   Opção 4 -- Excluir Produto     |");
		System.out.println("|   Opção 5 -- Sair                |");

		int option = input.nextInt();

		switch (option) {
			case 1:
				cadastrarProduto();
				break;
			case 2:
				listarProdutos();
				break;
			case 3:
				atualizarProduto();
				break;
			case 4:
				deletarProduto();
				break;
			case 5:
				System.out.println("Volte Sempre :)");
				System.exit(0);
			default:
				System.out.println("Opção inválida");
				menu();
				break;
		}
	}

	private static void cadastrarProduto() {
		System.out.println("Nome do produto:");
		String nome = input.next();

		System.out.println("Preço do produto:");
		String precoEntrada = input.next();
		double preco = parseDoubleComVirgula(precoEntrada);

		System.out.println("Quantidade do produto:");
		int quantidade = input.nextInt();

		Produto produto = new Produto(nome, preco, quantidade);
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.cadastrarProduto(produto); // salvar no banco de dados

		System.out.println(produto.getNome() + " cadastrado com sucesso");
		menu();
	}

	private static void listarProdutos() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtos = produtoDAO.listarProduto();

		if (produtos.size() > 0) {
			System.out.println("Listar Produtos!\n");
			for (Produto p : produtos) {
				System.out.println(p);
			}
		} else {
			System.out.println("Nenhum produto cadastrado");
		}
		menu();
	}

	private static void atualizarProduto() {
		System.out.println("Digite o ID do produto que deseja atualizar:");
		String idEntrada = input.next();
		int id = Integer.parseInt(idEntrada);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarProdutoPeloId(id);

		if (produto != null) {
			System.out.println("Nome atual: " + produto.getNome());
			System.out.println("Digite o novo nome (ou pressione Enter para manter o mesmo nome):");
			input.nextLine(); // Limpa o buffer
			String nome = input.nextLine();
			if (!nome.isEmpty()) {
				produto.setNome(nome);
			}

			System.out.println("Preço atual do produto: " + produto.getPreco());
			System.out.println("Digite o novo preço do produto (ou pressione Enter para manter o mesmo preço):");
			String precoInput = input.nextLine();
			if (!precoInput.isEmpty()) {
				double preco = Double.parseDouble(precoInput);
				if (preco > 0) {
					produto.setPreco(preco);
				}
			}

			System.out.println("Quantidade atual do produto: " + produto.getQuantidade());
			System.out.println("Digite a nova quantidade do produto (ou pressione Enter para manter a mesma quantidade):");
			String quantidadeInput = input.nextLine();
			if (!quantidadeInput.isEmpty()) {
				int quantidade = Integer.parseInt(quantidadeInput);
				if (quantidade >= 0) { // Permite que a quantidade seja 0 para indicar que não há mais estoque
					produto.setQuantidade(quantidade);
				}
			}
			produtoDAO.atualizarProduto(produto);
			System.out.println("Produto atualizado com sucesso.");
		} else {
			System.out.println("Produto não encontrado.");
		}
		menu();
	}

	private static void deletarProduto() {
		System.out.println("Digite o ID do produto que deseja deletar:");
		int id = input.nextInt();

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.deletarProduto(id);

		System.out.println("Produto deletado com sucesso.");
		menu();
	}

	private static double parseDoubleComVirgula(String entrada) {
		try {
			return Double.parseDouble(entrada.replace(',', '.'));
		} catch (NumberFormatException e) {
			System.out.println("Formato de número inválido. Use (.) como separador decimal.");
			return 0;
		}
	}
}
