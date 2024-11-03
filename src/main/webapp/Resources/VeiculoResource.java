package br.com.fiap.resources;

import fiap.tds.beans.Veiculo;
import fiap.tds.bo.VeiculoBO;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Path("/veiculo") // Nome do recurso na URL
public class VeiculoResource {

    private VeiculoBO veiculoBO = new VeiculoBO();

    // Inserir Veículo (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarVeiculo(Veiculo veiculo, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        veiculoBO.adicionarVeiculo(veiculo);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(veiculo.getId_veiculo()));
        return Response.created(builder.build()).build();
    }

    // Atualizar Veículo (PUT)
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(Veiculo veiculo, @PathParam("id") int id) throws ClassNotFoundException, SQLException {
        veiculo.setId_veiculo(id); // Assegura que o ID é atualizado conforme o parâmetro
        veiculoBO.atualizarVeiculo(veiculo);
        return Response.ok().build();
    }

    // Deletar Veículo (DELETE)
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarVeiculo(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        veiculoBO.excluirVeiculo(id);
        return Response.ok().build();
    }

    // Consultar todos os Veículos (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Veiculo> listarVeiculos() throws ClassNotFoundException, SQLException {
        return (ArrayList<Veiculo>) veiculoBO.listarVeiculos();
    }
}
