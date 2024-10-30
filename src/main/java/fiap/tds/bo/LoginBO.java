package fiap.tds.bo;

import fiap.tds.dao.LoginDAO;
import fiap.tds.dao.LoginDAOImpl;

import java.sql.SQLException;

public class LoginBO {
    private LoginDAO loginDAO;

    public LoginBO() throws SQLException {
        this.loginDAO = new LoginDAOImpl();
    }

    public boolean autenticar(String email, String senha) throws SQLException {
        if (!validarEmail(email)) {
            throw new IllegalArgumentException("Email inválido. Certifique-se de que contém '@' e um domínio válido.");
        }

        if (!validarSenha(senha)) {
            throw new IllegalArgumentException("Senha inválida. A senha deve ter pelo menos 8 caracteres.");
        }

        return loginDAO.autenticar(email, senha);
    }

    private boolean validarEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean validarSenha(String senha) {
        return senha != null && senha.length() >= 8;
    }
}
