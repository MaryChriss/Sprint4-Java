package fiap.tds.beans;

public class Veiculo {
    public int id_veiculo;
    private Cliente cliente;
    public String modelo_veiculo;
    public String marca_veiculo;
    public int ano_veiculo;
    public String placa_veiculo;

    public Veiculo() {
    }

    public Veiculo(int ano_veiculo, Cliente cliente, int id_veiculo, String marca_veiculo, String modelo_veiculo, String placa_veiculo) {
        this.ano_veiculo = ano_veiculo;
        this.cliente = cliente;
        this.id_veiculo = id_veiculo;
        this.marca_veiculo = marca_veiculo;
        this.modelo_veiculo = modelo_veiculo;
        this.placa_veiculo = placa_veiculo;
    }

    public Veiculo(int idVeiculo, Cliente cliente, String modelo, String marca, int ano, String placa) {
    }

    public int getAno_veiculo() {
        return ano_veiculo;
    }

    public void setAno_veiculo(int ano_veiculo) {
        this.ano_veiculo = ano_veiculo;
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

    public String getMarca_veiculo() {
        return marca_veiculo;
    }

    public void setMarca_veiculo(String marca_veiculo) {
        this.marca_veiculo = marca_veiculo;
    }

    public String getModelo_veiculo() {
        return modelo_veiculo;
    }

    public void setModelo_veiculo(String modelo_veiculo) {
        this.modelo_veiculo = modelo_veiculo;
    }

    public String getPlaca_veiculo() {
        return placa_veiculo;
    }

    public void setPlaca_veiculo(String placa_veiculo) {
        this.placa_veiculo = placa_veiculo;
    }
}
