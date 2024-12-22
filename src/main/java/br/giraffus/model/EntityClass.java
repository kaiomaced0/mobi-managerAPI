package br.giraffus.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public class EntityClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataInclusao;

    private Boolean ativo = true;

    @PrePersist // pre inclusao
    private void prePersisting() {
        this.dataInclusao = LocalDateTime.now();
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}