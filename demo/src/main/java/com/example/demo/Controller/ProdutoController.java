package com.example.demo.Controller;

import com.example.demo.Modelo.Produto;
import com.example.demo.Repository.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos") // Endpoint base para o controlador
public class ProdutoController {

    @Autowired
    private ProdutoDAO produtoDAO;

    // Endpoint para cadastrar um produto
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarProduto(@RequestBody Produto produto) {
        produtoDAO.cadastrarProduto(produto);
        return ResponseEntity.ok("Produto cadastrado com sucesso! ID: " + produto.getId());
    }

    // Endpoint para listar todos os produtos
    @GetMapping("/listar")
    public List<Produto> listarProdutos() {
        return produtoDAO.listarProduto();
    }

    // Endpoint para atualizar um produto
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarProduto(@RequestBody Produto produto) {
        produtoDAO.atualizarProduto(produto);
        return ResponseEntity.ok("Produto atualizado com sucesso! ID: " + produto.getId());
    }

    // Endpoint para buscar um produto pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPeloId(@PathVariable int id) {
        Produto produto = produtoDAO.buscarProdutoPeloId(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para deletar um produto pelo ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable int id) {
        produtoDAO.deletarProduto(id);
        return ResponseEntity.ok("Produto deletado com sucesso! ID: " + id);
    }

    // Endpoint para realizar uma venda e atualizar o estoque
    @PutMapping("/vender/{id}")
    public ResponseEntity<String> venderProduto(@PathVariable int id, @RequestParam int quantidade) {
        produtoDAO.venderProduto(id, quantidade);
        return ResponseEntity.ok("Venda realizada e estoque atualizado! ID do produto: " + id);
    }
}
