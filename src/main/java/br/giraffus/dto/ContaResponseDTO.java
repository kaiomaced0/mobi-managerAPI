package br.giraffus.dto;

import br.giraffus.model.Conta;

public record ContaResponseDTO(
    Long id,
    String cpf,
    String cnpj,
    String nome,
    String nomeFantasia
) {
    public static ContaResponseDTO toDTO(Conta conta) {
        return new ContaResponseDTO(
            conta.getId(),
            conta.getCpf(),
            conta.getCnpj(),
            conta.getNome(),
            conta.getNomeFantasia()
        );
    }
}