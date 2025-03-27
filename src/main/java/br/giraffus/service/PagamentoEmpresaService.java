package br.giraffus.service;

import java.util.List;

import br.giraffus.dto.DocumentoDTO;
import br.giraffus.dto.PagamentoEmpresaDTO;
import br.giraffus.dto.PagamentoEmpresaResponseDTO;

public interface PagamentoEmpresaService {
    PagamentoEmpresaResponseDTO create(PagamentoEmpresaDTO dto);
    List<PagamentoEmpresaResponseDTO> findAll();
    PagamentoEmpresaResponseDTO findById(Long id);
    PagamentoEmpresaResponseDTO update(Long id, PagamentoEmpresaDTO dto);
    void delete(Long id);
    PagamentoEmpresaResponseDTO addDocumento(Long id, DocumentoDTO dto);
}