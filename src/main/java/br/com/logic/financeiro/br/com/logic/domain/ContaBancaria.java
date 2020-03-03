//package br.com.logic.financeiro.br.com.logic.domain;
//
//import java.util.Objects;
//
//
//public class ContaBancaria {
//
//
//    private Double numeroConta;
//
//    private Double saldo;
//
//    public Boolean sacar(Double valor, ContaBancaria contaBancaria) {
//
//        return null;
//
//    }
//
//    public String depositar(Double valor, ContaBancaria contaBancaria) {
//
//        return null;
//
//    }
//
//    public void depositar(Double valor) {
//
//        this.setSaldo(this.getSaldo() + valor);
//
//    }
//
//    public Double getNumeroConta() {
//        return numeroConta;
//    }
//
//    public void setNumeroConta(Double numeroConta) {
//        this.numeroConta = numeroConta;
//    }
//
//    public Double getSaldo() {
//        return saldo;
//    }
//
//    public void setSaldo(Double saldo) {
//        this.saldo = saldo;
//    }
//
//    public ContaBancaria(Double numeroConta, Double saldo) {
//        this.numeroConta = numeroConta;
//        this.saldo = saldo;
//    }
//
//    public ContaBancaria() {
//
//    }
//
//    public ContaBancaria(Double numeroConta) {
//        this.numeroConta = numeroConta;
//    }
//
//    public String gerarRelatorioContaLogada(ContaBancaria contaBancaria) {
//
//        String relatorioContaLogada = "Saldo: " + contaBancaria.getSaldo() + "\nNumero da conta: " + contaBancaria.getNumeroConta();
//
//        return relatorioContaLogada;
//
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ContaBancaria that = (ContaBancaria) o;
//        return Objects.equals(numeroConta, that.numeroConta);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(numeroConta);
//    }
//}
