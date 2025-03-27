package br.giraffus.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Empresa extends EntityClass {
    private String nome;
    
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;
    
    @OneToOne
    private Plano plano;
    
    @OneToOne
    private Conta conta;
    
    @OneToOne
    private PagamentoEmpresa pagamentoEmpresa;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }
    
    public Plano getPlano() { return plano; }
    public void setPlano(Plano plano) { this.plano = plano; }
    
    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }
    
    public PagamentoEmpresa getPagamentoEmpresa() { return pagamentoEmpresa; }
    public void setPagamentoEmpresa(PagamentoEmpresa pagamentoEmpresa) { 
        this.pagamentoEmpresa = pagamentoEmpresa; 
    }
}