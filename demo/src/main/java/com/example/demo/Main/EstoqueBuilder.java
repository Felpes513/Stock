package com.example.demo.Main;

import com.example.demo.DAO.ProdutoDAO;

public class EstoqueBuilder {
    private ProdutoDAO produtoDAO;

    public EstoqueBuilder setProdutoDAO() {
        this.produtoDAO = produtoDAO;
        return this;
    }

    public Estoque createEstoque() {
        return new Estoque(produtoDAO);
    }
}