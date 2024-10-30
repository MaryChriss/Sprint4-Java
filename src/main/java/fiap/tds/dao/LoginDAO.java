package fiap.tds.dao;

import fiap.tds.beans.Cliente;

import java.sql.SQLException;

public interface LoginDAO {

    public boolean inserirLogin (int idLogin, Cliente cliente, String email_login, String senha_login);
    public boolean autenticar(String email_login, String senha_login) throws SQLException;
    public boolean atualizarLogin(int idLogin, Cliente cliente, String email_login, String senha_login);
    public boolean excluirLogin (int idLogin);
}
