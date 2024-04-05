package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProtocoloController controller = new ProtocoloController();
        ProtocoloUtil util = new ProtocoloUtil();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        util.exibirMenu();
        opcao = scanner.nextInt();
        scanner.nextLine();
        util.processarOpcao(opcao, controller);

        scanner.close();
    }
}