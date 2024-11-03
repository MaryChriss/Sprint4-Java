package fiap.tds.beans;

public class SolucaoMaoDeObra {
    private int id_mao_de_bra;
    private float preco_Mao_de_Obra;
    private String nome_servico_mao_de_obra;

    public SolucaoMaoDeObra() {
    }

    public SolucaoMaoDeObra(float preco_Mao_de_Obra, String nome_servico_mao_de_obra, int id_mao_de_bra) {
        this.preco_Mao_de_Obra = preco_Mao_de_Obra;
        this.nome_servico_mao_de_obra = nome_servico_mao_de_obra;
        this.id_mao_de_bra = id_mao_de_bra;
    }

    public int getId_mao_de_bra() {
        return id_mao_de_bra;
    }

    public void setId_mao_de_bra(int id_mao_de_bra) {
        this.id_mao_de_bra = id_mao_de_bra;
    }

    public String getNome_servico_mao_de_obra() {
        return nome_servico_mao_de_obra;
    }

    public void setNome_servico_mao_de_obra(String nome_servico_mao_de_obra) {
        this.nome_servico_mao_de_obra = nome_servico_mao_de_obra;
    }

    public float getPreco_Mao_de_Obra() {
        return preco_Mao_de_Obra;
    }

    public void setPreco_Mao_de_Obra(float preco_Mao_de_Obra) {
        this.preco_Mao_de_Obra = preco_Mao_de_Obra;
    }
}
