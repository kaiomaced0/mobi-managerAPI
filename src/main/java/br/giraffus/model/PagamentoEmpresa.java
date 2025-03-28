package br.giraffus.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class PagamentoEmpresa extends EntityClass {
    private Long ano;
    private Long mes;
    private Double preco;
    private LocalDate dataVencimento;
    private Boolean pagamentoRealizado;
    private String pagamento;
    
    @OneToMany()
    @JoinColumn(name = "pagamento_empresa_id")
    private List<Documento> documentos;

    public Long getAno() { return ano; }
    public void setAno(Long ano) { this.ano = ano; }
    
    public Long getMes() { return mes; }
    public void setMes(Long mes) { this.mes = mes; }
    
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    
    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }
    
    public Boolean getPagamentoRealizado() { return pagamentoRealizado; }
    public void setPagamentoRealizado(Boolean pagamentoRealizado) { this.pagamentoRealizado = pagamentoRealizado; }
    
    public String getPagamento() { return pagamento; }
    public void setPagamento(String pagamento) { this.pagamento = pagamento; }
    
    public List<Documento> getDocumentos() { return documentos; }
    public void setDocumentos(List<Documento> documentos) { this.documentos = documentos; }
}