package model;

public class ContaPatrimonial {
    private String codigo;
    private double saldo;
    private String descricao;
    private tipoConta tipo;

    public enum tipoConta{ATIVO,PASSIVO,PATRIMONIO_LIQUIDO}

    public ContaPatrimonial(String codigo, double saldo, String descricao, tipoConta tipo) {
        this.codigo = codigo;
        this.saldo = saldo;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public tipoConta getTipo() {
        return tipo;
    }
}
