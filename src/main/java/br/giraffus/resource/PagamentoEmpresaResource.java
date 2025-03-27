package br.giraffus.resource;

import br.giraffus.dto.PagamentoEmpresaDTO;
import br.giraffus.service.PagamentoEmpresaService;
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

@Path("/pagamento-empresa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoEmpresaResource {
    @Inject
    PagamentoEmpresaService service;

    @POST
    @PermitAll
    public Response createPagamento(PagamentoEmpresaDTO dto) {
        return Response.ok(service.create(dto)).build();
    }

    @GET
    @PermitAll
    public Response getAllPagamentos() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @PermitAll
    @Path("/{id}")
    public Response getPagamento(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @PUT
    @PermitAll
    @Path("/{id}")
    public Response updatePagamento(@PathParam("id") Long id, PagamentoEmpresaDTO dto) {
        return Response.ok(service.update(id, dto)).build();
    }

    @PATCH
    @PermitAll
    @Path("/{id}")
    public Response deletePagamento(@PathParam("id") Long id) {
        service.delete(id);
        return Response.ok().build();
    }
}