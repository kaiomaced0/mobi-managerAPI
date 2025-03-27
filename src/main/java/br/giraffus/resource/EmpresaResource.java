package br.giraffus.resource;

import br.giraffus.dto.EmpresaDTO;
import br.giraffus.service.EmpresaService;
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

@Path("/empresa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaResource {
    @Inject
    EmpresaService service;

    @POST
    @PermitAll
    public Response createEmpresa(EmpresaDTO dto) {
        return Response.ok(service.create(dto)).build();
    }

    @GET
    @PermitAll
    public Response getAllEmpresas() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @PermitAll
    @Path("/{id}")
    public Response getEmpresa(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @PUT
    @PermitAll
    @Path("/{id}")
    public Response updateEmpresa(@PathParam("id") Long id, EmpresaDTO dto) {
        return Response.ok(service.update(id, dto)).build();
    }

    @PATCH
    @PermitAll
    @Path("/{id}")
    public Response deleteEmpresa(@PathParam("id") Long id) {
        service.delete(id);
        return Response.ok().build();
    }
}