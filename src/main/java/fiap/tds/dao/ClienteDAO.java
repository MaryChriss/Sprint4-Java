package fiap.tds.dao;

import fiap.tds.beans.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {

    public Cliente inserirCliente (Cliente cliente) throws SQLException;
    public boolean excluirCliente(int id) throws SQLException;
    public boolean atualizarCliente(Cliente cliente) throws  SQLException;
    public List<Cliente> listarCliente() throws SQLException;
    public Cliente buscarClienteId(int id) throws SQLException;
}
