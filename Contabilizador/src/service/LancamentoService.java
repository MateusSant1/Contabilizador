package service;
import model.AnaliseResultadoExercicio;
import model.Lancamento;
import java.time.LocalDate;
import java.util.UUID;

public class LancamentoService {
    private AnaliseResultadoExercicio analiseExercicio;
    public LancamentoService(AnaliseResultadoExercicio analiseExercicio) {
        this.analiseExercicio = analiseExercicio;
    }

    public void registrarLancamento(String descricao, LocalDate data,Double valor, Lancamento.TipoLancamento tipo) {
        Lancamento lancamento = new Lancamento(UUID.randomUUID().toString(),descricao, tipo, data, valor);

        analiseExercicio.adicionarLancamento(lancamento);
    }
}
