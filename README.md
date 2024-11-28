# Gerenciador de Receitas e Despesas

## **Descrição do Projeto**
Este projeto tem como objetivo o desenvolvimento de uma aplicação voltada para a gestão financeira, permitindo o registro de receitas e despesas de maneira prática e eficiente. O sistema foi criado no contexto da disciplina de **Contabilidade**, com funcionalidades essenciais para o controle financeiro e geração de relatórios.

---

## **Equipe do Projeto**
- **Raphael José**  
- **Mateus Henrique**  
- **Enzo Figueiredo**

---

## **Funcionalidades**
1. **Adição de Lançamentos**  
   - Campos para entrada de:  
     - **Descrição:** Detalhamento do lançamento.  
     - **Data:** Data do registro.  
     - **Valor:** Montante financeiro.  
     - **Tipo:** Receita ou Despesa.  

2. **Registro de Lançamentos**  
   - Armazena as informações de receitas e despesas.

3. **Geração de Relatórios**  
   - Calcula e exibe o **resultado do exercício financeiro** (saldo entre receitas e despesas).  

4. **Salvamento de Relatórios**  
   - Opção para salvar o relatório gerado em um arquivo **.txt**, permitindo:  
     - Inspeção futura.  
     - Integração com sistemas de automação.

---

## **Tecnologias Utilizadas**
- **Java**  
  - Linguagem principal para o desenvolvimento da aplicação.  

- **Swing (javax.swing)**  
  - Biblioteca utilizada para criar a interface gráfica do sistema.  

---

## **Processos do Sistema**
### 1. **Adição de Campos**
Os usuários podem registrar lançamentos financeiros preenchendo os seguintes campos:  
- **Descrição:** Explicação detalhada do lançamento.  
- **Data:** Escolha do dia em que ocorreu a receita ou despesa.  
- **Valor:** O montante registrado.  
- **Tipo:** Define se é uma **receita** ou **despesa**.  

### 2. **Registro de Lançamentos**
Todos os lançamentos adicionados são salvos na memória do sistema e utilizados para cálculos financeiros posteriores.

### 3. **Geração de Relatório**
O sistema processa os dados de receitas e despesas para entregar um **resumo financeiro**, apresentando o saldo total ao usuário.

### 4. **Salvamento do Relatório**
Os relatórios podem ser exportados em formato **.txt**, permitindo sua análise futura ou compartilhamento com outros sistemas.

---

## **Como Utilizar**
1. Abra o sistema e preencha os campos necessários para registrar os lançamentos financeiros.  
2. Salve os registros e visualize o relatório com o resultado do exercício.  
3. Exporte o relatório para um arquivo **.txt**, se necessário.  
