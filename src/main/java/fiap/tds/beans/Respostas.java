package fiap.tds.beans;

public class Respostas {
    private int idRespostas;
    private String resposta;

    public Respostas() {}

    public Respostas(int idRespostas, String resposta) {
        this.idRespostas = idRespostas;
        this.resposta = resposta;
    }

    public int getIdRespostas() {
        return idRespostas;
    }

    public void setIdRespostas(int idRespostas) {
        this.idRespostas = idRespostas;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}
