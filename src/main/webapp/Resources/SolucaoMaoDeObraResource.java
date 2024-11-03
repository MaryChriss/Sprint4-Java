package fiap.resources;

import fiap.tds.beans.SolucaoMaoDeObra;
import fiap.tds.bo.SolucaoMaoDeObraBO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/solucao-mao-de-obra")
public class SolucaoMaoDeObraResource {

    private SolucaoMaoDeObraBO solucaoMaoDeObraBO = new SolucaoMaoDeObraBO();

    // Adicionar nova solução de mão de obra (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarSolucaoMaoDeObra(SolucaoMaoDeObra solucao, @Context UriInfo uriInfo) {
        try {
            solucaoMaoDeObraBO.adicionarSolucao(solucao);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(solucao.getId_mao_de_bra()));
            return Response.created(builder.build()).entity("Solução de mão de obra adicionada com sucesso.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar solução de mão de obra.").build();
        }
    }

    // Atualizar solução de mão de obra (PUT)
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarSolucaoMaoDeObra(@PathParam("id") int id, SolucaoMaoDeObra solucao) {
        try {
            solucao.setId_mao_de_bra(id); // Garante que o id da URL seja o do objeto atualizado
            boolean atualizado = solucaoMaoDeObraBO.atualizarSolucao(solucao);
            if (atualizado) {
                return Response.ok("Solução de mão de obra atualizada com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Solução de mão de obra não encontrada.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar solução de mão de obra.").build();
        }
    }

    // Excluir solução de mão de obra (DELETE)
    @DELETE
    @Path("/{id}")
    public Response excluirSolucaoMaoDeObra(@PathParam("id") int id) {
        try {
            boolean excluido = solucaoMaoDeObraBO.excluirSolucao(id);
            if (excluido) {
                return Response.ok("Solução de mão de obra excluída com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Solução de mão de obra não encontrada.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir solução de mão de obra.").build();
        }
    }

    // Buscar solução de mão de obra por ID (GET)
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarSolucaoMaoDeObraPorId(@PathParam("id") int id) {
        try {
            SolucaoMaoDeObra solucao = solucaoMaoDeObraBO.buscarSolucaoPorId(id);
            if (solucao != null) {
                return Response.ok(solucao).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Solução de mão de obra não encontrada.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar solução de mão de obra.").build();
        }
    }

    // Listar todas as soluções de mão de obra (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarSolucoesMaoDeObra() {
        try {
            List<SolucaoMaoDeObra> solucoes = solucaoMaoDeObraBO.listarSolucao();
            return Response.ok(solucoes).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar soluções de mão de obra.").build();
        }
    }
}
