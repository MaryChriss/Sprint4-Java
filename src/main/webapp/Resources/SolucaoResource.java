package fiap.resources;

import fiap.tds.beans.Solucao;
import fiap.tds.bo.SolucaoBO;
import fiap.tds.dao.SolucaoDAOImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/solucao")
public class SolucaoResource {

    private SolucaoBO solucaoBO = new SolucaoBO();

    // Adicionar nova solução (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarSolucao(Solucao solucao, @Context UriInfo uriInfo) {
        try {
            solucaoBO.adicionarSolucao(solucao);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(solucao.getIdSolucao()));
            return Response.created(builder.build()).entity("Solução adicionada com sucesso.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar solução.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Atualizar solução (PUT)
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarSolucao(@PathParam("id") int id, Solucao solucao) {
        try {
            solucao.setIdSolucao(id); // Garante que o id da URL seja o do objeto atualizado
            solucaoBO.atualizarSolucao(solucao);
            return Response.ok("Solução atualizada com sucesso.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar solução.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Excluir solução (DELETE)
    @DELETE
    @Path("/{id}")
    public Response excluirSolucao(@PathParam("id") int id) {
        try {
            solucaoBO.excluirSolucao(id);
            return Response.ok("Solução excluída com sucesso.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir solução.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Buscar solução por ID (GET)
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarSolucaoPorId(@PathParam("id") int id) {
        try {
            Solucao solucao = solucaoBO.buscarSolucaoPorId(id);
            if (solucao != null) {
                return Response.ok(solucao).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Solução não encontrada.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar solução.").build();
        }
    }

    // Listar todas as soluções (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarSolucoes() {
        try {
            List<Solucao> solucoes = solucaoBO.listarSolucoes();
            return Response.ok(solucoes).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar soluções.").build();
        }
    }
}
