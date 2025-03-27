package br.giraffus.service.impl;

import java.util.Comparator;
import java.util.List;

import br.giraffus.dto.PlanoDTO;
import br.giraffus.dto.PlanoResponseDTO;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Plano;
import br.giraffus.repository.PlanoRepository;
import br.giraffus.service.PlanoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PlanoServiceImpl implements PlanoService {
    @Inject PlanoRepository repository;

    @Override
    @Transactional
    public PlanoResponseDTO create(PlanoDTO dto) {
        Plano plano = new Plano();
        plano.setNome(dto.nome());
        plano.setPreco(dto.preco());
        plano.setDescricao(dto.descricao());
        plano.setMaxUsuarios(dto.maxUsuarios());
        plano.setMaxContratos(dto.maxContratos());
        plano.setMaxImoveis(dto.maxImoveis());
        plano.setMaxArmazenamentoDocumentosMb(dto.maxArmazenamentoDocumentosMb());
        repository.persist(plano);
        return PlanoResponseDTO.toDTO(plano);
    }

    @Override
    public List<PlanoResponseDTO> findAll() {
        return repository.listAll().stream()
                .filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed())
                .map(PlanoResponseDTO::toDTO)
                .toList();
    }

    @Override
    public PlanoResponseDTO findById(Long id) {
        Plano plano = repository.findById(id);
        if (plano == null || !plano.getAtivo()) {
            throw new NotFoundException();
        }
        return PlanoResponseDTO.toDTO(plano);
    }

    @Override
    @Transactional
    public PlanoResponseDTO update(Long id, PlanoDTO dto) {
        Plano plano = repository.findById(id);
        if (plano == null || !plano.getAtivo()) {
            throw new NotFoundException();
        }
        if(dto.nome() != null) plano.setNome(dto.nome());
        if(dto.preco() != null) plano.setPreco(dto.preco());
        if(dto.descricao() != null) plano.setDescricao(dto.descricao());
        if(dto.maxUsuarios() != null) plano.setMaxUsuarios(dto.maxUsuarios());
        if(dto.maxContratos() != null) plano.setMaxContratos(dto.maxContratos());
        if(dto.maxImoveis() != null) plano.setMaxImoveis(dto.maxImoveis());
        if(dto.maxArmazenamentoDocumentosMb() != null) 
            plano.setMaxArmazenamentoDocumentosMb(dto.maxArmazenamentoDocumentosMb());
        return PlanoResponseDTO.toDTO(plano);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Plano plano = repository.findById(id);
        if (plano == null || !plano.getAtivo()) {
            throw new NotFoundException();
        }
        plano.setAtivo(false);
    }
}