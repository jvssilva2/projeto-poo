import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Geradorcsv {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita o nome das colunas
        System.out.println("Digite o nome da primeira coluna: ");
        String column1 = scanner.nextLine();

        System.out.println("Digite o nome da segunda coluna: ");
        String column2 = scanner.nextLine();

        System.out.println("Digite o nome da terceira coluna: ");
        String column3 = scanner.nextLine();

      

        // Criação do arquivo CSV
        try (FileWriter fileWriter = new FileWriter("primeiroprojeto.csv")) {
            // Escrevendo o cabeçalho
            fileWriter.append(column1)
                      .append(',')
                      .append(column2)
                      .append(',')
                      .append(column3)
                      .append('\n');

            // Coletando e escrevendo dados
            boolean continueInput = true;
            int lineCount = 0;

            while (continueInput) {
                System.out.println("Digite os dados para a linha " + (lineCount + 1) + ":");
                
                System.out.print(column1 + ": ");
                String data1 = scanner.nextLine();

                System.out.print(column2 + ": ");
                String data2 = scanner.nextLine();

                System.out.print(column3 + ": ");
                String data3 = scanner.nextLine();

                // Escrevendo dados no arquivo
                fileWriter.append(data1)
                          .append(',')
                          .append(data2)
                          .append(',')
                          .append(data3)
                          .append('\n');
                
                lineCount++;

                // Pergunta se o usuário deseja adicionar mais dados
                System.out.println("Deseja adicionar outra linha? (s/n): ");
                String response = scanner.nextLine();

                if (response.equalsIgnoreCase("n")) {
                    continueInput = false;
                }
            }

            System.out.println("Arquivo CSV criado com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo CSV: " + e.getMessage());
        }

        scanner.close();
    }
}


