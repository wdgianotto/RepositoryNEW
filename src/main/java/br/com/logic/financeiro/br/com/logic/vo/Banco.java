package br.com.logic.financeiro.br.com.logic.vo;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Banco implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String ag;


    @JsonManagedReference
    @OneToMany(mappedBy = "banco")
    private List<Conta> conta = new ArrayList<>();

    public Banco(){
    }

    public Banco(Integer id) {
        this.id = id;
    }

    public Banco(Integer id, String nome, String ag) {
        this.id = id;
        this.nome = nome;
        this.ag = ag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAg() {
        return ag;
    }

    public void setAg(String ag) {
        this.ag = ag;
    }

    public List<Conta> getConta() {
        return conta;
    }

    public void setConta(List<Conta> conta) {
        this.conta = conta;
    }
}
