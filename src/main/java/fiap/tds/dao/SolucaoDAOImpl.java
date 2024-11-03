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
        String sql = "INSERT INTO TB_SOLUCAO (id_solucao, contato_mecanico_solucao, localizacao_oficina_solucao, solucao_final_solucao) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, solucao.getIdSolucao());
            stmt.setString(2, solucao.getContato_mecanico_solucao());
            stmt.setString(3, solucao.getLocalizacao_oficina_solucao());
            stmt.setString(4, solucao.getSolucao_final_solucao());  // Mantendo como String
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualizarSolucao(Solucao solucao) throws SQLException {
        String sql = "UPDATE TB_SOLUCAO SET contato_mecanico_solucao = ?, localizacao_oficina_solucao = ?, solucao_final_solucao = ? WHERE id_solucao = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, solucao.getContato_mecanico_solucao());
            stmt.setString(2, solucao.getLocalizacao_oficina_solucao());
            stmt.setString(3, solucao.getSolucao_final_solucao());  // Mantendo como String
            stmt.setInt(4, solucao.getIdSolucao());
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluirSolucao(int idSolucao) throws SQLException {
        String sql = "DELETE FROM TB_SOLUCAO WHERE id_solucao = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSolucao);
            stmt.executeUpdate();
        }
    }

    @Override
    public Solucao buscarSolucaoPorId(int idSolucao) throws SQLException {
        String sql = "SELECT * FROM TB_SOLUCAO WHERE id_solucao = ?";
        Solucao solucao = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSolucao);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    solucao = new Solucao(
                            rs.getString("contato_mecanico_solucao"),
                            rs.getInt("id_solucao"),
                            rs.getString("localizacao_oficina_solucao"),
                            rs.getString("solucao_final_solucao")  // Mantendo como String
                    );
                }
            }
        }

        return solucao;
    }

    @Override
    public List<Solucao> listarSolucoes() throws SQLException {
        String sql = "SELECT * FROM TB_SOLUCAO";
        List<Solucao> solucoes = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Solucao solucao = new Solucao(
                        rs.getString("contato_mecanico_solucao"),
                        rs.getInt("id_solucao"),
                        rs.getString("localizacao_oficina_solucao"),
                        rs.getString("solucao_final_solucao")  // Mantendo como String
                );
                solucoes.add(solucao);
            }
        }

        return solucoes;
    }
}
