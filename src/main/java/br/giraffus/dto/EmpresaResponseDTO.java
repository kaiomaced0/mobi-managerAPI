package br.giraffus.dto;

import java.util.List;

import br.giraffus.model.Empresa;

public record EmpresaResponseDTO(
    Long id,
    String nome,
    List<Long> usuarioIds,
    Long planoId,
    Long contaId,
    Long pagamentoEmpresaId
) {
    public static EmpresaResponseDTO toDTO(Empresa empresa) {
        List<Long> usuarioIds = empresa.getUsuarios().stream()
            .map(usuario -> usuario.getId())
            .toList();
            
        return new EmpresaResponseDTO(
            empresa.getId(),
            empresa.getNome(),
            usuarioIds,
            empresa.getPlano().getId(),
            empresa.getConta().getId(),
            empresa.getPagamentoEmpresa().getId()
        );
    }
}