package br.giraffus.resource;


import br.giraffus.dto.ImovelDTO;
import br.giraffus.dto.ImovelResponseDTO;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Imovel;
import br.giraffus.repository.ImovelRepository;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Comparator;
import java.util.List;

@Path("/imovel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImovelResource {

    @Inject
    ImovelRepository repository;

    @POST
    @PermitAll
    @Transactional
    public Response createImovel(ImovelDTO dto) {
        Imovel imovel = new Imovel();

        repository.persist(imovel);

        return Response.ok(ImovelResponseDTO.toDTO(imovel)).build();
    }

    @GET
    @PermitAll
    public Response getAllImovels() {
        List<Imovel> Imovels = repository.listAll();
        return Response.ok(Imovels.stream().filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed()).map(ImovelResponseDTO::toDTO).toList()).build();
    }

    @GET
    @PermitAll
    @Path("/{id}")
    public Response getImovel(@PathParam("id") Long id) {
        Imovel Imovel = repository.findById(id);
        if (Imovel == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(ImovelResponseDTO.toDTO(Imovel)).build();
    }

    @PUT
    @PermitAll
    @Transactional
    @Path("/{id}")
    public Response updateImovel(@PathParam("id") Long id, ImovelDTO dto) {
        Imovel imovel = repository.findById(id);
        if (imovel == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        if(dto.descricao() != null){
            imovel.setDescricao(dto.descricao());
        }
        repository.persist(imovel);

        return Response.ok(ImovelResponseDTO.toDTO(imovel)).build();
    }

    @PATCH
    @PermitAll
    @Transactional
    @Path("/{id}")
    public Response deleteImovel(@PathParam("id") Long id) {
        Imovel Imovel = repository.findById(id);
        if (!Imovel.getAtivo()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        Imovel.setAtivo(false);

        return Response.ok().build();
    }
}

