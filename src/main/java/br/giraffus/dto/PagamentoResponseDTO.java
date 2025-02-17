package br.giraffus.dto;

import br.giraffus.model.Pagamento;

public record PagamentoResponseDTO(
        Long id,
        String cpf,
        Double preco,
        String dataPagamento,
        String mesReferente,
        String anoReferente) {
    public static PagamentoResponseDTO toDTO(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getCpf(),
                pagamento.getPreco(),
                pagamento.getDataPagamento(),
                pagamento.getMesReferente(),
                pagamento.getAnoReferente());
    }
}
