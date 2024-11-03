package fiap.tds.bo;

import fiap.tds.beans.Pecas;
import fiap.tds.dao.PecasDAO;
import fiap.tds.dao.PecasDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class PecasBO {

    private PecasDAO pecasDAO;

    public PecasBO() {
        this.pecasDAO = new PecasDAOImpl(); // Inicializa o DAO
    }

    public void adicionarPeca(Pecas peca) throws SQLException {
        if (peca.getNome_peca() == null || peca.getNome_peca().isEmpty()) {
            throw new IllegalArgumentException("O nome da peça não pode ser vazio.");
        }
        if (peca.getPreco_peca() == 0) { // Verifica se o preço não foi definido
            throw new IllegalArgumentException("O preço da peça não pode ser zero.");
        }
        pecasDAO.adicionarPeca(peca);
    }

    public void atualizarPeca(Pecas peca) throws SQLException {
        pecasDAO.atualizarPeca(peca);
    }

    public void excluirPeca(int idPeca) throws SQLException {
        pecasDAO.excluirPeca(idPeca);
    }

    public Pecas buscarPecaPorId(int idPeca) throws SQLException {
        return pecasDAO.buscarPecaPorId(idPeca);
    }

    public List<Pecas> listarPecas() throws SQLException {
        return pecasDAO.listarPecas();
    }
}
