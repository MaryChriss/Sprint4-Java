package fiap.tds.beans;

public class Cliente {
    private Integer id_cliente;
    private String nome_cliente;
    private String endereco;
    private String telefone;
    private String email;

    // Construtor padrão
    public Cliente() {
    }

    public Cliente(String email, String endereco, String nome_cliente, String telefone) {
        this.email = email;
        this.endereco = endereco;
        this.nome_cliente = nome_cliente;
        this.telefone = telefone;
    }

    // Construtor com todos os parâmetros

    public Cliente(String email, String endereco, Integer id_cliente, String nome_cliente, String telefone) {
        this.email = email;
        this.endereco = endereco;
        this.id_cliente = id_cliente;
        this.nome_cliente = nome_cliente;
        this.telefone = telefone;
    }

    // Construtor com ID para busca ou exclusão
    public Cliente(int idCliente) {
        this.id_cliente = idCliente;
    }

    // Getters e Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
