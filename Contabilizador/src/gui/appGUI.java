package gui;

import model.AnaliseResultadoExercicio;
import model.Lancamento;
import service.LancamentoService;
import service.RelatorioService;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class appGUI {
    public static void main(String[] args) {
        // Configurar os modelos e serviços
        AnaliseResultadoExercicio analiseExercicio = new AnaliseResultadoExercicio(null);
        LancamentoService lancamentoService = new LancamentoService(analiseExercicio);
        RelatorioService relatorioService = new RelatorioService();

        // Criar a janela principal
        JFrame frame = new JFrame("Gestão Financeira");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Formulário de lançamento
        JLabel lblDescricao = new JLabel("Descrição:");
        JTextField txtDescricao = new JTextField();

        JLabel lblData = new JLabel("Data (YYYY-MM-DD):");
        JTextField txtData = new JTextField();

        JLabel lblValor = new JLabel("Valor:");
        JTextField txtValor = new JTextField();

        JLabel lblTipo = new JLabel("Tipo:");
        JComboBox<Lancamento.TipoLancamento> cbTipo = new JComboBox<>(Lancamento.TipoLancamento.values());

        JButton btnRegistrar = new JButton("Registrar Lançamento");

        // Área de relatório
        JTextArea txtRelatorio = new JTextArea(10, 30);
        txtRelatorio.setEditable(false);
        JScrollPane scrollRelatorio = new JScrollPane(txtRelatorio);

        JButton btnGerarRelatorio = new JButton("Gerar Relatório");
        JButton btnSalvarRelatorio = new JButton("Salvar Relatório");

        // Adicionar ações aos botões
        btnRegistrar.addActionListener(e -> {
            try {
                String descricao = txtDescricao.getText();
                LocalDate data = LocalDate.parse(txtData.getText());
                Double valor = Double.parseDouble(txtValor.getText());
                Lancamento.TipoLancamento tipo = (Lancamento.TipoLancamento) cbTipo.getSelectedItem();

                lancamentoService.registrarLancamento(descricao, data, valor, tipo);
                txtRelatorio.append("Lançamento registrado: " + descricao + " - " + valor + " (" + tipo + ")\n");

                // Limpar campos
                txtDescricao.setText("");
                txtData.setText("");
                txtValor.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao registrar lançamento: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnGerarRelatorio.addActionListener(e -> {
            txtRelatorio.append("\n[Relatório Gerado]\n");
            Double resultado = analiseExercicio.calcularResutados();
            String relatorio = "Resultado do exercício: " + resultado + "\n";
            txtRelatorio.append(relatorio);
        });

        btnSalvarRelatorio.addActionListener(e -> {
            String relatorio = txtRelatorio.getText();
            if (relatorio.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum relatório para salvar!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar Relatório");
            fileChooser.setSelectedFile(new java.io.File("relatorio.txt"));
            int userSelection = fileChooser.showSaveDialog(frame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                    writer.write(relatorio);
                    JOptionPane.showMessageDialog(frame, "Relatório salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao salvar relatório: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adicionar componentes ao painel
        panel.add(lblDescricao);
        panel.add(txtDescricao);
        panel.add(lblData);
        panel.add(txtData);
        panel.add(lblValor);
        panel.add(txtValor);
        panel.add(lblTipo);
        panel.add(cbTipo);
        panel.add(btnRegistrar);
        panel.add(btnGerarRelatorio);
        panel.add(btnSalvarRelatorio);
        panel.add(scrollRelatorio);

        // Adicionar o painel à janela
        frame.add(panel);
        frame.setVisible(true);
    }
}
