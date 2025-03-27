package br.giraffus.dto;

import br.giraffus.model.Notificacao;
import java.time.LocalDate;

public record NotificacaoResponseDTO(
    Long id,
    String mensagem,
    LocalDate dataEnvio,
    String status,
    String tipoNotificacao,
    Long empresaId,
    Long usuarioId
) {
    public static NotificacaoResponseDTO toDTO(Notificacao notificacao) {
        return new NotificacaoResponseDTO(
            notificacao.getId(),
            notificacao.getMensagem(),
            notificacao.getDataEnvio(),
            notificacao.getStatus(),
            notificacao.getTipoNotificacao(),
            notificacao.getEmpresa().getId(),
            notificacao.getUsuario().getId()
        );
    }
}