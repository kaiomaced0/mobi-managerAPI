package br.giraffus.dto;

public record PagamentoDTO(
        String cpf,
        Double preco,
        String dataPagamento,
        String mesReferente,
        String anoReferente
) {
}
