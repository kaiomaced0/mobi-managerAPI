package br.giraffus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Documento extends EntityClass {
    
    private String nome;
    private String tipo;
    
    @Lob
    private byte[] arquivo;
    
    @ManyToOne
    private PagamentoEmpresa pagamentoEmpresa;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public byte[] getArquivo() { return arquivo; }
    public void setArquivo(byte[] arquivo) { this.arquivo = arquivo; }
    
    public PagamentoEmpresa getPagamentoEmpresa() { return pagamentoEmpresa; }
    public void setPagamentoEmpresa(PagamentoEmpresa pagamentoEmpresa) { 
        this.pagamentoEmpresa = pagamentoEmpresa; 
    }
}