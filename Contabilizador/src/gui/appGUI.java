package gui;

import model.AnaliseResultadoExercicio;
import model.Lancamento;
import service.LancamentoService;
import service.RelatorioService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);

        // Painel principal com estilo moderno
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Painel de entrada com design moderno
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fontes e cores modernas
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color labelColor = new Color(50, 50, 50);

        // Descrição
        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setFont(labelFont);
        lblDescricao.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(lblDescricao, gbc);

        JTextField txtDescricao = new JTextField();
        txtDescricao.setFont(inputFont);
        txtDescricao.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtDescricao.setPreferredSize(new Dimension(300, 40));
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(txtDescricao, gbc);

        // Data
        JLabel lblData = new JLabel("Data (YYYY-MM-DD):");
        lblData.setFont(labelFont);
        lblData.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lblData, gbc);

        JTextField txtData = new JTextField();
        txtData.setFont(inputFont);
        txtData.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtData.setPreferredSize(new Dimension(300, 40));
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(txtData, gbc);

        // Valor
        JLabel lblValor = new JLabel("Valor:");
        lblValor.setFont(labelFont);
        lblValor.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(lblValor, gbc);

        JTextField txtValor = new JTextField();
        txtValor.setFont(inputFont);
        txtValor.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtValor.setPreferredSize(new Dimension(300, 40));
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(txtValor, gbc);

        // Tipo
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(labelFont);
        lblTipo.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(lblTipo, gbc);

        JComboBox<Lancamento.TipoLancamento> cbTipo = new JComboBox<>(Lancamento.TipoLancamento.values());
        cbTipo.setFont(inputFont);
        cbTipo.setPreferredSize(new Dimension(300, 40));
        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(cbTipo, gbc);

        // Botões com estilo moderno
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnRegistrar = createStyledButton("Registrar Lançamento", new Color(33, 150, 243));
        JButton btnGerarRelatorio = createStyledButton("Gerar Relatório", new Color(76, 175, 80));
        JButton btnSalvarRelatorio = createStyledButton("Salvar Relatório", new Color(255, 152, 0));

        buttonPanel.add(btnRegistrar);
        buttonPanel.add(btnGerarRelatorio);
        buttonPanel.add(btnSalvarRelatorio);

        // Área de relatório
        JTextArea txtRelatorio = new JTextArea(10, 30);
        txtRelatorio.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtRelatorio.setEditable(false);
        JScrollPane scrollRelatorio = new JScrollPane(txtRelatorio);
        scrollRelatorio.setBorder(BorderFactory.createTitledBorder("Relatório"));

        // Adicionar componentes ao painel principal
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollRelatorio, BorderLayout.SOUTH);

        // Adicionar ações aos botões
        btnRegistrar.addActionListener(e -> {
            try {
                String descricao = txtDescricao.getText();
                LocalDate data = LocalDate.parse(txtData.getText());
                Double valor = Double.parseDouble(txtValor.getText());
                Lancamento.TipoLancamento tipo = (Lancamento.TipoLancamento) cbTipo.getSelectedItem();

                lancamentoService.registrarLancamento(descricao, data, valor, tipo);
                txtRelatorio.append("Lançamento registrado: " + descricao + " - R$ " + valor + " (" + tipo + ")\n");

                // Limpar campos
                txtDescricao.setText("");
                txtData.setText("");
                txtValor.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao registrar o lançamento, existe algum campo em falta ou com informação errada", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnGerarRelatorio.addActionListener(e -> {
            txtRelatorio.append("\n[Relatório Gerado]\n");
            Double resultado = analiseExercicio.calcularResutados();
            String relatorio = "Resultado do exercício: R$" + resultado + "\n";
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
                    JOptionPane.showMessageDialog(frame, "Erro ao salvar relatório", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adicionar o painel à janela
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    // Criação de botões estilizados
    private static JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 40));

        // Efeito de hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(button.getBackground().darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }
}