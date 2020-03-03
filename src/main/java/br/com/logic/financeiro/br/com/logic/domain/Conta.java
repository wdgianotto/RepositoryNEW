package br.com.logic.financeiro.br.com.logic.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Conta implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numeroConta;
    private Double saldo;
    private Double credito;

    @JsonBackReference
    @ManyToOne
            @JoinColumn(name = "idcliente")
    private Cliente cliente;

    @JsonBackReference
    @ManyToOne
            @JoinColumn(name = "idbanco")
    private Banco banco;

    @JsonBackReference
    @ManyToOne
            @JoinColumn(name = "idtipoconta")
    private TipoConta tipoConta;

    public Conta(){
    }

    public Conta(Integer id, Integer numeroConta, Double saldo,
                 Cliente cliente, Banco banco, TipoConta tipoConta, Double credito) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cliente = cliente;
        this.banco = banco;
        this.tipoConta = tipoConta;
        if(tipoConta.getId() == 1) {
            this.credito = credito;
        }else{
            this.credito = 0.00;
        }
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(id, conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
