package br.giraffus.resource;


import br.giraffus.dto.ContratoDTO;
import br.giraffus.dto.ContratoResponseDTO;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Contrato;
import br.giraffus.repository.ContratoRepository;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Comparator;
import java.util.List;

@Path("/contrato")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContratoResource {

    @Inject
    ContratoRepository repository;

    @POST
    @PermitAll
    @Transactional
    public Response createContrato(ContratoDTO dto) {
        Contrato contrato = new Contrato();

        repository.persist(contrato);

        return Response.ok(ContratoResponseDTO.toDTO(contrato)).build();
    }

    @GET
    @PermitAll
    public Response getAllContratos() {
        List<Contrato> contratos = repository.listAll();
        return Response.ok(contratos.stream().filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed()).map(ContratoResponseDTO::toDTO).toList()).build();
    }

    @GET
    @PermitAll
    @Path("/{id}")
    public Response getContrato(@PathParam("id") Long id) {
        Contrato contrato = repository.findById(id);
        if (contrato == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(ContratoResponseDTO.toDTO(contrato)).build();
    }

    @PUT
    @PermitAll
    @Transactional
    @Path("/{id}")
    public Response updateContrato(@PathParam("id") Long id, ContratoDTO dto) {
        Contrato contrato = repository.findById(id);
        if (contrato == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        repository.persist(contrato);
        
        return Response.ok(ContratoResponseDTO.toDTO(contrato)).build();
    }

    @PATCH
    @PermitAll
    @Transactional
    @Path("/{id}")
    public Response deleteContrato(@PathParam("id") Long id) {
        Contrato contrato = repository.findById(id);
        if (!contrato.getAtivo()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        contrato.setAtivo(false);

        return Response.ok().build();
    }
}

