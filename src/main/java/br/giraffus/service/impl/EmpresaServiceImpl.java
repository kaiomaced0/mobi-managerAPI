package br.giraffus.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.giraffus.dto.EmpresaDTO;
import br.giraffus.dto.EmpresaResponseDTO;
import br.giraffus.dto.EmpresaUpdateDTO;
import br.giraffus.dto.UsuarioResponseDTO;
import br.giraffus.model.Empresa;
import br.giraffus.model.EntityClass;
import br.giraffus.repository.ContaRepository;
import br.giraffus.repository.EmpresaRepository;
import br.giraffus.repository.PlanoRepository;
import br.giraffus.repository.UsuarioRepository;
import br.giraffus.service.EmpresaService;
import br.giraffus.service.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EmpresaServiceImpl implements EmpresaService {

    @Inject EmpresaRepository repository;
    @Inject UsuarioRepository usuarioRepository;
    @Inject PlanoRepository planoRepository;
    @Inject ContaRepository contaRepository;
    @Inject UsuarioService usuarioService;

    @Override
    @Transactional
    public EmpresaResponseDTO create(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setNome(dto.nome());
        empresa.setUsuarios(new ArrayList<>());
        UsuarioResponseDTO usuario = usuarioService.insert(dto.usuarioDTO());
        empresa.getUsuarios().add(usuarioRepository.findById(usuario.id()));
        empresa.setPlano(planoRepository.findById(dto.planoId()));
        empresa.setConta(contaRepository.findById(dto.contaId()));
        repository.persist(empresa);
        return EmpresaResponseDTO.toDTO(empresa);
    }

    @Override
    public List<EmpresaResponseDTO> findAll() {
        return repository.listAll().stream()
                .filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed())
                .map(EmpresaResponseDTO::toDTO)
                .toList();
    }

    @Override
    public EmpresaResponseDTO findById(Long id) {
        Empresa empresa = repository.findById(id);
        if (empresa == null || !empresa.getAtivo()) {
            throw new NotFoundException();
        }
        return EmpresaResponseDTO.toDTO(empresa);
    }

    @Override
    @Transactional
    public EmpresaResponseDTO update(Long id, EmpresaUpdateDTO dto) {
        Empresa empresa = repository.findById(id);
        if (empresa == null || !empresa.getAtivo()) {
            throw new NotFoundException();
        }
        if(dto.nome() != null) empresa.setNome(dto.nome());

        if(dto.planoId() != null){
            if(dto.planoId() != null) empresa.setPlano(planoRepository.findById(dto.planoId()));
        }
        if(dto.contaId() != null){
            if(dto.contaId() != null) empresa.setConta(contaRepository.findById(dto.contaId()));
        }

        repository.persist(empresa);
        return EmpresaResponseDTO.toDTO(empresa);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Empresa empresa = repository.findById(id);
        if (empresa == null || !empresa.getAtivo()) {
            throw new NotFoundException();
        }
        empresa.setAtivo(false);
    }
}