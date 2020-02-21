package br.com.logic.financeiro.br.com.logic.vo;

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
    private String nomeCliente;
    private String cpf;
    private String nomeBanco;
    private String ag;
    private Integer numeroConta;
    private Double saldo;

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

    public Conta(Integer id, String nome, String cpf, String nomeBanco, String ag, Integer numeroConta, Double saldo, Cliente cliente, Banco banco, TipoConta tipoConta) {
        this.id = id;
        this.nomeCliente = nome;
        this.cpf = cpf;
        this.nomeBanco = nomeBanco;
        this.ag = ag;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cliente = cliente;
        this.banco = banco;
        this.tipoConta = tipoConta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getAg() {
        return ag;
    }

    public void setAg(String ag) {
        this.ag = ag;
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
