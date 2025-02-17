package br.giraffus.resource;


import br.giraffus.dto.ProprietarioDTO;
import br.giraffus.dto.ProprietarioResponseDTO;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Proprietario;
import br.giraffus.repository.ProprietarioRepository;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Comparator;
import java.util.List;

@Path("/proprietario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProprietarioResource {

    @Inject
    ProprietarioRepository repository;

    @POST
    @PermitAll
    @Transactional
    public Response createProprietario(ProprietarioDTO dto) {
        Proprietario proprietario = new Proprietario();
        proprietario.setNome(dto.nome());
        proprietario.setEmail(dto.email());
        proprietario.setDescricao(dto.descricao());
        proprietario.setIdentificacao(dto.identificacao());
        proprietario.setTelefone(dto.telefone());
        repository.persist(proprietario);

        return Response.ok(ProprietarioResponseDTO.toDTO(proprietario)).build();
    }

    @GET
    @PermitAll
    public Response getAllProprietarios() {
        List<Proprietario> Proprietarios = repository.listAll();
        return Response.ok(Proprietarios.stream().filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed()).map(ProprietarioResponseDTO::toDTO).toList()).build();
    }

    @GET
    @PermitAll
    @Path("/{id}")
    public Response getProprietario(@PathParam("id") Long id) {
        Proprietario Proprietario = repository.findById(id);
        if (Proprietario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(ProprietarioResponseDTO.toDTO(Proprietario)).build();
    }

    @PUT
    @PermitAll
    @Transactional
    @Path("/{id}")
    public Response updateProprietario(@PathParam("id") Long id, ProprietarioDTO dto) {
        Proprietario Proprietario = repository.findById(id);
        if (Proprietario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        if(dto.nome() != null) {
            Proprietario.setNome(dto.nome());
        }
        repository.persist(Proprietario);

        return Response.ok().build();
    }

    @PATCH
    @PermitAll
    @Transactional
    @Path("/{id}")
    public Response deleteProprietario(@PathParam("id") Long id) {
        Proprietario Proprietario = repository.findById(id);
        if (!Proprietario.getAtivo()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        Proprietario.setAtivo(false);

        return Response.ok().build();
    }
}

