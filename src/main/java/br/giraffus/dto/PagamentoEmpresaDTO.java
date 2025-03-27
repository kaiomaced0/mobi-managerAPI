package br.giraffus.dto;

import java.time.LocalDate;
import java.util.List;

public record PagamentoEmpresaDTO(
    Long ano,
    Long mes,
    Double preco,
    LocalDate dataVencimento,
    Boolean pagamentoRealizado,
    String pagamento,
    List<Long> documentoIds
) {}