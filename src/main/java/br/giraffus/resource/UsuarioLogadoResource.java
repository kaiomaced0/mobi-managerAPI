package br.giraffus.resource;

import br.giraffus.service.UsuarioLogadoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuario-logado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {

    @Inject
    UsuarioLogadoService service;

    @GET
    @RolesAllowed({"Admin", "User", "Client"})
    @Path("/perfil")
    public Response getPerfilUsuarioLogado() {
        return service.getPerfilUsuarioLogado();
    }
}