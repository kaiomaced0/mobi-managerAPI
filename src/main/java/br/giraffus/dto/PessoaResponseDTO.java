package br.giraffus.dto;

import br.giraffus.model.Pessoa;

public record PessoaResponseDTO(
    Long id,
    String nome, 
    String cpf,
    String telefone,
    String email
) {
    public static PessoaResponseDTO toDTO(Pessoa pessoa) {
        return new PessoaResponseDTO(
            pessoa.getId(),
            pessoa.getNome(),
            pessoa.getCpf(),
            pessoa.getTelefone(),
            pessoa.getEmail()
        );
    }
}