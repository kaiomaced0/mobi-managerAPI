package br.giraffus.dto;
import jakarta.validation.constraints.NotBlank;

public record UsuarioUpdateLoginDTO(
        @NotBlank String login) {

}
