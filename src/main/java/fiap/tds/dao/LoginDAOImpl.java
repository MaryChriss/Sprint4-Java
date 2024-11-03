package fiap.tds.dao;

import fiap.tds.Credenciais.Credenciais;
import fiap.tds.beans.Cliente;
import fiap.tds.beans.Login;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {

    private Connection conn;
    private ClienteDAOImpl clienteDAO;

    public LoginDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(Credenciais.url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        conn = ods.getConnection();
        clienteDAO = new ClienteDAOImpl();
    }

    @Override
    public Login inserirLogin(Login login) {
        String sql = "INSERT INTO TB_LOGIN (id_login, id_cliente, email, senha) VALUES (SEQ_ID_LOGIN.NEXTVAL, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, new String[] { "id_login" })) {
            ps.setInt(1, login.getId_cliente());
            ps.setString(2, login.getEmail_login());
            ps.setString(3, login.getSenha_login());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    login.setId_login(generatedId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return login;
    }


    @Override
    public Login autenticar(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM TB_LOGIN WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idLogin = rs.getInt("id_login");
                int idCliente = rs.getInt("id_cliente");
                String senhaRetornada = rs.getString("senha");
                Cliente cliente = clienteDAO.buscarClienteId(idCliente);
                return new Login(cliente, email, idLogin, senhaRetornada);
            }
            return null;
        }
    }

    @Override
    public boolean atualizarLogin(int idCliente, Cliente cliente, String email, String senha) {
        String sql = "UPDATE TB_LOGIN SET email = ?, senha = ?  WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.setInt(3, idCliente);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean excluirLogin(int idCliente) {
        String sql = "DELETE FROM TB_LOGIN WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Login> listarLogins() throws SQLException {
        String sql = "SELECT * FROM TB_LOGIN";
        List<Login> listaLogins = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                Cliente cliente = clienteDAO.buscarClienteId(idCliente);

                Login login = new Login(
                        cliente,
                        rs.getString("email"),
                        rs.getInt("id_login"),
                        rs.getString("senha")
                );
                listaLogins.add(login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLogins;
    }


    @Override
    public Login buscarLoginPorId(int idCliente) throws SQLException {
        String sql = "SELECT * FROM TB_LOGIN WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int clienteId = rs.getInt("id_cliente");
                Cliente cliente = clienteDAO.buscarClienteId(clienteId);
                return new Login(
                        cliente,
                        rs.getString("email"),
                        clienteId,
                        rs.getString("senha")
                );
            }
        }
        return null;
    }
}
