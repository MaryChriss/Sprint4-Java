package fiap.tds.dao;


import fiap.tds.beans.Pecas;

import java.sql.SQLException;
import java.util.List;

public interface PecasDAO {
    void adicionarPeca(Pecas peca) throws SQLException;
    void atualizarPeca(Pecas peca) throws SQLException;
    void excluirPeca(int idPeca) throws SQLException;
    Pecas buscarPecaPorId(int idPeca) throws SQLException;
    List<Pecas> listarPecas() throws SQLException;
}
