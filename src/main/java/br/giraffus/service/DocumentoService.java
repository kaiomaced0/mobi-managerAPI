package br.giraffus.service;

import java.io.File;
import java.util.List;

import br.giraffus.dto.DocumentoDTO;
import br.giraffus.dto.DocumentoResponseDTO;
import br.giraffus.model.Documento;

public interface DocumentoService {
    DocumentoResponseDTO create(DocumentoDTO dto);
    List<DocumentoResponseDTO> findAll();
    DocumentoResponseDTO findById(Long id);
    void delete(Long id);
    
    Documento upload(DocumentoDTO documentoDTO);
    File download(Long id);
}