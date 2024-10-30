package fiap.tds.beans;

public class Cliente {
    private int id_cliente;
    public String nome_cliente;
    public String endereco_cliente;
    public String telefone_cliente;
    public String email_cliente;

    public Cliente() {
    }

    public Cliente(int id_cliente, String nome_cliente, String telefone_cliente, String email_cliente, String endereco_cliente) {
        this.id_cliente = id_cliente;
        this.nome_cliente = nome_cliente;
        this.telefone_cliente = telefone_cliente;
        this.email_cliente = email_cliente;
        this.endereco_cliente = endereco_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getEndereco_cliente() {
        return endereco_cliente;
    }

    public void setEndereco_cliente(String endereco_cliente) {
        this.endereco_cliente = endereco_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getTelefone_cliente() {
        return telefone_cliente;
    }

    public void setTelefone_cliente(String telefone_cliente) {
        this.telefone_cliente = telefone_cliente;
    }
}

