package com.example.demo.service;

import com.example.demo.Modelo.Produto;
import com.example.demo.DAO.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo.Main.Estoque.menu;

public class EstoqueService {
        @Autowired
        private Produto produtoDAO;

        private Map<Produto, Integer> carrinho = new HashMap<>();

        public void cadastrarProduto(Produto produto) {
            produtoDAO.cadastrarProduto(produto);
        }

        public List<Produto> listarProdutos() {
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List<Produto> produtos = produtoDAO.listarProduto();

                if (produtos.size() > 0){
                        System.out.println("Listar Produtos!\n");
                        for (Produto p : produtos){
                                System.out.println(p);
                        }
                } else {
                        System.out.println("Nenhum produto cadastrado");
                }
                menu();
            return produtos;
        }

        public String comprarProduto(int id, int quantidade) {
            // Adicione a lógica de compra aqui
            return "";
        }

        public String verCarrinho() {
            // Adicione a lógica para ver o carrinho aqui
            return "";
        }

        public void deletarProduto(int id) {
            produtoDAO.deletarProduto(id);
        }
}
