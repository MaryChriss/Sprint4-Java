package fiap.tds.dao;



import fiap.tds.beans.Solucao;

import java.sql.SQLException;
import java.util.List;

public interface SolucaoDAO {
    void adicionarSolucao(Solucao solucao) throws SQLException;
    void atualizarSolucao(Solucao solucao) throws SQLException;
    void excluirSolucao(int idSolucao) throws SQLException;
    Solucao buscarSolucaoPorId(int idSolucao) throws SQLException;
    List<Solucao> listarSolucoes() throws SQLException; // Alteração do nome para plural
}
