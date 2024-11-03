package fiap.resources;

import fiap.tds.beans.Cliente;
import fiap.tds.bo.ClienteBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/cliente") //nome da pagina
public class ClienteResource {

    private ClienteBO clienteBO = new ClienteBO();

    public ClienteResource() throws SQLException {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRS(Cliente cliente, @Context UriInfo uriInfo) throws SQLException {
        try {
            clienteBO.inserir(cliente);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(cliente.getId_cliente()));

        return Response.created(builder.build()).build();
    }

    //atualizar (put)

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRs (Cliente cliente, @PathParam("id") int id) throws SQLException {
        clienteBO.atualizar(cliente);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaRS(@PathParam("id") int id) throws SQLException {
        clienteBO.excluir(id);
        return Response.ok().build();
    }

    //consultar (GET) devolvendo a lista

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Cliente> selecionaRS() throws SQLException {
        return (ArrayList<Cliente>) clienteBO.listar();
    }

}
