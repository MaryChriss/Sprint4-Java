package fiap.tds.beans;

public class Questionario {
    private int idQuestionario;
    private Cliente cliente;
    private Veiculo veiculo;
    public String fumaca_questionario;
    public String temperatura_elevada_questionario;
    public String perca_de_forca_questionario;
    public String barulho_suspensao_questionario;
    public String vibracao_veiculo_questionario;
    public String problema_amortecedor_questionario;
    public String recarga_bateria_questionario;

    public Questionario() {
    }

    public Questionario(String barulho_suspensao_questionario, Cliente cliente, String fumaca_questionario, int idQuestionario, String perca_de_forca_questionario, String problema_amortecedor_questionario, String recarga_bateria_questionario, String temperatura_elevada_questionario, Veiculo veiculo, String vibracao_veiculo_questionario) {
        this.barulho_suspensao_questionario = barulho_suspensao_questionario;
        this.cliente = cliente;
        this.fumaca_questionario = fumaca_questionario;
        this.idQuestionario = idQuestionario;
        this.perca_de_forca_questionario = perca_de_forca_questionario;
        this.problema_amortecedor_questionario = problema_amortecedor_questionario;
        this.recarga_bateria_questionario = recarga_bateria_questionario;
        this.temperatura_elevada_questionario = temperatura_elevada_questionario;
        this.veiculo = veiculo;
        this.vibracao_veiculo_questionario = vibracao_veiculo_questionario;
    }

    public String getBarulho_suspensao_questionario() {
        return barulho_suspensao_questionario;
    }

    public void setBarulho_suspensao_questionario(String barulho_suspensao_questionario) {
        this.barulho_suspensao_questionario = barulho_suspensao_questionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFumaca_questionario() {
        return fumaca_questionario;
    }

    public void setFumaca_questionario(String fumaca_questionario) {
        this.fumaca_questionario = fumaca_questionario;
    }

    public int getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(int idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public String getPerca_de_forca_questionario() {
        return perca_de_forca_questionario;
    }

    public void setPerca_de_forca_questionario(String perca_de_forca_questionario) {
        this.perca_de_forca_questionario = perca_de_forca_questionario;
    }

    public String getProblema_amortecedor_questionario() {
        return problema_amortecedor_questionario;
    }

    public void setProblema_amortecedor_questionario(String problema_amortecedor_questionario) {
        this.problema_amortecedor_questionario = problema_amortecedor_questionario;
    }

    public String getRecarga_bateria_questionario() {
        return recarga_bateria_questionario;
    }

    public void setRecarga_bateria_questionario(String recarga_bateria_questionario) {
        this.recarga_bateria_questionario = recarga_bateria_questionario;
    }

    public String getTemperatura_elevada_questionario() {
        return temperatura_elevada_questionario;
    }

    public void setTemperatura_elevada_questionario(String temperatura_elevada_questionario) {
        this.temperatura_elevada_questionario = temperatura_elevada_questionario;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getVibracao_veiculo_questionario() {
        return vibracao_veiculo_questionario;
    }

    public void setVibracao_veiculo_questionario(String vibracao_veiculo_questionario) {
        this.vibracao_veiculo_questionario = vibracao_veiculo_questionario;
    }
}
