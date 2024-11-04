package fiap.tds.resources;

import fiap.tds.Credenciais.Credenciais;
import fiap.tds.beans.Solucao;
import fiap.tds.bo.SolucaoBO;
import fiap.tds.dao.SolucaoDAOImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/solucao")
public class SolucaoResource {

    private SolucaoBO solucaoBO;

    public SolucaoResource() throws SQLException {
        Connection conn = Credenciais.getConnection();
        this.solucaoBO = new SolucaoBO(new SolucaoDAOImpl(conn));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarSolucao(Solucao solucao, @Context UriInfo uriInfo) throws SQLException {
        solucaoBO.adicionarSolucao(solucao);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(solucao.getIdSolucao()));
        return Response.created(builder.build()).entity("Solução adicionada com sucesso!").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarSolucao(@PathParam("id") int id, Solucao solucao) throws SQLException {
        solucao.setIdSolucao(id);
        solucaoBO.atualizarSolucao(solucao);
        return Response.ok("Solução atualizada com sucesso!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirSolucao(@PathParam("id") int id) throws SQLException {
        solucaoBO.excluirSolucao(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Solucao> listarSolucoes() throws SQLException {
        return solucaoBO.listarSolucoes();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Solucao buscarSolucaoPorId(@PathParam("id") int id) throws SQLException {
        return solucaoBO.buscarSolucaoPorId(id);
    }
}
