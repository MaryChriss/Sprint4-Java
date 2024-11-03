package fiap.tds.dao;


import fiap.tds.beans.Problema;

import java.sql.SQLException;
import java.util.List;

public interface ProblemaDAO {
    void inserir(Problema problema) throws SQLException;
    List<Problema> listarTodos() throws SQLException;
    Problema buscarPorId(int id) throws SQLException;
    boolean atualizar(Problema problema) throws SQLException;
    boolean excluir(int id) throws SQLException;
}