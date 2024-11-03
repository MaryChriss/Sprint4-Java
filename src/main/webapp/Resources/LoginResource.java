package fiap.resources;

import fiap.tds.beans.Login;
import fiap.tds.bo.LoginBO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

@Path("/login")
public class LoginResource {

    private LoginBO loginBO;

    public LoginResource() throws SQLException {
        this.loginBO = new LoginBO();
    }

    // Autenticar login (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticar(Login login) {
        try {
            boolean autenticado = loginBO.autenticar(login.getEmail_login(), login.getSenha_login());
            if (autenticado) {
                return Response.ok("Login realizado com sucesso.").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciais inválidas.").build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro no servidor.").build();
        }
    }

    // Inserir login (POST)
    // Inserir login (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirLogin(Login login, @Context UriInfo uriInfo) throws SQLException {
        int idLogin = login.getCliente().getId_cliente();  // Assumindo que o id do cliente é o identificador de login
        boolean inserido = loginBO.inserirLogin(idLogin, login.getCliente(), login.getEmail_login(), login.getSenha_login());

        if (inserido) {
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(idLogin));
            return Response.created(builder.build()).entity("Login criado com sucesso.").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Falha ao criar login.").build();
        }
    }


    // Atualizar login (PUT)
    @PUT
    @Path("/{idLogin}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarLogin(@PathParam("idLogin") int idLogin, Login login) throws SQLException {
        boolean atualizado = loginBO.atualizarLogin(idLogin, login.getCliente(), login.getEmail_login(), login.getSenha_login());
        if (atualizado) {
            return Response.ok("Login atualizado com sucesso.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Login não encontrado.").build();
        }
    }

    // Excluir login (DELETE)
    @DELETE
    @Path("/{idLogin}")
    public Response deletarLogin(@PathParam("idLogin") int idLogin) throws SQLException {
        boolean excluido = loginBO.excluirLogin(idLogin);
        if (excluido) {
            return Response.ok("Login excluído com sucesso.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Login não encontrado.").build();
        }
    }

    // Listar todos os logins (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarLogins() throws SQLException {
        try {
            return Response.ok(loginBO.listarLogins()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar logins.").build();
        }
    }
}
