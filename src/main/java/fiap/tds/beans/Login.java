package fiap.tds.beans;

public class Login{
    private Cliente cliente;
    private String senha_login;
    private String email_login;

    public Login() {
    }

    public Login(Cliente cliente, String email_login, String senha_login) {
        this.cliente = cliente;
        this.email_login = email_login;
        this.senha_login = senha_login;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEmail_login() {
        return email_login;
    }

    public void setEmail_login(String email_login) {
        this.email_login = email_login;
    }

    public String getSenha_login() {
        return senha_login;
    }

    public void setSenha_login(String senha_login) {
        this.senha_login = senha_login;
    }
}
