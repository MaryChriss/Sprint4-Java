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

@Path("/solucaoMaoDeObra")
public class SolucaoMaoDeObraResource {

    private SolucaoBO solucaoBO;

    public SolucaoMaoDeObraResource() throws SQLException {
        Connection conn = Credenciais.getConnection();  // Usando a classe Credenciais para obter a conexão
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
        solucao.setIdSolucao(id);  // Definir ID antes de atualizar
        solucaoBO.atualizarSolucao(solucao);
        return Response.ok("Solução atualizada com sucesso!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirSolucao(@PathParam("id") int id) throws SQLException {
        solucaoBO.excluirSolucao(id);
        return Response.ok("Solução excluída com sucesso!").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarSolucoes() throws SQLException {
        List<Solucao> solucoes = solucaoBO.listarSolucoes();
        return Response.ok(solucoes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarSolucaoPorId(@PathParam("id") int id) throws SQLException {
        Solucao solucao = solucaoBO.buscarSolucaoPorId(id);
        if (solucao == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Solução não encontrada").build();
        }
        return Response.ok(solucao).build();
    }
}
