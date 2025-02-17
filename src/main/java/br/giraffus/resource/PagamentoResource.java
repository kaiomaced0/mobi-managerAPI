package br.giraffus.resource;


import br.giraffus.dto.PagamentoDTO;
import br.giraffus.dto.PagamentoResponseDTO;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Pagamento;
import br.giraffus.repository.PagamentoRepository;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Comparator;
import java.util.List;

@Path("/pagamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoResource {

    @Inject
    PagamentoRepository repository;

    @POST
    @PermitAll
    @Transactional
    public Response createPagamento(PagamentoDTO dto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setCpf(dto.cpf());
        pagamento.setPreco(dto.preco());
        pagamento.setDataPagamento(dto.dataPagamento());
        pagamento.setMesReferente(dto.mesReferente());
        pagamento.setAnoReferente(dto.anoReferente());
        repository.persist(pagamento);

        return Response.ok(PagamentoResponseDTO.toDTO(pagamento)).build();
    }

    @GET
    @PermitAll
    public Response getAllPagamentos() {
        List<Pagamento> Pagamentos = repository.listAll();
        return Response.ok(Pagamentos.stream().filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed()).map(PagamentoResponseDTO::toDTO).toList()).build();
    }

    @GET
    @PermitAll
    @Path("/{id}")
    public Response getPagamento(@PathParam("id") Long id) {
        Pagamento Pagamento = repository.findById(id);
        if (Pagamento == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(PagamentoResponseDTO.toDTO(Pagamento)).build();
    }

    @PUT
    @PermitAll
    @Transactional
    @Path("/{id}")
    public Response updatePagamento(@PathParam("id") Long id, PagamentoDTO dto) {
        Pagamento Pagamento = repository.findById(id);
        if (Pagamento == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        if(dto.preco() != null) {
            Pagamento.setPreco(dto.preco());
        }
        repository.persist(Pagamento);

        return Response.ok().build();
    }

    @PATCH
    @PermitAll
    @Transactional
    @Path("/{id}")
    public Response deletePagamento(@PathParam("id") Long id) {
        Pagamento Pagamento = repository.findById(id);
        if (!Pagamento.getAtivo()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        Pagamento.setAtivo(false);

        return Response.ok().build();
    }
}

