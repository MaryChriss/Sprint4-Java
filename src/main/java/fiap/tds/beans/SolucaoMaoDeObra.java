package fiap.tds.beans;

public class SolucaoMaoDeObra {
    private int id_mao_de_bra;
    private float preco_Mao_de_Obra;

    public SolucaoMaoDeObra() {
    }

    public SolucaoMaoDeObra(float preco_Mao_de_Obra, int id_mao_de_bra) {
        this.preco_Mao_de_Obra = preco_Mao_de_Obra;
        this.id_mao_de_bra = id_mao_de_bra;
    }

    public int getId_mao_de_bra() {
        return id_mao_de_bra;
    }

    public void setId_mao_de_bra(int id_mao_de_bra) {
        this.id_mao_de_bra = id_mao_de_bra;
    }


    public float getPreco_Mao_de_Obra() {
        return preco_Mao_de_Obra;
    }

    public void setPreco_Mao_de_Obra(float preco_Mao_de_Obra) {
        this.preco_Mao_de_Obra = preco_Mao_de_Obra;
    }
}
