package fiap.tds.dao;

import fiap.tds.beans.Questionario;

import java.sql.SQLException;
import java.util.List;

public interface QuestionarioDAO {
    public void inserir(Questionario questionario);
    public List<Questionario> listarTodos();
    public boolean excluir(int id) throws SQLException;
    public boolean atualizarQuestionario(Questionario questionario) throws  SQLException;
    public Questionario buscarId(int id) throws SQLException;
}
