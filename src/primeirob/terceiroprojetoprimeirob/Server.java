package primeirob.terceiroprojetoprimeirob;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	public static void main(String[] args) {
		
		ServerSocket servidor = null;
		try {
			System.out.println("Inicializando o servidor");
			servidor = new ServerSocket(8080);
			System.out.println("O servidor foi inicializado");
			
			while(true){
				Socket cliente = servidor.accept();
				new GerirClientes(cliente);
			}
			
		} catch (IOException e) {
			
			try {
				if(servidor != null)
					servidor.close();
			} catch (IOException e1) {}
			
			System.err.println("a porta está ocupada ou servidor foi fechado");
			e.printStackTrace();
		}
		
	}
}