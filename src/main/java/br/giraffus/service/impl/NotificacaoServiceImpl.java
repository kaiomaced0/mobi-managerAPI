package br.giraffus.service.impl;

import br.giraffus.dto.NotificacaoDTO;
import br.giraffus.dto.NotificacaoResponseDTO;
import br.giraffus.model.Notificacao;
import br.giraffus.model.EntityClass;
import br.giraffus.repository.NotificacaoRepository;
import br.giraffus.repository.EmpresaRepository;
import br.giraffus.repository.UsuarioRepository;
import br.giraffus.service.NotificacaoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Comparator;

@ApplicationScoped
public class NotificacaoServiceImpl implements NotificacaoService {
    @Inject NotificacaoRepository repository;
    @Inject EmpresaRepository empresaRepository;
    @Inject UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public NotificacaoResponseDTO create(NotificacaoDTO dto) {
        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem(dto.mensagem());
        notificacao.setDataEnvio(dto.dataEnvio());
        notificacao.setStatus(dto.status());
        notificacao.setTipoNotificacao(dto.tipoNotificacao());
        notificacao.setEmpresa(empresaRepository.findById(dto.empresaId()));
        notificacao.setUsuario(usuarioRepository.findById(dto.usuarioId()));
        repository.persist(notificacao);
        return NotificacaoResponseDTO.toDTO(notificacao);
    }

    @Override
    public List<NotificacaoResponseDTO> findAll() {
        return repository.listAll().stream()
                .filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed())
                .map(NotificacaoResponseDTO::toDTO)
                .toList();
    }

    @Override
    public NotificacaoResponseDTO findById(Long id) {
        Notificacao notificacao = repository.findById(id);
        if (notificacao == null || !notificacao.getAtivo()) {
            throw new NotFoundException();
        }
        return NotificacaoResponseDTO.toDTO(notificacao);
    }

    @Override
    @Transactional
    public NotificacaoResponseDTO update(Long id, NotificacaoDTO dto) {
        Notificacao notificacao = repository.findById(id);
        if (notificacao == null || !notificacao.getAtivo()) {
            throw new NotFoundException();
        }
        if(dto.mensagem() != null) notificacao.setMensagem(dto.mensagem());
        if(dto.dataEnvio() != null) notificacao.setDataEnvio(dto.dataEnvio());
        if(dto.status() != null) notificacao.setStatus(dto.status());
        if(dto.tipoNotificacao() != null) notificacao.setTipoNotificacao(dto.tipoNotificacao());
        if(dto.empresaId() != null) notificacao.setEmpresa(empresaRepository.findById(dto.empresaId()));
        if(dto.usuarioId() != null) notificacao.setUsuario(usuarioRepository.findById(dto.usuarioId()));
        return NotificacaoResponseDTO.toDTO(notificacao);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Notificacao notificacao = repository.findById(id);
        if (notificacao == null || !notificacao.getAtivo()) {
            throw new NotFoundException();
        }
        notificacao.setAtivo(false);
    }
}