package br.giraffus.dto;

public record EnderecoDTO(
        String quadra,
        String rua,
        String cep,
        String descricao,
        String bairro,
        String numero,
        Long idCidade
) {
}
