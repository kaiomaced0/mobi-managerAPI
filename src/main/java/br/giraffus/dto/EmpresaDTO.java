package br.giraffus.dto;

import java.util.List;

public record EmpresaDTO(
    String nome,
    List<Long> usuarioIds,
    Long planoId,
    Long contaId,
    Long pagamentoEmpresaId
) {}