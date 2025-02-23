package br.com.colli.model;

import jakarta.inject.Named;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity(name = "historico_alugueis")
public class HistoricoAlugueis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluguel_id")
    private Long aluguelId;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "houve_avaria")
    private Boolean houveAvaria;
    private String observacao;

    // Getters e Setters
    public Long getAluguelId() {
        return aluguelId;
    }

    public void setAluguelId(Long aluguelId) {
        this.aluguelId = aluguelId;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Boolean getHouveAvaria() {
        return houveAvaria;
    }

    public void setHouveAvaria(Boolean houveAvaria) {
        this.houveAvaria = houveAvaria;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}

