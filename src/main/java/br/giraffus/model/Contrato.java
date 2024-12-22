package br.giraffus.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Contrato extends EntityClass {

    private String diaVencimento;

    private LocalDate dataInicioContrato;

    private LocalDate dataFimContrato;

    @OneToMany
    @JoinColumn(name = "contrato_id")
    private List<Pagamento> listaPagamento;

    @ManyToOne
    @JoinColumn(name = "inquilino_id")
    private Inquilino inquilino;


    public String getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(String diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public LocalDate getDataInicioContrato() {
        return dataInicioContrato;
    }

    public void setDataInicioContrato(LocalDate dataInicioContrato) {
        this.dataInicioContrato = dataInicioContrato;
    }

    public LocalDate getDataFimContrato() {
        return dataFimContrato;
    }

    public void setDataFimContrato(LocalDate dataFimContrato) {
        this.dataFimContrato = dataFimContrato;
    }

    public List<Pagamento> getListaPagamento() {
        return listaPagamento;
    }

    public void setListaPagamento(List<Pagamento> listaPagamento) {
        this.listaPagamento = listaPagamento;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }
}
