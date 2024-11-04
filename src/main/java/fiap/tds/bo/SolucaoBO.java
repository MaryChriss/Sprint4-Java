package fiap.tds.bo;

import fiap.tds.beans.Solucao;
import fiap.tds.dao.SolucaoDAO;
import java.sql.SQLException;
import java.util.List;

public class SolucaoBO {
    private SolucaoDAO solucaoDAO;

    public SolucaoBO(SolucaoDAO solucaoDAO) {
        this.solucaoDAO = solucaoDAO;
    }

    public void adicionarSolucao(Solucao solucao) throws SQLException {
        // Validações adicionais (se necessário) antes de adicionar a solução
        if (solucao.getIdSolucao() <= 0) {
            throw new IllegalArgumentException("ID da solução inválido.");
        }
        solucaoDAO.adicionarSolucao(solucao);
    }

    public List<Solucao> listarSolucoes() throws SQLException {
        return solucaoDAO.listarSolucoes();
    }

    public Solucao buscarSolucaoPorId(int idSolucao) throws SQLException {
        if (idSolucao <= 0) {
            throw new IllegalArgumentException("ID da solução inválido.");
        }
        return solucaoDAO.buscarSolucaoPorId(idSolucao);
    }

    public boolean atualizarSolucao(Solucao solucao) throws SQLException {
        if (solucao.getIdSolucao() <= 0) {
            throw new IllegalArgumentException("ID da solução inválido.");
        }
        return solucaoDAO.atualizarSolucao(solucao);
    }

    public boolean excluirSolucao(int idSolucao) throws SQLException {
        if (idSolucao <= 0) {
            throw new IllegalArgumentException("ID da solução inválido.");
        }
        return solucaoDAO.excluirSolucao(idSolucao);
    }
}
