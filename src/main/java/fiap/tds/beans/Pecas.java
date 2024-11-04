package fiap.tds.beans;

public class Pecas {
    private int id_peca;
    private String nome_peca;
    private double preco_peca;

    public Pecas(int id_peca, String nome_peca, double preco_peca) {
        this.id_peca = id_peca;
        this.nome_peca = nome_peca;
        this.preco_peca = preco_peca;
    }

    public int getId_peca() {
        return id_peca;
    }

    public void setId_peca(int id_peca) {
        this.id_peca = id_peca;
    }

    public String getNome_peca() {
        return nome_peca;
    }

    public void setNome_peca(String nome_peca) {
        this.nome_peca = nome_peca;
    }

    public double getPreco_peca() {
        return preco_peca;
    }

    public void setPreco_peca(double preco_peca) {
        this.preco_peca = preco_peca;
    }
}
