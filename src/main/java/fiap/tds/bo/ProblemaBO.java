package fiap.tds.bo;


import fiap.tds.beans.Problema;
import fiap.tds.dao.ProblemaDAO;

import java.sql.SQLException;
import java.util.List;

public class ProblemaBO {
    private ProblemaDAO problemaDAO;

    public ProblemaBO(ProblemaDAO problemaDAO) {
        this.problemaDAO = problemaDAO;
    }

    public void adicionarProblema(Problema problema) throws SQLException {
        problemaDAO.inserir(problema);
    }

    public List<Problema> listarProblemas() throws SQLException {
        return problemaDAO.listarTodos();
    }

    public Problema buscarProblemaPorId(int id) throws SQLException {
        return problemaDAO.buscarPorId(id);
    }

    public boolean atualizarProblema(Problema problema) throws SQLException {
        return problemaDAO.atualizar(problema);
    }

    public boolean excluirProblema(int id) throws SQLException {
        return problemaDAO.excluir(id);
    }
}
