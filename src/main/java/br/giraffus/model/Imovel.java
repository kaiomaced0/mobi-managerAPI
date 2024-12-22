package br.giraffus.model;

import br.giraffus.enums.Imovel.CategoriaImovel;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Imovel extends EntityClass{

    private Integer qtdQuartos;

    private Integer qtdGaragem;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Column(name = "categoriaimovel_id")
    private CategoriaImovel categoriaImovel;

    private Double valorIptu;

    private Double valorTotalImovel;


    public Integer getQtdQuartos() {
        return qtdQuartos;
    }

    public void setQtdQuartos(Integer qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
    }

    public Integer getQtdGaragem() {
        return qtdGaragem;
    }

    public void setQtdGaragem(Integer qtdGaragem) {
        this.qtdGaragem = qtdGaragem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public CategoriaImovel getCategoriaImovel() {
        return categoriaImovel;
    }

    public void setCategoriaImovel(CategoriaImovel categoriaImovel) {
        this.categoriaImovel = categoriaImovel;
    }

    public Double getValorIptu() {
        return valorIptu;
    }

    public void setValorIptu(Double valorIptu) {
        this.valorIptu = valorIptu;
    }

    public Double getValorTotalImovel() {
        return valorTotalImovel;
    }

    public void setValorTotalImovel(Double valorTotalImovel) {
        this.valorTotalImovel = valorTotalImovel;
    }
}
