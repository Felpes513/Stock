package com.example.demo.Repository;

import com.example.demo.Modelo.Produto;
import com.example.demo.conexao.ConexaoMySQL;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class ProdutoDAO {

    private static final Logger logger = Logger.getLogger(ProdutoDAO.class.getName());

    // Método de cadastro de objetos
    public void cadastrarProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.executeUpdate();

            // Obtem o ID gerado pelo banco de dados
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    produto.setId(generatedKeys.getInt(1));
                    logger.info("Produto cadastrado com sucesso! ID: " + produto.getId());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao cadastrar produto: ", e);
        }
    }

    public List<Produto> listarProduto() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(rs.getString("nome"), rs.getDouble("preco"), rs.getInt("quantidade"));
                produto.setId(rs.getInt("id"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar produtos: ", e);
        }
        return produtos;
    }

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Produto atualizado com sucesso. ID: " + produto.getId());
            } else {
                logger.warning("Produto não encontrado. ID: " + produto.getId());
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar o produto: ", e);
        }
    }

    public Produto buscarProdutoPeloId(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        Produto produto = null;

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto(
                            rs.getString("nome"),
                            rs.getDouble("preco"),
                            rs.getInt("quantidade")
                    );
                    produto.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar o produto: ", e);
        }
        return produto;
    }

    public void deletarProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Produto deletado com sucesso! ID: " + id);
            } else {
                logger.warning("Produto não encontrado. ID: " + id);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao deletar produto: ", e);
        }
    }

    // Método para realizar uma venda e atualizar o estoque
    public void venderProduto(int idProduto, int quantidadeVendida) {
        String sql = "UPDATE produtos SET quantidade = quantidade - ? WHERE id = ? AND quantidade >= ?";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantidadeVendida);
            stmt.setInt(2, idProduto);
            stmt.setInt(3, quantidadeVendida); // Condição para garantir que haja estoque suficiente

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Estoque atualizado com sucesso após a venda! ID do produto: " + idProduto);
            } else {
                logger.warning("Produto não encontrado ou quantidade insuficiente. ID: " + idProduto);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar o estoque: ", e);
        }
    }
}