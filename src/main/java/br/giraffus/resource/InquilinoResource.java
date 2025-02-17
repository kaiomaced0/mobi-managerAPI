package br.giraffus.resource;


import br.giraffus.dto.InquilinoDTO;
import br.giraffus.dto.InquilinoResponseDTO;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Inquilino;
import br.giraffus.repository.InquilinoRepository;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Comparator;
import java.util.List;

@Path("/inquilino")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InquilinoResource {

    @Inject
    InquilinoRepository repository;

    @POST
    @PermitAll
    @Transactional
    public Response createInquilino(InquilinoDTO dto) {
        Inquilino inquilino = new Inquilino();
        inquilino.setCpf(dto.cpf());
        inquilino.setNome(dto.nome());
        inquilino.setEmail(dto.email());
        inquilino.setTelefone(dto.telefone());
        repository.persist(inquilino);

        return Response.ok(InquilinoResponseDTO.toDTO(inquilino)).build();
    }

    @GET
    @PermitAll
    public Response getAllInquilinos() {
        List<Inquilino> Inquilinos = repository.listAll();
        return Response.ok(Inquilinos.stream().filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed()).map(InquilinoResponseDTO::toDTO).toList()).build();
    }

    @GET
    @PermitAll
    @Path("/{id}")
    public Response getInquilino(@PathParam("id") Long id) {
        Inquilino Inquilino = repository.findById(id);
        if (Inquilino == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(InquilinoResponseDTO.toDTO(Inquilino)).build();
    }

    @PUT
    @PermitAll
    @Transactional
    @Path("/{id}")
    public Response updateInquilino(@PathParam("id") Long id, InquilinoDTO dto) {
        Inquilino inquilino = repository.findById(id);
        if (inquilino == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        if(dto.nome() != null) {
            inquilino.setNome(dto.nome());
        }
        if(dto.telefone() != null) {
            inquilino.setTelefone(dto.telefone());
        }
        if(dto.email() != null) {
            inquilino.setEmail(dto.email());
        }
        if(dto.cpf() != null) {
            inquilino.setCpf(dto.cpf());
        }
        repository.persist(inquilino);

        return Response.ok(InquilinoResponseDTO.toDTO(inquilino)).build();
    }

    @PATCH
    @PermitAll
    @Transactional
    @Path("/{id}")
    public Response deleteInquilino(@PathParam("id") Long id) {
        Inquilino Inquilino = repository.findById(id);
        if (!Inquilino.getAtivo()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        Inquilino.setAtivo(false);

        return Response.ok().build();
    }
}

