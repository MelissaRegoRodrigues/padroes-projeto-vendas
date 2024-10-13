package api;

import api.models.pagamento.*;
import api.patterns.pagamento.COR.PagamentoHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        PagamentoHandler handlers = PagamentoHandler.encadear(new PagamentoCartaoDebito(), new PagamentoPix());
        String opcao;
        boolean continuar = true;
        System.out.println("Bem-vindo à nossa loja!");

        do {
            System.out.println("Você é:");
            System.out.println("1 - Cliente");
            System.out.println("2 - Administrador");
            System.out.println("Digite 'sair' para encerrar.");

            opcao = sc.nextLine().toLowerCase().trim();

            switch (opcao) {
                case "1":
                    System.out.println("Você escolheu Cliente.");
                    mostrarMenuCliente();
                    continuar = false;
                    break;
                case "2":
                    System.out.println("Você escolheu Administrador.");
                    mostrarMenuAdministrador();
                    continuar = false;
                    break;
                case "sair":
                    System.out.println("Saindo do sistema...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção desconhecida, escolha uma opção entre 1, 2 ou 'sair'.");
            }

            System.out.println();

        } while (continuar);

        sc.close();
    }


    public static void mostrarMenuCliente() {
        System.out.println("Menu do Cliente (a ser implementado)...");
    }

    public static void mostrarMenuAdministrador() {
        System.out.println("Menu do Administrador (a ser implementado)...");
    }
}
