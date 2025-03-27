package br.giraffus.model;


import java.util.Set;

import br.giraffus.enums.usuario.Perfil;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

@Entity(name = "Usuario")
public class Usuario extends Pessoa{

    private String email;

    private String senha;

    private String login;

    private Boolean mudarSenha;

    private String cargo;

    @ElementCollection
    @CollectionTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"))
    @Column(name = "perfil", length = 30)
    private Set<Perfil> perfis;


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


    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public Boolean getMudarSenha() {
        return mudarSenha;
    }


    public void setMudarSenha(Boolean mudarSenha) {
        this.mudarSenha = mudarSenha;
    }


    public String getCargo() {
        return cargo;
    }


    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


    public Set<Perfil> getPerfis() {
        return perfis;
    }


    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }

    
}
