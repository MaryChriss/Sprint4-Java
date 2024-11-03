package fiap.tds.beans;

public class Pecas {
    private int id_peca;
    private Solucao solucao;
    public String nome_peca;
    private int preco_peca;

    public Pecas() {
    }

    public Pecas(int id_peca, String nome_peca, int preco_peca, Solucao solucao) {
        this.id_peca = id_peca;
        this.nome_peca = nome_peca;
        this.preco_peca = preco_peca;
        this.solucao = solucao;
    }

    public Pecas(int idPeca, String nomePeca, String precoPeca) {
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

    public int getPreco_peca() {
        return preco_peca;
    }

    public void setPreco_peca(int preco_peca) {
        this.preco_peca = preco_peca;
    }

    public Solucao getSolucao() {
        return solucao;
    }

    public void setSolucao(Solucao solucao) {
        this.solucao = solucao;
    }
}
