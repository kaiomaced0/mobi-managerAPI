package br.giraffus.service.impl;

import br.giraffus.dto.PessoaDTO;
import br.giraffus.dto.PessoaResponseDTO;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Pessoa;
import br.giraffus.repository.PessoaRepository;
import br.giraffus.service.PessoaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class PessoaServiceImpl implements PessoaService {

    @Inject
    PessoaRepository repository;

    @Override
    @Transactional
    public PessoaResponseDTO create(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setCpf(dto.cpf());
        pessoa.setTelefone(dto.telefone());
        pessoa.setEmail(dto.email());
        repository.persist(pessoa);
        return PessoaResponseDTO.toDTO(pessoa);
    }

    @Override
    public List<PessoaResponseDTO> findAll() {
        return repository.listAll().stream()
                .filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed())
                .map(PessoaResponseDTO::toDTO)
                .toList();
    }

    @Override
    public PessoaResponseDTO findById(Long id) {
        Pessoa pessoa = repository.findById(id);
        if (pessoa == null || !pessoa.getAtivo()) {
            throw new NotFoundException();
        }
        return PessoaResponseDTO.toDTO(pessoa);
    }

    @Override
    @Transactional
    public PessoaResponseDTO update(Long id, PessoaDTO dto) {
        Pessoa pessoa = repository.findById(id);
        if (pessoa == null || !pessoa.getAtivo()) {
            throw new NotFoundException();
        }
        if(dto.nome() != null) pessoa.setNome(dto.nome());
        if(dto.cpf() != null) pessoa.setCpf(dto.cpf());
        if(dto.telefone() != null) pessoa.setTelefone(dto.telefone());
        if(dto.email() != null) pessoa.setEmail(dto.email());
        repository.persist(pessoa);
        return PessoaResponseDTO.toDTO(pessoa);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Pessoa pessoa = repository.findById(id);
        if (pessoa == null || !pessoa.getAtivo()) {
            throw new NotFoundException();
        }
        pessoa.setAtivo(false);
    }
}