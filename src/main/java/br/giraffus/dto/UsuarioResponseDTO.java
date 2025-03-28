package br.giraffus.dto;

import br.giraffus.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String email,
    String login,
    String cargo
) {
    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getLogin(),
            usuario.getCargo()
        );
    }
}