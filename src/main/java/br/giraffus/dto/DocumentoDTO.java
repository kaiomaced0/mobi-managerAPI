package br.giraffus.dto;

public record DocumentoDTO(
    String nome,
    String tipo,
    byte[] arquivo
) {}