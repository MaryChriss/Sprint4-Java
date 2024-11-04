package fiap.tds.beans;

public class Solucao {
    private int idSolucao;
    private int idPerguntaResposta;
    private String problema;
    private String solucaoFinal;

    public Solucao() {}

    public Solucao(int idSolucao, int idPerguntaResposta, String problema, String solucaoFinal) {
        this.idSolucao = idSolucao;
        this.idPerguntaResposta = idPerguntaResposta;
        this.problema = problema;
        this.solucaoFinal = solucaoFinal;
    }

    public int getIdSolucao() {
        return idSolucao;
    }

    public void setIdSolucao(int idSolucao) {
        this.idSolucao = idSolucao;
    }

    public int getIdPerguntaResposta() {
        return idPerguntaResposta;
    }

    public void setIdPerguntaResposta(int idPerguntaResposta) {
        this.idPerguntaResposta = idPerguntaResposta;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getSolucaoFinal() {
        return solucaoFinal;
    }

    public void setSolucaoFinal(String solucaoFinal) {
        this.solucaoFinal = solucaoFinal;
    }
}
