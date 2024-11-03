package fiap.tds.resources;

import fiap.tds.beans.Cliente;
import fiap.tds.beans.Pecas;
import fiap.tds.bo.PecasBO;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/pecas")
public class PecasResource {

    private PecasBO pecasBO = new PecasBO();

    // Inserir nova peça (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarPeca(Pecas peca, @Context UriInfo uriInfo) throws SQLException {
        pecasBO.adicionarPeca(peca);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(peca.getId_peca()));
        return Response.created(builder.build()).build();
    }

    // Atualizar peça existente (PUT)
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPeca(@PathParam("id") int idPeca, Pecas peca) {
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
    @Path("/{id}")
    public Response excluirPeca(@PathParam("id") int idPeca) {
        try {
            pecasBO.excluirPeca(idPeca);
            return Response.ok("Peça excluída com sucesso.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir peça.").build();
        }
    }

    // Buscar peça por ID (GET)
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPecaPorId(@PathParam("id") int idPeca) {
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
    public ArrayList<Pecas> listarPecas() throws SQLException {
        return (ArrayList<Pecas>) pecasBO.listarPecas();
    }

}
