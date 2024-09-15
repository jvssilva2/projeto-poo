package primeirob.segundoprojetoprimeirob;

public class NumerosRomanos {

    public static String intToRoman(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Número fora do intervalo permitido (1-3999)");
        }

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        String roman = "";

        roman += thousands[number / 1000];
        roman += hundreds[(number % 1000) / 100];
        roman += tens[(number % 100) / 10];
        roman += units[number % 10];

        return roman;
    }
    
    public static void main(String[] args) {
        // Teste a conversão
        int number = 1994;
        System.out.println("Número: " + number + " em romano é " + intToRoman(number));
    }
}

