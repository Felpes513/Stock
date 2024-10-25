package com.example.demo.Modelo;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class Produto {

    @Getter @Setter
    private int id;

    
    @Getter @Setter
    @com.example.demo.Modelo.NotBlank(message = "O nome do produto não pode ser vazio")
    private String nome;

    @Getter @Setter

    @Min(value = 0, message = "O preço deve ser maior ou igual a zero")
    private Double preco;

    @Getter @Setter
    @Min(value = 0, message = "A quantidade deve ser maior ou igual a zero")
    private int quantidade;

    public Produto(String nome, Double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return String.format("ID: %d%nNome: %s%nPreço: %.2f%nQuantidade: %d",
                this.getId(), this.getNome(), this.getPreco(), this.getQuantidade());
    }

    public void cadastrarProduto(Produto produto) {
        
    }

    public void deletarProduto(int id) {
    }
}
