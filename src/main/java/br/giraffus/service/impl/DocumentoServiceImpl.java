package br.giraffus.service.impl;

import java.util.Comparator;
import java.util.List;

import br.giraffus.dto.DocumentoDTO;
import br.giraffus.dto.DocumentoResponseDTO;
import br.giraffus.model.Documento;
import br.giraffus.model.EntityClass;
import br.giraffus.repository.DocumentoRepository;
import br.giraffus.service.DocumentoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DocumentoServiceImpl implements DocumentoService {
    @Inject DocumentoRepository repository;

    @Override
    @Transactional
    public DocumentoResponseDTO create(DocumentoDTO dto) {
        Documento documento = new Documento();
        documento.setNome(dto.nome());
        documento.setTipo(dto.tipo());
        documento.setArquivo(dto.arquivo());
        repository.persist(documento);
        return DocumentoResponseDTO.toDTO(documento);
    }

    @Override
    public List<DocumentoResponseDTO> findAll() {
        return repository.listAll().stream()
                .filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed())
                .map(DocumentoResponseDTO::toDTO)
                .toList();
    }

    @Override
    public DocumentoResponseDTO findById(Long id) {
        Documento documento = repository.findById(id);
        if (documento == null || !documento.getAtivo()) {
            throw new NotFoundException();
        }
        return DocumentoResponseDTO.toDTO(documento);
    }

    @Override
    public byte[] downloadDocumento(Long id) {
        Documento documento = repository.findById(id);
        if (documento == null || !documento.getAtivo()) {
            throw new NotFoundException();
        }
        return documento.getArquivo();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Documento documento = repository.findById(id);
        if (documento == null || !documento.getAtivo()) {
            throw new NotFoundException();
        }
        documento.setAtivo(false);
    }
}