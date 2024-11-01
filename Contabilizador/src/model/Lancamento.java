package model;

import java.time.LocalDate;

public class Lancamento {
    private String id;
    private String descricao;
    private TipoLancamento tipo;
    private LocalDate data;
    private Double valor;

    public enum TipoLancamento {RECEITA,DESPESA}

    public Lancamento(String id, String descricao, TipoLancamento tipo, LocalDate data, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;

    }

    public Double getValor() {
        return valor;
    }
    public TipoLancamento getTipo() {
        return tipo;
    }
}
