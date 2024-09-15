package primeirob.segundoprojetoprimeirob;

import java.util.HashMap;
import java.util.Map;

public class ConversorMoedas {

    private static final Map<String, Double> rates = new HashMap<>();

    static {
        // Taxas de câmbio em relação ao USD
        rates.put("USD", 1.0);
        rates.put("EUR", 0.85);
        rates.put("JPY", 110.0);
        rates.put("GBP", 0.75);
        rates.put("BRL", 5.25);
    }

    public static double convert(double amount, String fromCurrency, String toCurrency) {
        if (!rates.containsKey(fromCurrency) || !rates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Moeda não suportada");
        }
        double fromRate = rates.get(fromCurrency);
        double toRate = rates.get(toCurrency);
        return amount / fromRate * toRate;
    }

    public static void main(String[] args) {
        // Teste a conversão de moeda
        double amount = 100; // USD
        System.out.println(amount + " USD em EUR é " + convert(amount, "USD", "EUR"));
        System.out.println(amount + " USD em JPY é " + convert(amount, "USD", "JPY"));
    }
}

