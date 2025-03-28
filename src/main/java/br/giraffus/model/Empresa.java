package br.giraffus.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Empresa extends EntityClass {
    private String nome;
    
    @OneToMany
    @JoinColumn(name = "id_empresa")
    private List<Usuario> usuarios;
    
    @ManyToOne
    @JoinColumn(name = "id_plano")
    private Plano plano;
    
    @OneToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;
    
    @OneToMany
    @JoinColumn(name = "id_pagamento_empresa")
    private List<PagamentoEmpresa> pagamentoEmpresa;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }
    
    public Plano getPlano() { return plano; }
    public void setPlano(Plano plano) { this.plano = plano; }
    
    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }

    public List<PagamentoEmpresa> getPagamentoEmpresa() {
        return pagamentoEmpresa;
    }
    public void setPagamentoEmpresa(List<PagamentoEmpresa> pagamentoEmpresa) {
        this.pagamentoEmpresa = pagamentoEmpresa;
    }

    
}