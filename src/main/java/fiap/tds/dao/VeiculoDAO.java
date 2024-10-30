package fiap.tds.dao;


import fiap.tds.beans.Veiculo;

import java.sql.SQLException;
import java.util.List;

public interface VeiculoDAO {
    void adicionarVeiculo(Veiculo veiculo) throws SQLException;
    void atualizarVeiculo(Veiculo veiculo) throws SQLException;
    void excluirVeiculo(int id_veiculo) throws SQLException;
    List<Veiculo> listarVeiculos() throws SQLException;
    public Veiculo buscarVeiculoPorId(int id_veiculo) throws SQLException;
}

