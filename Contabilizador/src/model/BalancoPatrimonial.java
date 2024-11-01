package model;

import java.util.ArrayList;
import java.util.List;

public class BalancoPatrimonial {
    private List<ContaPatrimonial> contas;

    public BalancoPatrimonial(List<ContaPatrimonial> contas) {
        this.contas = new ArrayList<>();
    }

    public void addContaPatrimonial(ContaPatrimonial conta) {
        contas.add(conta);
    }

    public void calculaAtivo(ContaPatrimonial conta) {
        contas.stream().filter(c -> c.getTipo()== ContaPatrimonial.tipoConta.ATIVO).mapToDouble(
                ContaPatrimonial::getSaldo).sum();

    }

    public void calculaPassivo(ContaPatrimonial conta) {
        contas.stream().filter(c -> c.getTipo()== ContaPatrimonial.tipoConta.PASSIVO).mapToDouble(
                ContaPatrimonial::getSaldo).sum();

    }

    public String calculaSituacao(ContaPatrimonial conta) {
        double ativo = contas.stream().filter(c -> c.getTipo()== ContaPatrimonial.tipoConta.ATIVO).mapToDouble(
                ContaPatrimonial::getSaldo).sum();
        double passivo = contas.stream().filter(c -> c.getTipo()== ContaPatrimonial.tipoConta.PASSIVO).mapToDouble(
                ContaPatrimonial::getSaldo).sum();
        double patrimonioLiquido = contas.stream().filter(c -> c.getTipo()== ContaPatrimonial.tipoConta.PATRIMONIO_LIQUIDO).mapToDouble(
                ContaPatrimonial::getSaldo).sum();

        if (ativo - (passivo + patrimonioLiquido) > 0) {
            return "Situação Positiva";
        } else {
            return "Situação Negativa";
        }

    }

}
