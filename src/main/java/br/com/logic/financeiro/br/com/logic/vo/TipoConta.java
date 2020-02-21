package br.com.logic.financeiro.br.com.logic.vo;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipoConta implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipoConta;

    @JsonManagedReference
    @OneToMany(mappedBy = "tipoConta")
    private List<Conta> conta = new ArrayList<>();

    public TipoConta(Integer id, String tipoConta) {
        this.id = id;
        this.tipoConta = tipoConta;
    }

    public TipoConta() {
    }

    public TipoConta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }
}
