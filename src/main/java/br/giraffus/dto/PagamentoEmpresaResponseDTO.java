package br.giraffus.dto;

import br.giraffus.model.PagamentoEmpresa;
import java.time.LocalDate;
import java.util.List;

public record PagamentoEmpresaResponseDTO(
    Long id,
    Long ano,
    Long mes,
    Double preco,
    LocalDate dataVencimento,
    Boolean pagamentoRealizado,
    String pagamento,
    List<Long> documentoIds
) {
    public static PagamentoEmpresaResponseDTO toDTO(PagamentoEmpresa pagamento) {
        List<Long> documentoIds = pagamento.getDocumentos().stream()
            .map(doc -> doc.getId())
            .toList();
            
        return new PagamentoEmpresaResponseDTO(
            pagamento.getId(),
            pagamento.getAno(),
            pagamento.getMes(),
            pagamento.getPreco(),
            pagamento.getDataVencimento(),
            pagamento.getPagamentoRealizado(),
            pagamento.getPagamento(),
            documentoIds
        );
    }
}