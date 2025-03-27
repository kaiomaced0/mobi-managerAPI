package br.giraffus.service;

import java.util.List;

import br.giraffus.dto.PlanoDTO;
import br.giraffus.dto.PlanoResponseDTO;

public interface PlanoService {
    PlanoResponseDTO create(PlanoDTO dto);
    List<PlanoResponseDTO> findAll();
    PlanoResponseDTO findById(Long id);
    PlanoResponseDTO update(Long id, PlanoDTO dto);
    void delete(Long id);
}