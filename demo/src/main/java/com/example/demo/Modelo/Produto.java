package com.example.demo.Modelo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Produto {

    private int id;

    @NotBlank(message = "O nome do produto não pode ser vazio")
    private String nome;

    @Min(value = 0, message = "O preço deve ser maior ou igual a zero")
    private Double preco;

    @Min(value = 0, message = "A quantidade deve ser maior ou igual a zero")
    private int quantidade;

    // Construtor sem parâmetros, não inicializa os atributos
    public Produto() {
    }

    @Override
    public String toString() {
        return String.format("ID: %d%nNome: %s%nPreço: %.2f%nQuantidade: %d",
                this.getId(), this.getNome(), this.getPreco(), this.getQuantidade());
    }
}
