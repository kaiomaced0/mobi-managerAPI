package br.giraffus.resource;

import br.giraffus.dto.PessoaDTO;
import br.giraffus.service.PessoaService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

    @Inject
    PessoaService service;

    @POST
    @PermitAll
    public Response createPessoa(PessoaDTO dto) {
        return Response.ok(service.create(dto)).build();
    }

    @GET
    @PermitAll
    public Response getAllPessoas() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @PermitAll
    @Path("/{id}")
    public Response getPessoa(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @PUT
    @PermitAll
    @Path("/{id}")
    public Response updatePessoa(@PathParam("id") Long id, PessoaDTO dto) {
        return Response.ok(service.update(id, dto)).build();
    }

    @PATCH
    @PermitAll
    @Path("/{id}")
    public Response deletePessoa(@PathParam("id") Long id) {
        service.delete(id);
        return Response.ok().build();
    }
}