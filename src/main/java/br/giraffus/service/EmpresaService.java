package br.giraffus.service;

import java.util.List;

import br.giraffus.dto.EmpresaDTO;
import br.giraffus.dto.EmpresaResponseDTO;
import br.giraffus.dto.EmpresaUpdateDTO;

public interface EmpresaService {
    EmpresaResponseDTO create(EmpresaDTO dto);
    List<EmpresaResponseDTO> findAll();
    EmpresaResponseDTO findById(Long id);
    EmpresaResponseDTO update(Long id, EmpresaUpdateDTO dto);
    void delete(Long id);
}