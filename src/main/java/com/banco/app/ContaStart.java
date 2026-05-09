package com.banco.app;


import com.banco.config.Database;
import com.banco.service.ContaService;
import com.banco.util.InputUtil;
import com.banco.model.ContaBancaria;


import java.util.Scanner;


public class ContaStart {
    public static void main(String[] args) {

        Database.inicializar();

        ContaService service = new ContaService();
        Scanner sc = new Scanner(System.in);

        int op = 0;
        while (true) {
        System.out.println("\n");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Listar contas");
        System.out.println("3 - Buscar por id");
        System.out.println("4 - Depositar");
        System.out.println("5 - Sacar");
        System.out.println("6 - Tranferencia");
        System.out.println("7 - Excluir conta");
        System.out.println("0 - Sair");
        System.out.println("-------------------------");
        System.out.print("Opção: ");

        try{
            op = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("Erro: " + e.getMessage());
            continue;
        }
        
            if (op == 1) {
                String nome = InputUtil.lerString("Nome: ");

                double saldo = InputUtil.lerDouble("Saldo inicial: ");

                String tipo = InputUtil.lerString("Tipo (corrente/poupanca): ");

                service.criarConta(nome, saldo, tipo);
                System.out.println("Conta criada com sucesso.");
                sc.nextLine();
            }
            else if (op == 2) {
                System.out.println("Contas cadastradas:");
                for (ContaBancaria c : service.ListarContas()) {
                    System.out.println(c.getId() + " - " + c.getNome() + " | " + c.getTipo() + " | R$ " + c.getSaldo());
                }
            }
            else if(op == 3){
                int id = InputUtil.lerInt("Digite o id referente à conta que deseja buscar");

                ContaBancaria conta = service.BuscarPorId(id); 

                if (conta != null) {
                    System.out.println("Conta encontrada:");
                    System.out.println(conta);
                } else {
                    System.out.println("Nenhuma conta encontrada com esse ID.");
                }
            }

            else if(op == 4){
                int id = InputUtil.lerInt("Digite o id referente a conta que queres depositar");
                sc.nextLine();

                try{
                    ContaBancaria conta = service.BuscarPorId(id);
                    if(conta == null){
                        System.out.println("Tranferencia invalidada, a conta digitada nao existe");
                        sc.nextLine();
                        return;
                    }

                    System.out.println("Verifique se seus dados estao corretos");
                    System.out.println(conta);

                    String confirmacao = InputUtil.lerString("Confirmar transacao: (sim)S / N(nao)");

                    if(confirmacao.equalsIgnoreCase("S")){
                        double valor = InputUtil.lerDouble("Digite o valor do deposito");

                        sc.nextLine();

                        service.Depositar(id, valor);
                        System.out.println("Deposito realizado com sucesso");
                    }else{
                        System.out.println("Transacao invalidada");
                    }
                }catch(RuntimeException e){
                    System.out.println("Erro" + e.getMessage());
                }
            }

            else if(op == 5){
                int id = InputUtil.lerInt("Digite o id referente a conta que vai realizar o saque");
                sc.nextLine();

                try{
                    ContaBancaria conta = service.BuscarPorId(id);
                    if(conta == null){
                        System.out.println("Tranferencia invalida, a conta digitada nao existe");
                        sc.nextLine();
                        return;
                    }

                    System.out.println("Verifique se seus dados estao corretos");
                    System.out.println(conta);

                    String confirmacao = InputUtil.lerString("Confirmar saque (sim) S/N (nao)");

                    if(confirmacao.equalsIgnoreCase("S")){
                        double valor = InputUtil.lerDouble("Digite o valor do saque");

                        service.sacar(id, valor);
                        System.out.println("Saque realizado com sucesso");
                    }else{
                        System.out.println("Transacao invalidada");
                    }
                }catch(RuntimeException e){
                    System.out.println("Erro" + e.getMessage());
                }
            sc.nextLine();
            }
            

            else if(op == 6){
                int idTitular = InputUtil.lerInt("Digite o id da conta titular que deseja tranferir");

                try{
                    ContaBancaria conta = service.BuscarPorId(idTitular);
                    if(conta == null){
                        System.out.println("A conta digitada nao existe");
                        return;
                    }

                    sc.nextLine();
                    
                    int idDestino = InputUtil.lerInt("Digite o id da conta destino da tranferencia");

                    ContaBancaria contaDestino = service.BuscarPorId(idDestino);

                    if(contaDestino == null){
                        System.out.println("Tranferencia invalida: A conta digitada nao existe");
                        return;
                    }
                    sc.nextLine();

                    System.out.println("Conta origem: " + conta);
                    System.out.println("Conta destino: " + contaDestino);

                    String confirmacao = InputUtil.lerString("Confirmar transferencia (sim) S/N (nao)");

                    if(confirmacao.equalsIgnoreCase("S")){
                        double valor = InputUtil.lerDouble("Digite o valor da transferencia");
                        sc.nextLine();

                        service.tranferencia(idTitular, idDestino, valor);

                        System.out.println("Transferencia bem sucedida");
                    }else{
                        System.out.println("Falha na transacao");

                    }
                    
                } catch (RuntimeException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }

            else if(op == 7){
                int id = InputUtil.lerInt("Escreva o id da conta que deseja excluir");

                service.apagarConta(id);
            }

            else if (op == 0) {
                break;
            }
        }
        sc.close();
    }
}