package com.example.demo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoMySQL {
    // Classe utilitária para gerenciar a conexão com o banco de dados
    private static final Logger logger = Logger.getLogger(ConexaoMySQL.class.getName());

    // Construtor privado para evitar instâncias
    private ConexaoMySQL() {}

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.SENHA);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao conectar ao banco de dados: ", e);
            throw e; // Propagar a exceção após o log
        }
    }
}
