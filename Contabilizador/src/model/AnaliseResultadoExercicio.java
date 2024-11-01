package model;

import java.util.ArrayList;
import java.util.List;

public class AnaliseResultadoExercicio {
    List<Lancamento> lancamentos;

    public AnaliseResultadoExercicio(List<Lancamento> lancamentos) {
        this.lancamentos = new ArrayList<>();
    }

    public void adicionarLancamento(Lancamento lancamento) {
        lancamentos.add(lancamento);
    }

    public Double calcularResutados() {
        double despesas = lancamentos.stream().filter(d -> d.getTipo()== Lancamento.TipoLancamento.DESPESA).mapToDouble(Lancamento::getValor).sum();
        double receitas = lancamentos.stream().filter(l -> l.getTipo()== Lancamento.TipoLancamento.RECEITA).mapToDouble(Lancamento::getValor).sum();
        return receitas - despesas;
    }

}


