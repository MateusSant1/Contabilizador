package service;

import model.AnaliseResultadoExercicio;

public class RelatorioService {
    public void gerarRelatorio(AnaliseResultadoExercicio analise) {
        Double resultado = analise.calcularResutados();

        System.out.printf("Resultado do exercício: "+ resultado);
    }
}
