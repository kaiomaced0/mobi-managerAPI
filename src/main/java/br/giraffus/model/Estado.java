package br.giraffus.model;

import jakarta.persistence.Entity;

@Entity(name = "Estado")
public class Estado extends EntityClass{

    private String nome;

    private String sigla;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
