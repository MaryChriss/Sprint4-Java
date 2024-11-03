package fiap.tds.dao;

import fiap.tds.Credenciais.Credenciais;
import fiap.tds.beans.Pecas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PecasDAOImpl implements PecasDAO {

    private Connection conn;

    public PecasDAOImpl() {
        try {
            this.conn = Credenciais.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    @Override
    public void adicionarPeca(Pecas peca) throws SQLException {
        String sql = "INSERT INTO TB_PECAS (id_peca, nome_peca, preco_peca) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, peca.getId_peca());
            stmt.setString(2, peca.getNome_peca());
            stmt.setInt(3, peca.getPreco_peca());
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualizarPeca(Pecas peca) throws SQLException {
        String sql = "UPDATE TB_PECAS SET nome_peca = ?, preco_peca = ? WHERE id_peca = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, peca.getNome_peca());
            stmt.setInt(2, peca.getPreco_peca());
            stmt.setInt(3, peca.getId_peca());
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluirPeca(int idPeca) throws SQLException {
        String sql = "DELETE FROM TB_PECAS WHERE id_peca = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPeca);
            stmt.executeUpdate();
        }
    }

    @Override
    public Pecas buscarPecaPorId(int idPeca) throws SQLException {
        String sql = "SELECT id_peca, nome_peca, preco_peca FROM TB_PECAS WHERE id_peca = ?";
        Pecas peca = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPeca);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                peca = new Pecas(rs.getInt("id_peca"), rs.getString("nome_peca"), rs.getString("preco_peca"));
            }
        }
        return peca;
    }


    @Override
    public List<Pecas> listarPecas() throws SQLException {
        String sql = "SELECT ID_PECA, NOME_PECA, PRECO_PECA FROM TB_PECAS";
        List<Pecas> pecasList = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pecas peca = new Pecas(rs.getInt("ID_PECA"), rs.getString("NOME_PECA"), rs.getString("PRECO_PECA"));
                pecasList.add(peca);
            }
        }
        return pecasList;
    }

}
