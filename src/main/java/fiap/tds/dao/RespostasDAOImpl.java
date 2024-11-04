package fiap.tds.dao;

import fiap.tds.beans.Respostas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RespostasDAOImpl implements RespostasDAO {
    private Connection connection;

    public RespostasDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void adicionarResposta(Respostas resposta) throws SQLException {
        String sql = "INSERT INTO TB_RESPOSTAS (ID_RESPOSTAS, RESPOSTAS) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, resposta.getIdRespostas());
            ps.setString(2, resposta.getResposta());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Respostas> listarRespostas() throws SQLException {
        List<Respostas> respostas = new ArrayList<>();
        String sql = "SELECT * FROM TB_RESPOSTAS";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Respostas resposta = new Respostas(
                        rs.getInt("ID_RESPOSTAS"),
                        rs.getString("RESPOSTAS")
                );
                respostas.add(resposta);
            }
        }
        return respostas;
    }

    @Override
    public Respostas buscarRespostaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM TB_RESPOSTAS WHERE ID_RESPOSTAS = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Respostas(
                            rs.getInt("ID_RESPOSTAS"),
                            rs.getString("RESPOSTAS")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean atualizarResposta(Respostas resposta) throws SQLException {
        String sql = "UPDATE TB_RESPOSTAS SET RESPOSTAS = ? WHERE ID_RESPOSTAS = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, resposta.getResposta());
            ps.setInt(2, resposta.getIdRespostas());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean excluirResposta(int id) throws SQLException {
        String sql = "DELETE FROM TB_RESPOSTAS WHERE ID_RESPOSTAS = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
