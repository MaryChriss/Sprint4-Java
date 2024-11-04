package fiap.tds.resources;

import fiap.tds.Credenciais.Credenciais;
import fiap.tds.beans.SolucaoMaoDeObra;
import fiap.tds.bo.SolucaoMaoDeObraBO;
import fiap.tds.dao.SolucaoMaoDeObraDAOImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/solucaoMaoDeObra")
public class SolucaoMaoDeObraResource {

    private SolucaoMaoDeObraBO solucaoBO;

    public SolucaoMaoDeObraResource() throws SQLException {
        Connection conn = Credenciais.getConnection();
        this.solucaoBO = new SolucaoMaoDeObraBO(new SolucaoMaoDeObraDAOImpl(conn));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarSolucao(SolucaoMaoDeObra solucao, @Context UriInfo uriInfo) throws SQLException {
        solucaoBO.adicionarSolucao(solucao);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(solucao.getId_mao_de_bra()));
        return Response.created(builder.build()).entity("Solução adicionada com sucesso!").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarSolucao(@PathParam("id") int id, SolucaoMaoDeObra solucao) throws SQLException {
        solucao.setId_mao_de_bra(id);
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
        List<SolucaoMaoDeObra> solucoes = solucaoBO.listarSolucao();
        return Response.ok(solucoes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarSolucaoPorId(@PathParam("id") int id) throws SQLException {
        SolucaoMaoDeObra solucao = solucaoBO.buscarSolucaoPorId(id);
        if (solucao == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Solução não encontrada").build();
        }
        return Response.ok(solucao).build();
    }
}
