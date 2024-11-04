package fiap.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/problema")
public class ProblemaResource {

    private ProblemaBO problemaBO = new ProblemaBO();


    // Adicionar novo problema (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarProblema(Problema problema, @Context UriInfo uriInfo) {
        try {
            problemaBO.adicionarProblema(problema);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(problema.getIdProblema()));
            return Response.created(builder.build()).entity("Problema adicionado com sucesso.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar problema.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Atualizar problema (PUT)
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarProblema(@PathParam("id") int id, Problema problema) {
        try {
            problema.setIdProblema(id); // Garante que o id da URL seja o do problema
            boolean atualizado = problemaBO.atualizarProblema(problema);
            if (atualizado) {
                return Response.ok("Problema atualizado com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Problema não encontrado.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar problema.").build();
        }
    }

    // Excluir problema (DELETE)
    @DELETE
    @Path("/{id}")
    public Response excluirProblema(@PathParam("id") int id) {
        try {
            boolean excluido = problemaBO.excluirProblema(id);
            if (excluido) {
                return Response.ok("Problema excluído com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Problema não encontrado.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir problema.").build();
        }
    }

    // Buscar problema por ID (GET)
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarProblemaPorId(@PathParam("id") int id) {
        try {
            Problema problema = problemaBO.buscarProblemaPorId(id);
            if (problema != null) {
                return Response.ok(problema).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Problema não encontrado.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar problema.").build();
        }
    }

    // Listar todos os problemas (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProblemas() {
        try {
            List<Problema> problemas = problemaBO.listarProblemas();
            return Response.ok(problemas).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar problemas.").build();
        }
    }
}
