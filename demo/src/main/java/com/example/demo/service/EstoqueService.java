package com.example.demo.service;

import com.example.demo.Modelo.Produto;
import com.example.demo.Repository.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstoqueService {

        @Autowired
        private ProdutoDAO produtoDAO;

        private Map<Produto, Integer> carrinho = new HashMap<>();

        // Método para cadastrar um produto
        public void cadastrarProduto(Produto produto) {
                produtoDAO.cadastrarProduto(produto); // Salvar o produto no banco de dados
        }

        // Método para listar produtos
        public List<Produto> listarProdutos() {
                return produtoDAO.listarProduto();
        }

        // Método para adicionar produtos ao carrinho
        public void adicionarProdutoAoCarrinho(int idProduto) {
                Produto produto = produtoDAO.buscarProdutoPeloId(idProduto);
                if (produto != null) {
                        carrinho.put(produto, carrinho.getOrDefault(produto, 0) + 1);
                }
        }

        // Método para finalizar a compra e calcular o valor total
        public double finalizarCompras() {
                double valorDaCompra = 0.0;
                for (Produto p : carrinho.keySet()) {
                        int quantidade = carrinho.get(p);
                        valorDaCompra += p.getPreco() * quantidade;
                }
                carrinho.clear(); // Limpar o carrinho após a compra
                return valorDaCompra;
        }

        // Método para atualizar um produto
        public void atualizarProduto(int id, Produto dadosAtualizados) {
                Produto produto = produtoDAO.buscarProdutoPeloId(id);
                if (produto != null) {
                        produto.setNome(dadosAtualizados.getNome());
                        produto.setPreco(dadosAtualizados.getPreco());
                        produto.setQuantidade(dadosAtualizados.getQuantidade());
                        produtoDAO.atualizarProduto(produto); // Salva o produto atualizado no banco
                }
        }

        // Método para deletar um produto pelo ID
        public void deletarProduto(int id) {
                produtoDAO.deletarProduto(id);
        }
}