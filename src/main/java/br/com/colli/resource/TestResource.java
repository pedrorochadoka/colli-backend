package br.com.colli.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test")
public class TestResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testEnv() {
        return "Database URL: " + System.getenv("QUARKUS_DATASOURCE_JDBC_URL"); // <-- ALTERADO AQUI!
    }
}
