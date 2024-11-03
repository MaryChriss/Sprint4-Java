package fiap.tds.dao;

import fiap.tds.beans.Cliente;
import fiap.tds.beans.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAOImpl implements VeiculoDAO {
    private Connection conn;

    public VeiculoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void adicionarVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO TB_VEICULO (id_veiculo, id_cliente, modelo, marca, ano) VALUES (veiculo_seq.NEXTVAL, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, veiculo.getCliente().getId_cliente());
            pstmt.setString(2, veiculo.getModelo());
            pstmt.setString(3, veiculo.getMarca());
            pstmt.setInt(4, veiculo.getAno());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE TB_VEICULO SET modelo = ?, marca = ?, ano= ?, id_cliente = ? WHERE id_veiculo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setInt(3, veiculo.getAno());
            stmt.setInt(4, veiculo.getCliente().getId_cliente());
            stmt.setInt(5, veiculo.getId_veiculo());
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluirVeiculo(int idVeiculo) throws SQLException {
        String sql = "DELETE FROM TB_VEICULO WHERE id_veiculo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVeiculo);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Veiculo> listarVeiculos() throws SQLException {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM TB_VEICULO v JOIN TB_CLIENTE c ON v.id_cliente = c.id_cliente";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getInt("id_cliente"),
                        rs.getString("nome_cliente"),
                        rs.getString("telefone")
                );

                Veiculo veiculo = new Veiculo(
                        rs.getInt("id_veiculo"),
                        cliente,
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getInt("ano")
                );

                veiculos.add(veiculo);
            }
        }
        return veiculos;
    }

    public Veiculo buscarVeiculoPorId(int idVeiculo) throws SQLException {
        String sql = "SELECT * FROM TB_VEICULO WHERE id_veiculo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVeiculo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
                Cliente cliente = clienteDAO.buscarClienteId(rs.getInt("id_cliente"));
                return new Veiculo(
                        rs.getInt("id_veiculo"),
                        cliente,
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getInt("ano")
                );
            }
        }
        return null;
    }
}
