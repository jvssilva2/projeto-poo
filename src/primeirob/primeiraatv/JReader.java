package primeirob.primeiraatv;

import java.io.FileWriter;
import java.io.IOException;


public class JReader {
    public static void main(String[] args) {
    
        try (FileWriter writer = new FileWriter("exemplo_bala.txt")) {
            writer.write("Pega!😎\n");
            writer.write("Bem vindo a leitura e escrita com Java☕");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
