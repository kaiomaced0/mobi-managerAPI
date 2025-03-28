package br.giraffus.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthUsuarioDTO(
        @NotBlank String login,
        @NotBlank String senha

) {
}
