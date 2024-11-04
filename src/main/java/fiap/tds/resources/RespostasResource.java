package fiap.tds.resources;

import fiap.tds.Credenciais.Credenciais;
import fiap.tds.beans.Respostas;
import fiap.tds.bo.RespostasBO;
import fiap.tds.dao.RespostasDAOImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/respostas")
public class RespostasResource {

    private RespostasBO respostasBO;

    public RespostasResource() throws SQLException {
        Connection conn = Credenciais.getConnection();
        this.respostasBO = new RespostasBO(new RespostasDAOImpl(conn));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarResposta(Respostas resposta, @Context UriInfo uriInfo) throws SQLException {
        respostasBO.adicionarResposta(resposta);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(resposta.getIdRespostas()));
        return Response.created(builder.build()).entity("Resposta adicionada com sucesso!").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarResposta(@PathParam("id") int id, Respostas resposta) throws SQLException {
        resposta.setIdRespostas(id);
        respostasBO.atualizarResposta(resposta);
        return Response.ok("Resposta atualizada com sucesso!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirResposta(@PathParam("id") int id) throws SQLException {
        respostasBO.excluirResposta(id);
        return Response.ok("Resposta excluída com sucesso!").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarRespostas() throws SQLException {
        List<Respostas> respostas = respostasBO.listarRespostas();
        return Response.ok(respostas).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarRespostaPorId(@PathParam("id") int id) throws SQLException {
        Respostas resposta = respostasBO.buscarRespostaPorId(id);
        if (resposta == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Resposta não encontrada").build();
        }
        return Response.ok(resposta).build();
    }
}
