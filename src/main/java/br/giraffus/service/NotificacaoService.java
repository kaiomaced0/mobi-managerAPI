package br.giraffus.service;

import java.util.List;

import br.giraffus.dto.NotificacaoDTO;
import br.giraffus.dto.NotificacaoResponseDTO;

public interface NotificacaoService {
    NotificacaoResponseDTO create(NotificacaoDTO dto);
    List<NotificacaoResponseDTO> findAll();
    NotificacaoResponseDTO findById(Long id);
    NotificacaoResponseDTO update(Long id, NotificacaoDTO dto);
    void delete(Long id);
}