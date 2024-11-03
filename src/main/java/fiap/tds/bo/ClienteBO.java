package fiap.tds.bo;

import fiap.tds.beans.Cliente;
import fiap.tds.dao.ClienteDAO;
import fiap.tds.dao.ClienteDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class ClienteBO {

    private ClienteDAO clienteDAO;

    public ClienteBO() throws SQLException {
        this.clienteDAO = new ClienteDAOImpl();
    }

    public Cliente inserir(Cliente cliente) throws Exception {
        //if (clienteDAO.buscarClienteId(cliente.getId_cliente()) != null) {
        //    throw new Exception("Cliente já existe com este ID");
        //    cliente.setId_cliente(null);
        //}
        return clienteDAO.inserirCliente(cliente);
    }

    public boolean excluir(int id) throws SQLException {
        Cliente cliente = clienteDAO.buscarClienteId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado para exclusão.");
            return false;
        }
        return clienteDAO.excluirCliente(id);
    }

    public boolean atualizar(Cliente cliente) throws SQLException {
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            System.out.println("Email não pode ser vazio.");
            return false;
        } else if (cliente.getNome_cliente() == null  || cliente.getNome_cliente().isEmpty()) {
            System.out.println("Nome não pode estar vazio");
            return false;
        } else if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            System.out.println("Telefone não pode estar vazio");
            return false;
        }
        return clienteDAO.atualizarCliente(cliente);
    }

    public List<Cliente> listar() throws SQLException {
        return clienteDAO.listarCliente();
    }

    public Cliente buscarPorId(int id) throws SQLException {
        Cliente cliente = clienteDAO.buscarClienteId(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        return cliente;
    }
}

