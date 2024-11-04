package fiap.tds.bo;

import fiap.tds.beans.SolucaoMaoDeObra;
import fiap.tds.dao.MaoDeObraDAO;
import java.sql.SQLException;
import java.util.List;

public class SolucaoMaoDeObraBO {
    private MaoDeObraDAO dao;

    public SolucaoMaoDeObraBO(MaoDeObraDAO dao) {
        this.dao = dao;
    }

    public void adicionarSolucao(SolucaoMaoDeObra solucao) throws SQLException {
        dao.adicionarSolucao(solucao);
    }

    public List<SolucaoMaoDeObra> listarSolucao() throws SQLException {
        return dao.listarSolucao();
    }

    public SolucaoMaoDeObra buscarSolucaoPorId(int id) throws SQLException {
        return dao.buscarSolucaoPorId(id);
    }

    public boolean atualizarSolucao(SolucaoMaoDeObra solucao) throws SQLException {
        return dao.atualizarSolucao(solucao);
    }

    public boolean excluirSolucao(int id) throws SQLException {
        return dao.excluirSolucao(id);
    }
}
