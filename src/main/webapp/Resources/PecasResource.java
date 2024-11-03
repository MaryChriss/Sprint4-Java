package fiap.resources;

import fiap.tds.beans.Pecas;
import fiap.tds.bo.PecasBO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/pecas")
public class PecasResource {

    private PecasBO pecasBO = new PecasBO();

    // Inserir nova peça (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarPeca(Pecas peca, @Context UriInfo uriInfo) {
        try {
            pecasBO.adicionarPeca(peca);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(peca.getId_peca()));
            return Response.created(builder.build()).entity("Peça adicionada com sucesso.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar peça.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Atualizar peça existente (PUT)
    @PUT
    @Path("/{idPeca}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPeca(@PathParam("idPeca") int idPeca, Pecas peca) {
        try {
            peca.setId_peca(idPeca); // Garante que o id da URL seja o da peça
            pecasBO.atualizarPeca(peca);
            return Response.ok("Peça atualizada com sucesso.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar peça.").build();
        }
    }

    // Excluir peça (DELETE)
    @DELETE
    @Path("/{idPeca}")
    public Response excluirPeca(@PathParam("idPeca") int idPeca) {
        try {
            pecasBO.excluirPeca(idPeca);
            return Response.ok("Peça excluída com sucesso.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir peça.").build();
        }
    }

    // Buscar peça por ID (GET)
    @GET
    @Path("/{idPeca}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPecaPorId(@PathParam("idPeca") int idPeca) {
        try {
            Pecas peca = pecasBO.buscarPecaPorId(idPeca);
            if (peca != null) {
                return Response.ok(peca).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Peça não encontrada.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar peça.").build();
        }
    }

    // Listar todas as peças (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPecas() {
        try {
            List<Pecas> pecasList = pecasBO.listarPecas();
            return Response.ok(pecasList).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar peças.").build();
        }
    }
}
