package br.giraffus.dto;

import br.giraffus.model.Inquilino;

public record InquilinoResponseDTO(
        Long id,
        String nome,
        String telefone,
        String email,
        String cpf
) {
    public static InquilinoResponseDTO toDTO(Inquilino inquilino){
        return new InquilinoResponseDTO(
                inquilino.getId(),
                inquilino.getNome(),
                inquilino.getTelefone(),
                inquilino.getEmail(),
                inquilino.getCpf()
        );
    }
}
