package primeirob.segundoprojetoprimeirob;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Escolha a funcionalidade:");
        System.out.println("1. Conversão de inteiro para romano");
        System.out.println("2. Conversão de moedas");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Digite um número inteiro (1-3999):");
                int number = scanner.nextInt();
                try {
                    String roman = NumerosRomanos.intToRoman(number);
                    System.out.println("Número romano: " + roman);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                System.out.println("Digite a moeda de origem (USD, EUR, JPY, GBP, BRL):");
                String fromCurrency = scanner.next().toUpperCase();
                System.out.println("Digite a moeda de destino (USD, EUR, JPY, GBP, BRL):");
                String toCurrency = scanner.next().toUpperCase();
                System.out.println("Digite o valor:");
                double amount = scanner.nextDouble();
                try {
                    double convertedAmount = ConversorMoedas.convert(amount, fromCurrency, toCurrency);
                    System.out.println(amount + " " + fromCurrency + " em " + toCurrency + " é " + convertedAmount);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            default:
                System.out.println("Escolha inválida.");
                break;
        }

        scanner.close();
    }
}

