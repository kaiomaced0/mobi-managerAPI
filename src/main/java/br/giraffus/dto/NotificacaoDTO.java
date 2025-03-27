package br.giraffus.dto;

import java.time.LocalDate;

public record NotificacaoDTO(
    String mensagem,
    LocalDate dataEnvio,
    String status,
    String tipoNotificacao,
    Long empresaId,
    Long usuarioId
) {}