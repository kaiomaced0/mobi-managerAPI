package br.giraffus.dto;

import br.giraffus.model.Proprietario;

public record ProprietarioResponseDTO(
        Long id,
        String nome,
        String identificacao,
        String email,
        String telefone,
        String descricao
) {
    public static ProprietarioResponseDTO toDTO(Proprietario proprietario) {
        return new ProprietarioResponseDTO(
                proprietario.getId(),
                proprietario.getNome(),
                proprietario.getIdentificacao(),
                proprietario.getEmail(),
                proprietario.getTelefone(),
                proprietario.getDescricao()
        );
    }
}
