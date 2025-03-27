package br.giraffus.service;

import java.util.List;

import br.giraffus.dto.EmpresaDTO;
import br.giraffus.dto.EmpresaResponseDTO;

public interface EmpresaService {
    EmpresaResponseDTO create(EmpresaDTO dto);
    List<EmpresaResponseDTO> findAll();
    EmpresaResponseDTO findById(Long id);
    EmpresaResponseDTO update(Long id, EmpresaDTO dto);
    void delete(Long id);
}