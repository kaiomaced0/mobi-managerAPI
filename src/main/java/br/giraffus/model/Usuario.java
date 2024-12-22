package br.giraffus.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends EntityClass{

    private String nome;

    private LocalDate dataNascimento;

    private String email;

    private String senha;

    private String telefone;

    private String cpf;

    private Boolean mudarSenha; // usado para ele conseguir trocar de senha.


    @OneToMany
    @JoinColumn(name = "usuario_id")
    private List<Imovel> listaImovel;

    @OneToMany
    @JoinColumn(name = "usuario_id")
    private List<Proprietario> listaProprietario;


    public List<Imovel> getListaImovel() {
        return listaImovel;
    }

    public void setListaImovel(List<Imovel> listaImovel) {
        this.listaImovel = listaImovel;
    }

    public List<Proprietario> getListaProprietario() {
        return listaProprietario;
    }

    public void setListaProprietario(List<Proprietario> listaProprietario) {
        this.listaProprietario = listaProprietario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getMudarSenha() {
        return mudarSenha;
    }

    public void setMudarSenha(Boolean mudarSenha) {
        this.mudarSenha = mudarSenha;
    }
}
