package fiap.tds.dao;

import fiap.tds.beans.Solucao;
import java.sql.SQLException;
import java.util.List;

public interface SolucaoDAO {
    void adicionarSolucao(Solucao solucao) throws SQLException;
    List<Solucao> listarSolucoes() throws SQLException;
    Solucao buscarSolucaoPorId(int id) throws SQLException;
    boolean atualizarSolucao(Solucao solucao) throws SQLException;
    boolean excluirSolucao(int id) throws SQLException;
}
