package fiap.tds.resources;

import fiap.tds.Credenciais.Credenciais;
import fiap.tds.beans.Problema;
import fiap.tds.bo.ProblemaBO;
import fiap.tds.dao.ProblemaDAOImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/problema")
public class ProblemaResource {

    private ProblemaBO problemaBO;

    public ProblemaResource() throws SQLException {
        this.problemaBO = new ProblemaBO(new ProblemaDAOImpl(Credenciais.getConnection())); // Instanciar com conexão
    }

    // Inserir novo problema (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirProblema(Problema problema, @Context UriInfo uriInfo) throws SQLException {
        problemaBO.adicionarProblema(problema);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(problema.getIdProblema()));
        return Response.created(builder.build()).build();
    }

    // Atualizar problema existente (PUT)
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarProblema(Problema problema, @PathParam("id") int id) throws SQLException {
        problema.setIdProblema(id); // Garantir que o ID está configurado
        boolean atualizado = problemaBO.atualizarProblema(problema);

        if (atualizado) {
            return Response.ok("Problema atualizado com sucesso.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Problema não encontrado para atualização.").build();
        }
    }

    // Excluir problema (DELETE)
    @DELETE
    @Path("/{id}")
    public Response excluirProblema(@PathParam("id") int id) throws SQLException {
        boolean excluido = problemaBO.excluirProblema(id);

        if (excluido) {
            return Response.ok("Problema excluído com sucesso.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Problema não encontrado para exclusão.").build();
        }
    }

    // Consultar todos os problemas (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProblemas() throws SQLException {
        List<Problema> problemas = problemaBO.listarProblemas();
        return Response.ok(problemas).build();
    }

    // Consultar problema por ID (GET)
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarProblemaPorId(@PathParam("id") int id) throws SQLException {
        Problema problema = problemaBO.buscarProblemaPorId(id);

        if (problema != null) {
            return Response.ok(problema).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Problema não encontrado.").build();
        }
    }
}
