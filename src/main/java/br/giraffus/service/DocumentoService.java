package br.giraffus.service;

import java.util.List;

import br.giraffus.dto.DocumentoDTO;
import br.giraffus.dto.DocumentoResponseDTO;

public interface DocumentoService {
    DocumentoResponseDTO create(DocumentoDTO dto);
    List<DocumentoResponseDTO> findAll();
    DocumentoResponseDTO findById(Long id);
    byte[] downloadDocumento(Long id);
    void delete(Long id);
}