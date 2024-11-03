package fiap.tds.resources;

import fiap.tds.beans.Cliente;
import fiap.tds.bo.ClienteBO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/cliente") //nome da pagina
public class ClienteResource {

    private ClienteBO clienteBO = new ClienteBO();

    public ClienteResource() throws SQLException {
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirRS(Cliente cliente, @Context UriInfo uriInfo) throws Exception {
        Cliente clienteCriado = clienteBO.inserir(cliente);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(clienteCriado.getId_cliente()));
        return Response.created(builder.build()).entity(clienteCriado).build();
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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClienteporid(@PathParam("id") int id) throws SQLException {
        Cliente cliente = clienteBO.buscarPorId(id);
        if (cliente != null) {
            return Response.ok(cliente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
