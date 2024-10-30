package fiap.tds.dao;

import fiap.tds.Credenciais.Credenciais;
import fiap.tds.beans.Cliente;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static fiap.tds.Credenciais.Credenciais.url;

public class LoginDAOImpl implements LoginDAO {

    private Connection conn;

    public LoginDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        conn = ods.getConnection();
    }

    @Override
    public boolean inserirLogin(int idLogin, Cliente cliente, String email_login, String senha_login) {
        String sql = "INSERT INTO TB_LOGIN (id_login, id_cliente, email_login, senha_login) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idLogin);
            stmt.setInt(2, cliente.getId_cliente());
            stmt.setString(3, email_login);
            stmt.setString(4, senha_login);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean autenticar(String email_login, String senha_login) throws SQLException {
        String sql = "SELECT * FROM TB_LOGIN WHERE email_login = ? AND senha_login = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email_login);
        stmt.setString(2, senha_login);
        ResultSet rs = stmt.executeQuery();

        return rs.next();
    }

    @Override
    public boolean atualizarLogin(int idLogin, Cliente cliente, String email_login, String senha_login) {
        String sql = "UPDATE TB_LOGIN SET email_login = ?, senha_login = ? WHERE id_login = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email_login);
            stmt.setString(2, senha_login);
            stmt.setInt(3, idLogin);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean excluirLogin(int idLogin) {
        String sql = "DELETE FROM TB_LOGIN WHERE id_login = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idLogin);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
