package fiap.tds.dao;

import fiap.tds.beans.Problema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProblemaDAOImpl implements ProblemaDAO {
    private Connection conn;

    public ProblemaDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Problema problema) throws SQLException {
        String sql = "INSERT INTO TB_PROBLEMA (id_problema, id_questionario, id_solucao, nivel_de_prioridade, descricao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, problema.getIdProblema());
            stmt.setInt(2, problema.getQuestionario().getIdQuestionario());
            stmt.setInt(3, problema.getSolucao().getIdSolucao());
            stmt.setInt(4, problema.getNivel_de_prioridade());
            stmt.setString(5, problema.getDescricao());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Problema> listarTodos() throws SQLException {
        List<Problema> problemas = new ArrayList<>();
        String sql = "SELECT * FROM TB_PROBLEMA";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Problema problema = new Problema();
                problema.setIdProblema(rs.getInt("id_problema"));
                problema.setNivel_de_prioridade(rs.getInt("NIVEL_DE_PRIORIDADE"));
                problema.setDescricao(rs.getString("descricao"));
                problemas.add(problema);
            }
        }
        return problemas;
    }

    @Override
    public Problema buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM TB_PROBLEMA WHERE id_problema = ?";
        Problema problema = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    problema = new Problema();
                    problema.setIdProblema(rs.getInt("id_problema"));
                    problema.setNivel_de_prioridade(rs.getInt("NIVEL_DE_PRIORIDADE"));
                    problema.setDescricao(rs.getString("descricao"));
                }
            }
        }
        return problema;
    }

    @Override
    public boolean atualizar(Problema problema) throws SQLException {
        String sql = "UPDATE TB_PROBLEMA SET id_questionario = ?, nivel_de_prioridade = ?, descricao = ? WHERE id_problema = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, problema.getQuestionario().getIdQuestionario());
            ps.setInt(2, problema.getNivel_de_prioridade());
            ps.setString(3, problema.getDescricao());
            ps.setInt(4, problema.getIdProblema());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM TB_PROBLEMA WHERE id_problema = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
