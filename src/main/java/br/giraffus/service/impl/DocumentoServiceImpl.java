package br.giraffus.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import br.giraffus.dto.DocumentoDTO;
import br.giraffus.dto.DocumentoResponseDTO;
import br.giraffus.model.Documento;
import br.giraffus.model.EntityClass;
import br.giraffus.repository.DocumentoRepository;
import br.giraffus.service.DocumentoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DocumentoServiceImpl implements DocumentoService {
    @Inject DocumentoRepository repository;

    
    private static final String USER_PATH = System.getProperty("user.home")
        + File.separator + "quarkus"
        + File.separator + "docs"
        + File.separator + "documento"
        + File.separator;

    private static final Set<String> SUPPORTED_MIME_TYPES = Set.of(
        "application/pdf",
        "application/msword" // .doc
);


    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10 Mb limite de tamanho do arquivo

    @Override
    public Documento upload(DocumentoDTO documentoDTO) {
        try {
            verificarTamanhoArquivo(documentoDTO.getArquivo());
            String mimeType = getMimeType(documentoDTO.getNomeArquivo());

            Path diretorioArquivos = null;

            diretorioArquivos = Path.of(USER_PATH);

            Files.createDirectories(diretorioArquivos);

            String extensao =  mimeType.substring(mimeType.lastIndexOf("/") + 1);
            String nomeAleatorioArquivo = UUID.randomUUID() + "." + extensao;

            String path = null;
            path = USER_PATH + nomeAleatorioArquivo;

            File file = new File(path);
            if (file.exists())
                throw new IOException("Este arquivo já existe");

            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(documentoDTO.getArquivo());
            fos.flush();
            fos.close();

            Documento documento = new Documento();
            documento.setTitulo(documentoDTO.getTitulo());
            documento.setDescricao(documentoDTO.getDescricao());
            documento.setNomeArquivo(nomeAleatorioArquivo);
            return documento;
        } catch (Exception e) {
            throw new ValidationException(e.getMessage()); // Modificar com ValidationException
        }
    }

    @Override
    public File download(Long id) {
        Documento documento = repository.findById(id);
        Path diretorioArquivos = null;
        diretorioArquivos = Path.of(USER_PATH);
        
        return new File(diretorioArquivos.toString() + File.separator + documento.getNomeArquivo());
    }

    private void verificarTamanhoArquivo(byte[] arquivo) throws IOException {
        if (arquivo.length > MAX_FILE_SIZE)
            throw new IOException("Arquivo maior que 5 Mb");
    }

    private String getMimeType(String nomeArquivo) throws IOException{
        String mimeType = Files.probeContentType(new File(nomeArquivo).toPath());
        if (!SUPPORTED_MIME_TYPES.contains(mimeType))
            throw new IOException("O tipo de arquivo"+ mimeType +" não é suportado!");

        return mimeType;
    }

    @Override
    @Transactional
    public DocumentoResponseDTO create(DocumentoDTO dto) {
        Documento documento = upload(dto);
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
    @Transactional
    public void delete(Long id) {
        Documento documento = repository.findById(id);
        if (documento == null || !documento.getAtivo()) {
            throw new NotFoundException();
        }
        documento.setAtivo(false);
    }
}