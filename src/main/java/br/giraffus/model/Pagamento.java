package br.giraffus.model;

import jakarta.persistence.Entity;

@Entity(name = "Pagamento")
public class Pagamento extends EntityClass{

    private String cpf;

    private Double preco;

    private String dataPagamento;

    private String mesReferente;

    private String anoReferente;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getMesReferente() {
        return mesReferente;
    }

    public void setMesReferente(String mesReferente) {
        this.mesReferente = mesReferente;
    }

    public String getAnoReferente() {
        return anoReferente;
    }

    public void setAnoReferente(String anoReferente) {
        this.anoReferente = anoReferente;
    }
}
