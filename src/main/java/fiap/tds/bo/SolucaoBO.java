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
        // Validação dos dados da solução
        if (solucao.getIdSolucao() <= 0) {
            throw new IllegalArgumentException("ID da solução inválido.");
        }
        if (solucao.getContato_mecanico_solucao() == null || solucao.getContato_mecanico_solucao().isEmpty()) {
            throw new IllegalArgumentException("Contato mecânico não pode ser vazio.");
        }
        if (solucao.getLocalizacao_oficina_solucao() == null || solucao.getLocalizacao_oficina_solucao().isEmpty()) {
            throw new IllegalArgumentException("Localização da oficina não pode ser vazia.");
        }

        solucaoDAO.adicionarSolucao(solucao);
    }

    public void atualizarSolucao(Solucao solucao) throws SQLException {
        if (solucao.getIdSolucao() <= 0) {
            throw new IllegalArgumentException("ID da solução inválido.");
        }
        if (solucao.getContato_mecanico_solucao() == null || solucao.getContato_mecanico_solucao().isEmpty()) {
            throw new IllegalArgumentException("Contato mecânico não pode ser vazio.");
        }
        if (solucao.getLocalizacao_oficina_solucao() == null || solucao.getLocalizacao_oficina_solucao().isEmpty()) {
            throw new IllegalArgumentException("Localização da oficina não pode ser vazia.");
        }

        solucaoDAO.atualizarSolucao(solucao);
    }

    public void excluirSolucao(int idSolucao) throws SQLException {
        if (idSolucao <= 0) {
            throw new IllegalArgumentException("ID da solução inválido.");
        }

        solucaoDAO.excluirSolucao(idSolucao);
    }

    public Solucao buscarSolucaoPorId(int idSolucao) throws SQLException {
        if (idSolucao <= 0) {
            throw new IllegalArgumentException("ID da solução inválido.");
        }
        return solucaoDAO.buscarSolucaoPorId(idSolucao);
    }

    public List<Solucao> listarSolucoes() throws SQLException {
        return solucaoDAO.listarSolucoes(); // Mudança para garantir a nomenclatura correta
    }
}
