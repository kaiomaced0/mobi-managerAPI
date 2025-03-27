package br.giraffus.service;

import java.util.List;

import br.giraffus.dto.PessoaDTO;
import br.giraffus.dto.PessoaResponseDTO;

public interface PessoaService {
    PessoaResponseDTO create(PessoaDTO dto);
    List<PessoaResponseDTO> findAll();
    PessoaResponseDTO findById(Long id);
    PessoaResponseDTO update(Long id, PessoaDTO dto);
    void delete(Long id);
}