package fiap.tds.dao;

import fiap.tds.beans.Cliente;
import fiap.tds.beans.Questionario;
import fiap.tds.beans.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionarioDAOImpl implements QuestionarioDAO {
    private Connection conn;

    public QuestionarioDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Questionario questionario) {
        String sql = "INSERT INTO TB_QUESTIONARIO (id_questionario, id_cliente, id_veiculo, fumaca, temperatura_elevada, perda_de_forca, barulho_suspensao, vibracao_veiculo, problema_amortecedor, recarga_bateria)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, questionario.getIdQuestionario());
            stmt.setInt(2, questionario.getCliente().getId_cliente());
            stmt.setInt(3, questionario.getVeiculo().getId_veiculo());
            stmt.setString(4, questionario.getFumaca_questionario());
            stmt.setString(5, questionario.getTemperatura_elevada_questionario());
            stmt.setString(6, questionario.getPerca_de_forca_questionario());
            stmt.setString(7, questionario.getBarulho_suspensao_questionario());
            stmt.setString(8, questionario.getVibracao_veiculo_questionario());
            stmt.setString(9, questionario.getProblema_amortecedor_questionario());
            stmt.setString(10, questionario.getRecarga_bateria_questionario());

            stmt.executeUpdate();
            System.out.println("Questionário inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todos os questionários
    @Override
    public List<Questionario> listarTodos() {
        List<Questionario> questionarios = new ArrayList<>();
        String sql = "SELECT * FROM TB_QUESTIONARIO";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Questionario questionario = new Questionario();
                Cliente cliente = new Cliente();
                Veiculo veiculo = new Veiculo();

                questionario.setIdQuestionario(rs.getInt("id_questionario"));
                cliente.setId_cliente(rs.getInt("id_cliente"));
                questionario.setCliente(cliente);

                veiculo.setId_veiculo(rs.getInt("id_veiculo"));
                questionario.setVeiculo(veiculo);

                questionario.setFumaca_questionario(rs.getString("fumaca"));
                questionario.setTemperatura_elevada_questionario(rs.getString("temperatura_elevada"));
                questionario.setPerca_de_forca_questionario(rs.getString("perda_de_forca"));
                questionario.setBarulho_suspensao_questionario(rs.getString("barulho_suspensao"));
                questionario.setVibracao_veiculo_questionario(rs.getString("vibracao_veiculo"));
                questionario.setProblema_amortecedor_questionario(rs.getString("problema_amortecedor"));
                questionario.setRecarga_bateria_questionario(rs.getString("recarga_bateria"));

                questionarios.add(questionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionarios;
    }

    // Excluir um questionário pelo ID
    @Override
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM TB_QUESTIONARIO WHERE id_questionario = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Atualizar um questionário
    @Override
    public boolean atualizarQuestionario(Questionario questionario) throws SQLException {
        String sql = "UPDATE TB_QUESTIONARIO SET id_cliente = ?, id_veiculo = ?, fumaca = ?, temperatura_elevada = ?, perda_de_forca = ?, barulho_suspensao = ?, vibracao_veiculo = ?, problema_amortecedor = ?, recarga_bateria = ? WHERE id_questionario = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, questionario.getCliente().getId_cliente());
            ps.setInt(2, questionario.getVeiculo().getId_veiculo());
            ps.setString(3, questionario.getFumaca_questionario());
            ps.setString(4, questionario.getTemperatura_elevada_questionario());
            ps.setString(5, questionario.getPerca_de_forca_questionario());
            ps.setString(6, questionario.getBarulho_suspensao_questionario());
            ps.setString(7, questionario.getVibracao_veiculo_questionario());
            ps.setString(8, questionario.getProblema_amortecedor_questionario());
            ps.setString(9, questionario.getRecarga_bateria_questionario());
            ps.setInt(10, questionario.getIdQuestionario());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Buscar questionário por ID
    @Override
    public Questionario buscarId(int id) throws SQLException {
        String sql = "SELECT * FROM TB_QUESTIONARIO WHERE id_questionario = ?";
        Questionario questionario = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                questionario = new Questionario();
                Cliente cliente = new Cliente();
                Veiculo veiculo = new Veiculo();

                questionario.setIdQuestionario(rs.getInt("id_questionario"));
                cliente.setId_cliente(rs.getInt("id_cliente"));
                questionario.setCliente(cliente);

                veiculo.setId_veiculo(rs.getInt("id_veiculo"));
                questionario.setVeiculo(veiculo);

                questionario.setFumaca_questionario(rs.getString("fumaca"));
                questionario.setTemperatura_elevada_questionario(rs.getString("temperatura_elevada"));
                questionario.setPerca_de_forca_questionario(rs.getString("perda_de_forca"));
                questionario.setBarulho_suspensao_questionario(rs.getString("barulho_suspensao"));
                questionario.setVibracao_veiculo_questionario(rs.getString("vibracao_veiculo"));
                questionario.setProblema_amortecedor_questionario(rs.getString("problema_amortecedor"));
                questionario.setRecarga_bateria_questionario(rs.getString("recarga_bateria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionario;
    }
}
