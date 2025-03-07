package br.com.colli.controller;

import br.com.colli.model.Cor;
import br.com.colli.service.CorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/cores")
@Produces(MediaType.APPLICATION_JSON)
public class CorController {

    @Inject
    CorService corService;

    @GET
    public List<Cor> listarTodasAsCores() {
        return corService.listarCores();
    }
}
