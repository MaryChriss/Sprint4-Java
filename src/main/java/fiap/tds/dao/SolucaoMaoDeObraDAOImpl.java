package fiap.tds.dao;


import fiap.tds.beans.SolucaoMaoDeObra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolucaoMaoDeObraDAOImpl implements MaoDeObraDAO {
    private Connection connection;

    public SolucaoMaoDeObraDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void adicionarSolucao(SolucaoMaoDeObra solucao) throws SQLException {
        String sql = "INSERT INTO TB_SOLUCAO_MAO_OBRA (id_mao_de_obra, preco_mao_de_obra, nome_servico_mao_de_obra) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, solucao.getId_mao_de_bra());
            ps.setFloat(2, solucao.getPreco_Mao_de_Obra());
            ps.setString(3, solucao.getNome_servico_mao_de_obra());
            ps.executeUpdate();
        }
    }

    @Override
    public List<SolucaoMaoDeObra> listarSolucao() throws SQLException {
        List<SolucaoMaoDeObra> solucoes = new ArrayList<>();
        String sql = "SELECT * FROM TB_SOLUCAO_MAO_OBRA";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SolucaoMaoDeObra solucao = new SolucaoMaoDeObra(
                        rs.getFloat("preco_mao_de_obra"),
                        rs.getString("servico_a_ser_efeito"),
                        rs.getInt("id_mao_de_obra")
                                );
                solucoes.add(solucao);
            }
        }
        return solucoes;
    }

    @Override
    public SolucaoMaoDeObra buscarSolucaoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM SolucaoMaoDeObra WHERE maodeObra = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new SolucaoMaoDeObra(
                            rs.getFloat("precoMaoDeObra"),
                            rs.getString("servicoAserfeito"),
                            rs.getInt("maodeObra")
                                        );
                }
            }
        }
        return null;
    }

    @Override
    public boolean atualizarSolucao(SolucaoMaoDeObra solucao) throws SQLException {
        String sql = "UPDATE TB_SOLUCAO_MAO_OBRA SET preco_mao_de_obra = ?,  servico_a_ser_efeito = ? WHERE id_mao_de_obra = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setFloat(1, solucao.getPreco_Mao_de_Obra());
            ps.setString(2, solucao.getNome_servico_mao_de_obra());
            ps.setInt(3, solucao.getId_mao_de_bra());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean excluirSolucao(int id) throws SQLException {
        String sql = "DELETE FROM TB_SOLUCAO_MAO_OBRA WHERE id_mao_de_obra = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
