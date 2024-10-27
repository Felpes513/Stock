package com.example.demo.Controller;

import com.example.demo.Modelo.Produto;
import com.example.demo.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {
        return estoqueService.listarProdutos();
    }
}
