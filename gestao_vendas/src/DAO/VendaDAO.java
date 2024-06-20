package DAO;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import venda.Venda;

public class VendaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gestao_vendas";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final String INSERT_VENDA_SQL = "INSERT INTO venda (data, cliente, valor_total, forma_pagamento) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_VENDAS_SQL = "SELECT * FROM venda";

    public void cadastrarVenda(Venda venda) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VENDA_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setDate(1, venda.getData());
            preparedStatement.setString(2, venda.getCliente());
            preparedStatement.setBigDecimal(3, venda.getValorTotal());
            preparedStatement.setString(4, venda.getFormaPagamento());

            preparedStatement.executeUpdate();

            // Obtendo o ID gerado automaticamente
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                venda.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Venda> listarVendas() {
        List<Venda> vendas = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VENDAS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date data = resultSet.getDate("data");
                String cliente = resultSet.getString("cliente");
                BigDecimal valorTotal = resultSet.getBigDecimal("valor_total");
                String formaPagamento = resultSet.getString("forma_pagamento");

                Venda venda = new Venda(data, cliente, valorTotal, formaPagamento);
                venda.setId(id); // Definindo o ID da venda
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendas;
    }
}