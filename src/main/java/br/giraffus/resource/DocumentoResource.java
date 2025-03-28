package br.giraffus.resource;

import br.giraffus.dto.DocumentoDTO;
import br.giraffus.service.DocumentoService;
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

@Path("/documento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocumentoResource {
    @Inject
    DocumentoService service;

    @POST
    @PermitAll
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createDocumento(DocumentoDTO dto) {
        return Response.ok(service.create(dto)).build();
    }

    @GET
    @PermitAll
    public Response getAllDocumentos() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @PermitAll
    @Path("/{id}")
    public Response getDocumento(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @PermitAll
    @Path("/download/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadDocumento(@PathParam("id") Long id) {
        return Response.ok(service.download(id)).build();
    }

    @DELETE
    @PermitAll
    @Path("/{id}")
    public Response deleteDocumento(@PathParam("id") Long id) {
        service.delete(id);
        return Response.ok().build();
    }
}