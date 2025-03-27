package br.giraffus.service.impl;

import br.giraffus.dto.EmpresaDTO;
import br.giraffus.dto.EmpresaResponseDTO;
import br.giraffus.model.Empresa;
import br.giraffus.model.EntityClass;
import br.giraffus.repository.*;
import br.giraffus.service.EmpresaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Comparator;

@ApplicationScoped
public class EmpresaServiceImpl implements EmpresaService {
    @Inject EmpresaRepository repository;
    @Inject UsuarioRepository usuarioRepository;
    @Inject PlanoRepository planoRepository;
    @Inject ContaRepository contaRepository;
    @Inject PagamentoEmpresaRepository pagamentoEmpresaRepository;

    @Override
    @Transactional
    public EmpresaResponseDTO create(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setNome(dto.nome());
        empresa.setUsuarios(dto.usuarioIds().stream()
            .map(id -> usuarioRepository.findById(id))
            .toList());
        empresa.setPlano(planoRepository.findById(dto.planoId()));
        empresa.setConta(contaRepository.findById(dto.contaId()));
        empresa.setPagamentoEmpresa(pagamentoEmpresaRepository.findById(dto.pagamentoEmpresaId()));
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
    public EmpresaResponseDTO update(Long id, EmpresaDTO dto) {
        Empresa empresa = repository.findById(id);
        if (empresa == null || !empresa.getAtivo()) {
            throw new NotFoundException();
        }
        if(dto.nome() != null) empresa.setNome(dto.nome());
        if(dto.usuarioIds() != null) {
            empresa.setUsuarios(dto.usuarioIds().stream()
                .map(usuarioId -> usuarioRepository.findById(usuarioId))
                .toList());
        }
        if(dto.planoId() != null) empresa.setPlano(planoRepository.findById(dto.planoId()));
        if(dto.contaId() != null) empresa.setConta(contaRepository.findById(dto.contaId()));
        if(dto.pagamentoEmpresaId() != null) 
            empresa.setPagamentoEmpresa(pagamentoEmpresaRepository.findById(dto.pagamentoEmpresaId()));
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