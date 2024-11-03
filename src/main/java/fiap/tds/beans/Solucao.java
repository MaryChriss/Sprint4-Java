package fiap.tds.beans;

public class Solucao {
    private int idSolucao;
    private String contato_mecanico_solucao;
    private String localizacao_oficina_solucao;
    private String solucao_final_solucao;

    public Solucao() {}

    public Solucao(String contato_mecanico_solucao, int idSolucao, String localizacao_oficina_solucao, String solucao_final_solucao) {
        this.contato_mecanico_solucao = contato_mecanico_solucao;
        this.idSolucao = idSolucao;
        this.localizacao_oficina_solucao = localizacao_oficina_solucao;
        this.solucao_final_solucao = solucao_final_solucao;
    }

    public String getContato_mecanico_solucao() {
        return contato_mecanico_solucao;
    }

    public void setContato_mecanico_solucao(String contato_mecanico_solucao) {
        this.contato_mecanico_solucao = contato_mecanico_solucao;
    }

    public int getIdSolucao() {
        return idSolucao;
    }

    public void setIdSolucao(int idSolucao) {
        this.idSolucao = idSolucao;
    }

    public String getLocalizacao_oficina_solucao() {
        return localizacao_oficina_solucao;
    }

    public void setLocalizacao_oficina_solucao(String localizacao_oficina_solucao) {
        this.localizacao_oficina_solucao = localizacao_oficina_solucao;
    }

    public String getSolucao_final_solucao() {
        return solucao_final_solucao;
    }

    public void setSolucao_final_solucao(String solucao_final_solucao) {
        this.solucao_final_solucao = solucao_final_solucao;
    }
}
