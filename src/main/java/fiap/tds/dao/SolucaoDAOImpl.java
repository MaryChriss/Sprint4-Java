package fiap.tds.dao;

import fiap.tds.beans.Solucao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolucaoDAOImpl implements SolucaoDAO {
    private Connection conn;

    public SolucaoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void adicionarSolucao(Solucao solucao) throws SQLException {
        String sql = "INSERT INTO TB_SOLUCAO (ID_SOLUCAO, ID_PERGUNTA_RESPOSTA, PROBLEMA, SOLUCAO_FINAL) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, solucao.getIdSolucao());
            stmt.setInt(2, solucao.getIdPerguntaResposta());
            stmt.setString(3, solucao.getProblema());
            stmt.setString(4, solucao.getSolucaoFinal());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Solucao> listarSolucoes() throws SQLException {
        String sql = "SELECT * FROM TB_SOLUCAO";
        List<Solucao> solucoes = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Solucao solucao = new Solucao(
                        rs.getInt("ID_SOLUCAO"),
                        rs.getInt("ID_PERGUNTA_RESPOSTA"),
                        rs.getString("PROBLEMA"),
                        rs.getString("SOLUCAO_FINAL")
                );
                solucoes.add(solucao);
            }
        }
        return solucoes;
    }

    @Override
    public Solucao buscarSolucaoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM TB_SOLUCAO WHERE ID_SOLUCAO = ?";
        Solucao solucao = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    solucao = new Solucao(
                            rs.getInt("ID_SOLUCAO"),
                            rs.getInt("ID_PERGUNTA_RESPOSTA"),
                            rs.getString("PROBLEMA"),
                            rs.getString("SOLUCAO_FINAL")
                    );
                }
            }
        }
        return solucao;
    }

    @Override
    public boolean atualizarSolucao(Solucao solucao) throws SQLException {
        String sql = "UPDATE TB_SOLUCAO SET ID_PERGUNTA_RESPOSTA = ?, PROBLEMA = ?, SOLUCAO_FINAL = ? WHERE ID_SOLUCAO = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, solucao.getIdPerguntaResposta());
            stmt.setString(2, solucao.getProblema());
            stmt.setString(3, solucao.getSolucaoFinal());
            stmt.setInt(4, solucao.getIdSolucao());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluirSolucao(int id) throws SQLException {
        String sql = "DELETE FROM TB_SOLUCAO WHERE ID_SOLUCAO = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
