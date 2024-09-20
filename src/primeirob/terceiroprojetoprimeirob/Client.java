package primeirob.terceiroprojetoprimeirob;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try {
            final Socket cliente = new Socket("127.0.0.1", 8080); 

            
            new Thread(() -> {
                try {
                    BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    String mensagem;
                    while (true) {
                        mensagem = leitor.readLine();
                        if (mensagem != null && !mensagem.isEmpty()) { 
                            System.out.println("O servidor disse: " + mensagem);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Impossível ler a mensagem do servidor");
                    e.printStackTrace();
                }
            }).start();

            
            PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
            BufferedReader leitorTerminal = new BufferedReader(new InputStreamReader(System.in));
            String mensagemTerminal;

            while (true) {
                mensagemTerminal = leitorTerminal.readLine();
                if (mensagemTerminal != null && !mensagemTerminal.isEmpty()) { // Verifica se a mensagem não é nula ou vazia
                    escritor.println(mensagemTerminal);
                    if (mensagemTerminal.equalsIgnoreCase("::SAIR")) {
                        break; 
                    }
                }
            }

            
            cliente.close();
            System.out.println("Conexão encerrada.");
            System.exit(0);

        } catch (UnknownHostException e) {
            System.out.println("O endereço passado é inválido");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("O servidor pode estar fora do ar");
            e.printStackTrace();
        }
    }
}