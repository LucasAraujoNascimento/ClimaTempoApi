package com.nascimento.api_clima.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "historico")
public class HistoricoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String cidade;
    private String temperatura;
    private String descricao;

    @Column(name = "data_hora")
    private Timestamp dataHora;

    public HistoricoModel() {
    }

    public HistoricoModel(int id, String cidade, String temperatura, String descricao, Timestamp dataHora) {
        this.id = id;
        this.cidade = cidade;
        this.temperatura = temperatura;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }
}
