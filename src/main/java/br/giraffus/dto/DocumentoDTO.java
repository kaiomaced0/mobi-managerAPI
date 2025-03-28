package br.giraffus.dto;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

public class DocumentoDTO { 

    @FormParam("nomeArquivo")
    @PartType(MediaType.TEXT_PLAIN)
    private String nomeArquivo;

    @FormParam("titulo")
    @PartType(MediaType.TEXT_PLAIN)
    private String titulo;

    @FormParam("descricao")
    @PartType(MediaType.TEXT_PLAIN)
    private String descricao;

    @FormParam("arquivo")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private byte[] arquivo;

    @FormParam("tipo")
    @PartType(MediaType.TEXT_PLAIN)
    private String tipo;


    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public String getTipo() {
        return tipo;
    }
}