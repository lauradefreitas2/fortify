package org.example;

import java.util.Date;

public class ProtocoloModel {
    private int numeroProtocolo;
    private Cliente cliente;
    private Date dataAbertura;
    private String descricao;
    private TipoProtocolo tipoProtocolo;

    public ProtocoloModel(int numeroProtocolo, Cliente cliente, Date dataAbertura, String descricao, TipoProtocolo tipoProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
        this.cliente = cliente;
        this.dataAbertura = dataAbertura;
        this.descricao = descricao;
        this.tipoProtocolo = tipoProtocolo;

    }

    // Getters e setters
    public int getNumeroProtocolo() {
        return numeroProtocolo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoProtocolo getTipoProtocolo() {
        return tipoProtocolo;
    }

    public void setTipoProtocolo(TipoProtocolo tipoProtocolo) {
        this.tipoProtocolo = tipoProtocolo;
    }
}

