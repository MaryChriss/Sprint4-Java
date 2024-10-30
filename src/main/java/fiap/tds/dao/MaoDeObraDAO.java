package fiap.tds.dao;


import fiap.tds.beans.SolucaoMaoDeObra;

import java.sql.SQLException;
import java.util.List;


public interface MaoDeObraDAO {
    void adicionarSolucao(SolucaoMaoDeObra solucao) throws SQLException;
    List<SolucaoMaoDeObra> listarSolucao() throws SQLException;
    SolucaoMaoDeObra buscarSolucaoPorId(int id) throws SQLException;
    boolean atualizarSolucao(SolucaoMaoDeObra solucao) throws SQLException;
    boolean excluirSolucao(int id) throws SQLException;
}
