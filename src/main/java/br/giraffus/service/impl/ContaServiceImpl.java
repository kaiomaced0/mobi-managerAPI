package br.giraffus.service.impl;

import java.util.Comparator;
import java.util.List;

import br.giraffus.dto.ContaDTO;
import br.giraffus.dto.ContaResponseDTO;
import br.giraffus.model.Conta;
import br.giraffus.model.EntityClass;
import br.giraffus.repository.ContaRepository;
import br.giraffus.service.ContaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ContaServiceImpl implements ContaService {
    @Inject ContaRepository repository;

    @Override
    @Transactional
    public ContaResponseDTO create(ContaDTO dto) {
        Conta conta = new Conta();
        conta.setCpf(dto.cpf());
        conta.setCnpj(dto.cnpj());
        conta.setNome(dto.nome());
        conta.setNomeFantasia(dto.nomeFantasia());
        repository.persist(conta);
        return ContaResponseDTO.toDTO(conta);
    }

    @Override
    public List<ContaResponseDTO> findAll() {
        return repository.listAll().stream()
                .filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed())
                .map(ContaResponseDTO::toDTO)
                .toList();
    }

    @Override
    public ContaResponseDTO findById(Long id) {
        Conta conta = repository.findById(id);
        if (conta == null || !conta.getAtivo()) {
            throw new NotFoundException();
        }
        return ContaResponseDTO.toDTO(conta);
    }

    @Override
    @Transactional
    public ContaResponseDTO update(Long id, ContaDTO dto) {
        Conta conta = repository.findById(id);
        if (conta == null || !conta.getAtivo()) {
            throw new NotFoundException();
        }
        if(dto.cpf() != null) conta.setCpf(dto.cpf());
        if(dto.cnpj() != null) conta.setCnpj(dto.cnpj());
        if(dto.nome() != null) conta.setNome(dto.nome());
        if(dto.nomeFantasia() != null) conta.setNomeFantasia(dto.nomeFantasia());
        return ContaResponseDTO.toDTO(conta);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Conta conta = repository.findById(id);
        if (conta == null || !conta.getAtivo()) {
            throw new NotFoundException();
        }
        conta.setAtivo(false);
    }
}