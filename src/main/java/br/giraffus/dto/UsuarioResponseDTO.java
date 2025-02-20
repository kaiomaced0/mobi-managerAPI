package br.giraffus.dto;

import br.giraffus.model.Usuario;

import java.time.LocalDate;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        String senha,
        String telefone,
        String cpf,
        LocalDate dataNascimento
) {
    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getTelefone(),
                usuario.getCpf(),
                usuario.getDataNascimento()
        );
    }
}
