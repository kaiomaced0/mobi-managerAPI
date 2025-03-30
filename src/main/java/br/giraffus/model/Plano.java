package br.giraffus.model;

import jakarta.persistence.Entity;

@Entity(name = "Plano")
public class Plano extends EntityClass {
    
    private String nome;
    private Double preco;
    private String descricao;
    private Integer maxUsuarios;
    private Integer maxContratos;
    private Integer maxImoveis;
    private Integer maxArmazenamentoDocumentosMb;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public Integer getMaxUsuarios() { return maxUsuarios; }
    public void setMaxUsuarios(Integer maxUsuarios) { this.maxUsuarios = maxUsuarios; }
    
    public Integer getMaxContratos() { return maxContratos; }
    public void setMaxContratos(Integer maxContratos) { this.maxContratos = maxContratos; }
    
    public Integer getMaxImoveis() { return maxImoveis; }
    public void setMaxImoveis(Integer maxImoveis) { this.maxImoveis = maxImoveis; }
    
    public Integer getMaxArmazenamentoDocumentosMb() { return maxArmazenamentoDocumentosMb; }
    public void setMaxArmazenamentoDocumentosMb(Integer maxArmazenamentoDocumentosMb) { 
        this.maxArmazenamentoDocumentosMb = maxArmazenamentoDocumentosMb; 
    }
}