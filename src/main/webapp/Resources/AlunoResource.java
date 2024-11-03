package fiap.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/aluno") //nome da pagina
public class AlunoResource {

    private AlunoBO alunoBO = new AlunoBO();

    //inserir (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastroRS(Aluno aluno, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        alunoBO.inserirBO(aluno);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(aluno.getRm()));

        return Response.created(builder.build()).build();
    }

    //atualizar (put)

    @PUT
    @Path("/{rm}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRs (Aluno aluno, @PathParam("rm") int rm) throws SQLException, ClassNotFoundException {
        alunoBO.atualizarBO(aluno);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{rm}")
    public Response deletaRS(@PathParam("rm") int rm) throws SQLException, ClassNotFoundException {
        alunoBO.deletarBO(rm);
        return Response.ok().build();
    }

    //consultar (GET) devolvendo a lista

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> selecionaRS() throws SQLException, ClassNotFoundException {
        return (ArrayList<Aluno>) alunoBO.selecionarBO();
    }

}
