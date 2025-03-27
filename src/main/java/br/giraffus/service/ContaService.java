package br.giraffus.service;

import java.util.List;

import br.giraffus.dto.ContaDTO;
import br.giraffus.dto.ContaResponseDTO;

public interface ContaService {
    ContaResponseDTO create(ContaDTO dto);
    List<ContaResponseDTO> findAll();
    ContaResponseDTO findById(Long id);
    ContaResponseDTO update(Long id, ContaDTO dto);
    void delete(Long id);
}