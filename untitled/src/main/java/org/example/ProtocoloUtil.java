package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ProtocoloUtil {
    private static final ProtocoloService service = new ProtocoloService();
    private static Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Abrir protocolo");
            System.out.println("2 - Consultar protocolo");
            System.out.println("3 - Deletar protocolo");
            System.out.println("4 - Editar protocolo");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();


            ProtocoloController controller = new ProtocoloController();
            this.processarOpcao(opcao, controller);
        } while (opcao != 5);
    }
    public void processarOpcao(int opcao, ProtocoloController controller) {
        switch (opcao) {
            case 1:
                abrirProtocolo(controller);
                break;
            case 2:
                consultarProtocolo(controller);
                break;
            case 3:
                deletarProtocolo(controller);
                break;
            case 4:
                editarProtocolo(controller);
                break;
            case 5:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void abrirProtocolo(ProtocoloController controller) {
        System.out.println("Abrir protocolo:");

        int numeroProtocolo = 0;
        while (numeroProtocolo <= 0) {
            System.out.print("Número do protocolo: ");
            if (scanner.hasNextInt()) {
                numeroProtocolo = scanner.nextInt();
                if (numeroProtocolo <= 0) {
                    System.out.println("O número do protocolo deve ser um valor inteiro positivo.");
                }
            } else {
                System.out.println("Por favor, insira um número válido para o protocolo.");
                scanner.next();
            }
        }

        scanner.nextLine();

        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine().trim();
        while (nomeCliente.isEmpty()) {
            System.out.println("O nome do cliente não pode estar vazio.");
            System.out.print("Nome do cliente: ");
            nomeCliente = scanner.nextLine().trim();
        }

        System.out.print("CPF do cliente: ");
        String cpfCliente = scanner.nextLine().trim();
        while (!isValidCPF(cpfCliente)) {
            System.out.println("O CPF inserido não é válido.");
            System.out.print("CPF do cliente: ");
            cpfCliente = scanner.nextLine().trim();
        }

        System.out.print("Telefone do cliente: ");
        String telefoneCliente = scanner.nextLine().trim();
        while (telefoneCliente.isEmpty()) {
            System.out.println("O telefone do cliente não pode estar vazio.");
            System.out.print("Telefone do cliente: ");
            telefoneCliente = scanner.nextLine().trim();
        }

        System.out.print("Data de abertura (formato dd/MM/yyyy): ");
        Date dataAbertura = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        while (dataAbertura == null) {
            String dataInput = scanner.nextLine().trim();
            try {
                dataAbertura = sdf.parse(dataInput);
            } catch (ParseException e) {
                System.out.println("Data inserida inválida. Por favor, insira no formato dd/MM/yyyy.");
                System.out.print("Data de abertura (formato dd/MM/yyyy): ");
            }
        }

        System.out.print("Descrição do protocolo: ");
        String descricao = scanner.nextLine().trim();
        while (descricao.isEmpty()) {
            System.out.println("A descrição do protocolo não pode estar vazia.");
            System.out.print("Descrição do protocolo: ");
            descricao = scanner.nextLine().trim();
        }

        System.out.print("Tipo do protocolo (reclamação, consulta, elogio): ");
        String tipoInput = scanner.nextLine().trim().toUpperCase();
        TipoProtocolo tipoProtocolo = null;
        while (tipoProtocolo == null) {
            try {
                tipoProtocolo = TipoProtocolo.valueOf(tipoInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de protocolo inserido inválido. Insira reclamação, consulta ou elogio.");
                System.out.print("Tipo do protocolo (reclamação, consulta, elogio): ");
                tipoInput = scanner.nextLine().trim().toUpperCase();
            }
        }

        ProtocoloModel protocolo = new ProtocoloModel(numeroProtocolo, new Cliente(nomeCliente, cpfCliente, telefoneCliente), dataAbertura, descricao, tipoProtocolo);

        controller.adicionarProtocolo(protocolo);

        System.out.println("Protocolo aberto com sucesso.");
    }



    private boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");
        if (cpf.length() != 11) {
            return false;
        }
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit > 9) {
            firstDigit = 0;
        }
        if (Character.getNumericValue(cpf.charAt(9)) != firstDigit) {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit > 9) {
            secondDigit = 0;
        }

        if (Character.getNumericValue(cpf.charAt(10)) != secondDigit) {
            return false;
        }

        return true;
    }
    private void consultarProtocolo(ProtocoloController controller) {
        System.out.println("Consultar protocolo:");
        int numeroProtocolo = solicitarNumeroProtocolo(scanner);
        controller.consultarProtocolo(numeroProtocolo);
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("Número do protocolo: ");
            if (scanner.hasNextInt()) {
                numeroProtocolo = scanner.nextInt();
                if (numeroProtocolo > 0) {
                    inputValido = true;
                } else {
                    System.out.println("O número do protocolo deve ser um valor inteiro positivo.");
                }
            } else {
                System.out.println("Por favor, insira um número válido para o protocolo.");
                scanner.next();
            }
        }
        scanner.nextLine();


        controller.consultarProtocolo(numeroProtocolo);
    }

    private void deletarProtocolo(ProtocoloController controller) {
        System.out.println("Deletar protocolo:");
        int numeroProtocolo = 0;
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("Número do protocolo: ");
            if (scanner.hasNextInt()) {
                numeroProtocolo = scanner.nextInt();
                if (numeroProtocolo > 0) {
                    inputValido = true;
                } else {
                    System.out.println("O número do protocolo deve ser um valor inteiro positivo.");
                }
            } else {
                System.out.println("Por favor, insira um número válido para o protocolo.");
                scanner.next();
            }
        }
        scanner.nextLine();


        controller.deletarProtocolo(numeroProtocolo);
    }
    private void editarProtocolo(ProtocoloController controller) {
        System.out.println("Editar protocolo:");


        int numeroProtocolo = solicitarNumeroProtocolo(scanner);


        System.out.print("Novo nome do cliente: ");
        String nomeCliente = scanner.nextLine().trim();
        while (nomeCliente.isEmpty()) {
            System.out.println("O nome do cliente não pode estar vazio.");
            System.out.print("Novo nome do cliente: ");
            nomeCliente = scanner.nextLine().trim();
        }

        System.out.print("Novo telefone do cliente: ");
        String telefoneCliente = scanner.nextLine().trim();
        while (telefoneCliente.isEmpty()) {
            System.out.println("O telefone do cliente não pode estar vazio.");
            System.out.print("Novo telefone do cliente: ");
            telefoneCliente = scanner.nextLine().trim();
        }


        Date dataAbertura = solicitarDataAbertura(scanner);


        System.out.print("Nova descrição do protocolo: ");
        String descricao = scanner.nextLine().trim();
        while (descricao.isEmpty()) {
            System.out.println("A descrição do protocolo não pode estar vazia.");
            System.out.print("Nova descrição do protocolo: ");
            descricao = scanner.nextLine().trim();
        }


        TipoProtocolo tipoProtocolo = solicitarTipoProtocolo(scanner);


        ProtocoloModel protocolo = new ProtocoloModel(numeroProtocolo, new Cliente(nomeCliente, "", telefoneCliente), dataAbertura, descricao, tipoProtocolo);


        controller.editarProtocolo(protocolo);
    }


    private int solicitarNumeroProtocolo(Scanner scanner) {
        System.out.print("Número do protocolo: ");
        while (true) {
            if (scanner.hasNextInt()) {
                int numeroProtocolo = scanner.nextInt();
                scanner.nextLine();
                if (numeroProtocolo > 0) {
                    return numeroProtocolo;
                } else {
                    System.out.println("O número do protocolo deve ser um valor inteiro positivo.");
                }
            } else {
                System.out.println("Por favor, insira um número válido para o protocolo.");
                scanner.next();
            }
        }
    }

    private Date solicitarDataAbertura(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Data de abertura do protocolo (formato dd/MM/yyyy): ");
        while (true) {
            try {
                String dataInput = scanner.nextLine().trim();
                Date dataAbertura = sdf.parse(dataInput);
                return dataAbertura;
            } catch (ParseException e) {
                System.out.println("Data inserida inválida. Por favor, insira no formato dd/MM/yyyy.");
                System.out.print("Data de abertura do protocolo (formato dd/MM/yyyy): ");
            }
        }
    }
    private TipoProtocolo solicitarTipoProtocolo(Scanner scanner) {
        while (true) {
            System.out.print("Tipo do protocolo (reclamação, consulta, elogio): ");
            String tipoInput = ProtocoloUtil.scanner.nextLine().trim().toUpperCase();
            try {
                return TipoProtocolo.valueOf(tipoInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de protocolo inserido inválido. Insira reclamação, consulta ou elogio.");
            }
        }
    }
}

