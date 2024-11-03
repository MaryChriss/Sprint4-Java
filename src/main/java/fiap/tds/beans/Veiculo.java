package fiap.tds.beans;

public class Veiculo {
    private int id_veiculo;
    private Cliente cliente;
    private String modelo;
    private String marca;
    private int ano;

    public Veiculo() {

    }

    public Veiculo(int id_veiculo, Cliente cliente, String modelo, String marca, int ano) {
        this.id_veiculo = id_veiculo;
        this.cliente = cliente;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(int id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
