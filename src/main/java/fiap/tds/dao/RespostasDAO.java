package fiap.tds.dao;

import fiap.tds.beans.Respostas;
import java.sql.SQLException;
import java.util.List;

public interface RespostasDAO {
    void adicionarResposta(Respostas resposta) throws SQLException;
    List<Respostas> listarRespostas() throws SQLException;
    Respostas buscarRespostaPorId(int id) throws SQLException;
    boolean atualizarResposta(Respostas resposta) throws SQLException;
    boolean excluirResposta(int id) throws SQLException;
}
