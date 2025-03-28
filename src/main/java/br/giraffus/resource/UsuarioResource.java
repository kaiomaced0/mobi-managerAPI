package br.giraffus.resource;

import br.giraffus.dto.UsuarioDTO;
import br.giraffus.service.UsuarioService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @GET
    @PermitAll
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @PermitAll
    @Path("/funcionarios")
    public Response getFuncionarios() {
        return Response.ok(service.getFuncionarios()).build();
    }

    @GET
    @PermitAll
    @Path("/nome/{nome}")
    public Response getNome(@PathParam("nome") String nome) {
        return Response.ok(service.getNome(nome)).build();
    }

    @GET
    @PermitAll
    @Path("/{id}")
    public Response getId(@PathParam("id") Long id) {
        return Response.ok(service.getId(id)).build();
    }

    @POST
    @PermitAll
    public Response insert(UsuarioDTO usuario) {
        return Response.ok(service.insert(usuario)).build();
    }

    @POST
    @PermitAll
    @Path("/funcionario")
    public Response insertFuncionario(UsuarioDTO usuario) {
        return service.insertFuncionario(usuario);
    }
    
    @DELETE
    @PermitAll
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}