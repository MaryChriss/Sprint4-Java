package fiap.tds.bo;

import fiap.tds.beans.Respostas;
import fiap.tds.dao.RespostasDAO;
import java.sql.SQLException;
import java.util.List;

public class RespostasBO {
    private RespostasDAO dao;

    public RespostasBO(RespostasDAO dao) {
        this.dao = dao;
    }

    public void adicionarResposta(Respostas resposta) throws SQLException {
        dao.adicionarResposta(resposta);
    }

    public List<Respostas> listarRespostas() throws SQLException {
        return dao.listarRespostas();
    }

    public Respostas buscarRespostaPorId(int id) throws SQLException {
        return dao.buscarRespostaPorId(id);
    }

    public boolean atualizarResposta(Respostas resposta) throws SQLException {
        return dao.atualizarResposta(resposta);
    }

    public boolean excluirResposta(int id) throws SQLException {
        return dao.excluirResposta(id);
    }
}
