package fiap.tds.beans;

public class Login {
    private int id_login;
    private int id_cliente;
    private Cliente cliente;
    private String senha_login;
    private String email_login;

    public Login() {}

    public Login(Cliente cliente, String email_login, int id_cliente, int id_login, String senha_login) {
        this.cliente = cliente;
        this.email_login = email_login;
        this.id_cliente = id_cliente;
        this.id_login = id_login;
        this.senha_login = senha_login;
    }

    public Login(Cliente cliente, String email_login, int id_login, String senha_login) {
        this.cliente = cliente;
        this.email_login = email_login;
        this.id_login = id_login;
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

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_login() {
        return id_login;
    }

    public void setId_login(int id_login) {
        this.id_login = id_login;
    }

    public String getSenha_login() {
        return senha_login;
    }

    public void setSenha_login(String senha_login) {
        this.senha_login = senha_login;
    }
}
