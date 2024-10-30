package fiap.tds.beans;

public class Problema {
    private int idProblema;
    public int nivel_de_prioridade;
    private String descricao;
    private Solucao solucao;
    private Questionario questionario;

    public Problema() {
    }

    public Problema(String descricao, int idProblema, int nivel_de_prioridade, Questionario questionario, Solucao solucao) {
        this.descricao = descricao;
        this.idProblema = idProblema;
        this.nivel_de_prioridade = nivel_de_prioridade;
        this.questionario = questionario;
        this.solucao = solucao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }

    public int getNivel_de_prioridade() {
        return nivel_de_prioridade;
    }

    public void setNivel_de_prioridade(int nivel_de_prioridade) {
        this.nivel_de_prioridade = nivel_de_prioridade;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public Solucao getSolucao() {
        return solucao;
    }

    public void setSolucao(Solucao solucao) {
        this.solucao = solucao;
    }
}
