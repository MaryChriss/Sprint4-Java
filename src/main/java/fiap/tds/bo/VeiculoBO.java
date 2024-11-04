package fiap.tds.bo;

import fiap.tds.beans.Veiculo;
import fiap.tds.dao.VeiculoDAO;

import java.sql.SQLException;
import java.util.List;

public class VeiculoBO {

    private VeiculoDAO veiculoDAO;

    public VeiculoBO(VeiculoDAO veiculoDAO) {
        this.veiculoDAO = veiculoDAO;
    }

    public void adicionarVeiculo(Veiculo veiculo) throws SQLException {
        if (!validarModelo(veiculo.getModelo())) {
            throw new IllegalArgumentException("Modelo do veículo inválido.");
        }

        if (!validarMarca(veiculo.getMarca())) {
            throw new IllegalArgumentException("Marca do veículo inválida.");
        }

        if (!validarAno(veiculo.getAno())) {
            throw new IllegalArgumentException("Ano do veículo inválido. Deve ser entre 1886 e o ano atual.");
        }

        veiculoDAO.adicionarVeiculo(veiculo);
    }

    public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        if (!validarModelo(veiculo.getModelo())) {
            throw new IllegalArgumentException("Modelo do veículo inválido.");
        }

        if (!validarMarca(veiculo.getMarca())) {
            throw new IllegalArgumentException("Marca do veículo inválida.");
        }

        if (!validarAno(veiculo.getAno())) {
            throw new IllegalArgumentException("Ano do veículo inválido. Deve ser entre 1886 e o ano atual.");
        }

        veiculoDAO.atualizarVeiculo(veiculo);
    }

    public void excluirVeiculo(int idVeiculo) throws SQLException {
        if (idVeiculo <= 0) {
            throw new IllegalArgumentException("ID de veículo inválido.");
        }
        veiculoDAO.excluirVeiculo(idVeiculo);
    }

    public List<Veiculo> listarVeiculos() throws SQLException {
        return veiculoDAO.listarVeiculos();
    }

    public Veiculo buscarVeiculoPorId(int idVeiculo) throws SQLException {
        return veiculoDAO.buscarVeiculoPorId(idVeiculo);
    }

    // Métodos de validação
    private boolean validarModelo(String modelo) {
        return modelo != null && !modelo.trim().isEmpty();
    }

    private boolean validarMarca(String marca) {
        return marca != null && !marca.trim().isEmpty();
    }

    private boolean validarAno(int ano) {
        int anoAtual = java.time.Year.now().getValue();
        return ano >= 1886 && ano <= anoAtual;
    }

    public Veiculo buscarVeiculoPorIdCliente(int idCliente) throws SQLException {
        return veiculoDAO.buscarVeiculoPorIdCliente(idCliente);
    }
}
