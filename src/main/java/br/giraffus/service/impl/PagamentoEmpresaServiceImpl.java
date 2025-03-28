package br.giraffus.service.impl;

import java.util.Comparator;
import java.util.List;

import br.giraffus.dto.DocumentoDTO;
import br.giraffus.dto.PagamentoEmpresaDTO;
import br.giraffus.dto.PagamentoEmpresaResponseDTO;
import br.giraffus.model.EntityClass;
import br.giraffus.model.PagamentoEmpresa;
import br.giraffus.repository.DocumentoRepository;
import br.giraffus.repository.PagamentoEmpresaRepository;
import br.giraffus.service.PagamentoEmpresaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PagamentoEmpresaServiceImpl implements PagamentoEmpresaService {
    @Inject PagamentoEmpresaRepository repository;
    @Inject DocumentoRepository documentoRepository;

    @Override
    @Transactional
    public PagamentoEmpresaResponseDTO create(PagamentoEmpresaDTO dto) {
        PagamentoEmpresa pagamento = new PagamentoEmpresa();
        pagamento.setAno(dto.ano());
        pagamento.setMes(dto.mes());
        pagamento.setPreco(dto.preco());
        pagamento.setDataVencimento(dto.dataVencimento());
        pagamento.setPagamentoRealizado(dto.pagamentoRealizado());
        pagamento.setPagamento(dto.pagamento());
        pagamento.setDocumentos(dto.documentoIds().stream()
            .map(id -> documentoRepository.findById(id))
            .toList());
        repository.persist(pagamento);
        return PagamentoEmpresaResponseDTO.toDTO(pagamento);
    }

    @Override
    public List<PagamentoEmpresaResponseDTO> findAll() {
        return repository.listAll().stream()
                .filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed())
                .map(PagamentoEmpresaResponseDTO::toDTO)
                .toList();
    }

    @Override
    public PagamentoEmpresaResponseDTO findById(Long id) {
        PagamentoEmpresa pagamento = repository.findById(id);
        if (pagamento == null || !pagamento.getAtivo()) {
            throw new NotFoundException();
        }
        return PagamentoEmpresaResponseDTO.toDTO(pagamento);
    }

    @Override
    @Transactional
    public PagamentoEmpresaResponseDTO update(Long id, PagamentoEmpresaDTO dto) {
        PagamentoEmpresa pagamento = repository.findById(id);
        if (pagamento == null || !pagamento.getAtivo()) {
            throw new NotFoundException();
        }
        if(dto.ano() != null) pagamento.setAno(dto.ano());
        if(dto.mes() != null) pagamento.setMes(dto.mes());
        if(dto.preco() != null) pagamento.setPreco(dto.preco());
        if(dto.dataVencimento() != null) pagamento.setDataVencimento(dto.dataVencimento());
        if(dto.pagamentoRealizado() != null) pagamento.setPagamentoRealizado(dto.pagamentoRealizado());
        if(dto.pagamento() != null) pagamento.setPagamento(dto.pagamento());
        if(dto.documentoIds() != null) {
            pagamento.setDocumentos(dto.documentoIds().stream()
                .map(docId -> documentoRepository.findById(docId))
                .toList());
        }
        return PagamentoEmpresaResponseDTO.toDTO(pagamento);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        PagamentoEmpresa pagamento = repository.findById(id);
        if (pagamento == null || !pagamento.getAtivo()) {
            throw new NotFoundException();
        }
        pagamento.setAtivo(false);
    }

    
    @Override
    @Transactional
    public PagamentoEmpresaResponseDTO addDocumento(Long id, DocumentoDTO dto) {
        PagamentoEmpresa pagamento = repository.findById(id);
        if (pagamento == null || !pagamento.getAtivo()) {
            throw new NotFoundException();
        }
        
        return PagamentoEmpresaResponseDTO.toDTO(pagamento);
    }
}