package fiap.tds.resources;

import fiap.tds.Credenciais.Credenciais;
import fiap.tds.beans.Veiculo;
import fiap.tds.bo.VeiculoBO;
import fiap.tds.dao.VeiculoDAOImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Path("/veiculo")
public class VeiculoResource {

    private VeiculoBO veiculoBO;

    public VeiculoResource() throws SQLException {
        Connection conn = Credenciais.getConnection();
        this.veiculoBO = new VeiculoBO(new VeiculoDAOImpl(conn));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarVeiculo(Veiculo veiculo, @Context UriInfo uriInfo) throws SQLException {
        veiculoBO.adicionarVeiculo(veiculo);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(veiculo.getId_veiculo()));
        return Response.created(builder.build()).entity("Veículo adicionado com sucesso!").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(@PathParam("id") int id, Veiculo veiculo) throws SQLException {
        veiculo.setId_veiculo(id);  // Definir ID antes de atualizar
        veiculoBO.atualizarVeiculo(veiculo);
        return Response.ok("Veículo atualizado com sucesso!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirVeiculo(@PathParam("id") int id) throws SQLException {
        veiculoBO.excluirVeiculo(id);
        return Response.ok("Veículo excluído com sucesso!").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVeiculos() throws SQLException {
        List<Veiculo> veiculos = veiculoBO.listarVeiculos();
        return Response.ok(veiculos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarVeiculoPorId(@PathParam("id") int id) throws SQLException {
        Veiculo veiculo = veiculoBO.buscarVeiculoPorId(id);
        if (veiculo == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado").build();
        }
        return Response.ok(veiculo).build();
    }

    @GET
    @Path("/getByClient/{id_cliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarVeiculoPorIdCliente(@PathParam("id_cliente") int id) throws SQLException {
        Veiculo veiculo = veiculoBO.buscarVeiculoPorIdCliente(id);
        if (veiculo == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado").build();
        }
        return Response.ok(veiculo).build();
    }
}
