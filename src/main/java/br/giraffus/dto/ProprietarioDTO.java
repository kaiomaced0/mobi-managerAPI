package br.giraffus.dto;

public record ProprietarioDTO(
        String nome,
        String identificacao,
        String email,
        String telefone,
        String descricao
) {
}
