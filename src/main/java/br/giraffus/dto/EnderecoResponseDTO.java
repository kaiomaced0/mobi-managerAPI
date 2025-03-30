package br.giraffus.dto;

import br.giraffus.model.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String Logradouro,
        String bairro,
        String numero,
        String cep,
        String complemento,
        String latitude,
        String longitude,
        String cidade,
        String estado
) {
    public static EnderecoResponseDTO toDTO(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getNumero(),
                endereco.getCep(),
                endereco.getComplemento(),
                endereco.getLatitude(),
                endereco.getLongitude(),
                endereco.getCidade(),
                endereco.getEstado().getNome()
        );
    }
}
