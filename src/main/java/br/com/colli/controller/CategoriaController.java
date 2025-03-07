package br.com.colli.controller;

import br.com.colli.model.Categoria;
import br.com.colli.service.CategoriaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/categoria")
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaController {

    @Inject
    CategoriaService categoriaService;

    @GET
    public Response obterTodasCategorias() {
        try{
            List<Categoria> categorias = categoriaService.obterTodasCategorias();
            return Response.ok(categorias).build();

        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar todos as categorias").build();
        }
    }

}
