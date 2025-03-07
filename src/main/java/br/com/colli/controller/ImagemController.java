package br.com.colli.controller;

import br.com.colli.model.Imagem;
import br.com.colli.service.ImagemService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/imagens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImagemController {

    @Inject
    ImagemService imagemService;

    @GET
    @Path("/por-cor/{cor}")
    public List<Imagem> buscarImagensPorCor(@PathParam("cor") String cor){
        return imagemService.buscarImagensPorCor(cor);
    }

}
