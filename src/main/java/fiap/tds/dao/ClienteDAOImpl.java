package fiap.tds.dao;


import fiap.tds.Credenciais.Credenciais;
import fiap.tds.beans.Cliente;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public boolean inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO TB_CLIENTE (id_cliente, nome_cliente, endereco_cliente, telefone_cliente, email_cliente) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cliente.getId_cliente());
            ps.setString(2, cliente.getNome_cliente());
            ps.setString(3, cliente.getEndereco_cliente());
            ps.setString(4, cliente.getTelefone_cliente());
            ps.setString(5, cliente.getEmail_cliente());
            ps.execute();

        } catch (SQLException e) {
            if (conn == null) {
                System.err.println("Conexão nula!");
            }
            e.printStackTrace();
            return false;
        }

        return true;
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
        String sql = "UPDATE TB_CLIENTE SET nome_cliente = ?, endereco_cliente = ?, telefone_cliente = ?, email_cliente = ? WHERE id_cliente = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, cliente.getNome_cliente());
            ps.setString(2, cliente.getEndereco_cliente());
            ps.setString(3, cliente.getTelefone_cliente());
            ps.setString(4, cliente.getEmail_cliente());
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
                int id = rs.getInt("id_cliente");
                String nome = rs.getString("nome_cliente");
                String endereco = rs.getString("endereco_cliente");
                String telefone = rs.getString("telefone_cliente");
                String email = rs.getString("email_cliente");
                clientes.add(new Cliente(id, nome, endereco, telefone, email));
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
                String nome = rs.getString("nome_cliente");
                String telefone = rs.getString("telefone_cliente");
                String email = rs.getString("email_cliente");
                String endereco = rs.getString("endereco_cliente");

                cliente = new Cliente(id, nome, telefone, email, endereco);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }
}