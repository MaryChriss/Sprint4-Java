package fiap.tds.dao;


import fiap.tds.Credenciais.Credenciais;
import fiap.tds.beans.Cliente;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static fiap.tds.Credenciais.Credenciais.url;

public class ClienteDAOImpl implements ClienteDAO{

    private Connection conn;

    public ClienteDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        conn = ods.getConnection();
    }

    @Override
    public Cliente inserirCliente(Cliente cliente) {
        // Use a sequence para o ID
        String sql = "INSERT INTO TB_CLIENTE (id_cliente, nome_cliente, endereco, telefone, email) VALUES (SEQ_ID_CLIENTE.NEXTVAL, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[] { "id_cliente" })) {
            ps.setString(1, cliente.getNome_cliente());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEmail());
            ps.executeUpdate();


            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    cliente.setId_cliente(generatedId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    @Override
    public boolean excluirCliente(int id) throws SQLException {
        String sql = "DELETE FROM TB_CLIENTE WHERE id_cliente = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }

        return true;
    }

    @Override
    public boolean atualizarCliente(Cliente cliente) {
        String sql = "UPDATE TB_CLIENTE SET nome_cliente = ?, endereco = ?, telefone = ?, email = ? WHERE id_cliente = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, cliente.getNome_cliente());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEmail());
            ps.setInt(5, cliente.getId_cliente());

            // Executando a atualização
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage());
        }

        return true;
    }

    @Override
    public List<Cliente> listarCliente() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM TB_CLIENTE";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_cliente");
                String nome = rs.getString("nome_cliente");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                clientes.add(new Cliente(email, endereco, id,nome , telefone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }


    @Override
    public Cliente buscarClienteId(int id) throws SQLException {
        String sql = "SELECT * FROM TB_CLIENTE WHERE id_cliente = ?";
        Cliente cliente = null;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Integer idCliente = id;
                String nome = rs.getString("nome_cliente");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String endereco = rs.getString("endereco");

                cliente = new Cliente(email,endereco, idCliente, nome , telefone);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }
}