package primeirob.terceiroprojetoprimeirob;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class GerirClientes extends Thread {
	
	private Socket cliente;
	private String nomeCliente;
	private BufferedReader leitor;
	private PrintWriter escritor;
	private static final Map<String,GerirClientes> clientes = new HashMap<String,GerirClientes>();

	public GerirClientes(Socket cliente) {
		this.cliente = cliente;
		start();
	}
	
	
	@Override
	public void run() {
		try {
			leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			escritor = new PrintWriter(cliente.getOutputStream(), true);
			
			efetuarLogin();
			
			String msg;
			while(true){
				msg = leitor.readLine();
				if(msg.equalsIgnoreCase(ComandosExecuçao.SAIR)){
					this.cliente.close();
				}else if(msg.startsWith(ComandosExecuçao.MENSAGEM)){
					String nomeDestinario = msg.substring(ComandosExecuçao.MENSAGEM.length(), msg.length());
					System.out.println("enviando para " + nomeDestinario);
					GerirClientes destinario = clientes.get(nomeDestinario);
					if(destinario == null){
						escritor.println("O cliente informado nao existe");
					}else{
						escritor.println("digite uma mensagem para " + destinario.getNomeCliente());
						destinario.getEscritor().println(this.nomeCliente + " disse: " + leitor.readLine());
					}
					
				
				}else if(msg.equals(ComandosExecuçao.LISTA_USUARIOS)){
					atualizarListaUsuarios(this);
				}else{
					escritor.println(this.nomeCliente + ", você disse: " + msg);
				}
			}
			
		} catch (IOException e) {
			System.err.println("o cliente fechou a conexao");
			clientes.remove(this.nomeCliente);
			e.printStackTrace();
		}
	}

	private synchronized void  efetuarLogin() throws IOException {
		
		while(true){
			escritor.println(ComandosExecuçao.LOGIN);
			this.nomeCliente = leitor.readLine().toLowerCase().replaceAll(",", "");
			if(this.nomeCliente.equalsIgnoreCase("null") || this.nomeCliente.isEmpty()){
				escritor.println(ComandosExecuçao.LOGIN_NEGADO);
			}else if(clientes.containsKey(this.nomeCliente)){
				escritor.println(ComandosExecuçao.LOGIN_NEGADO);
			}else{
				escritor.println(ComandosExecuçao.LOGIN_ACEITO);
				escritor.println("olá " + this.nomeCliente);
				clientes.put(this.nomeCliente, this);
				for(String cliente: clientes.keySet()){
					atualizarListaUsuarios(clientes.get(cliente));
				}
				break;
			}
		}
	}

	private void atualizarListaUsuarios(GerirClientes cliente) {
		StringBuffer str = new StringBuffer();
		for(String c: clientes.keySet()){
			if(cliente.getNomeCliente().equals(c))
				continue;
			
			str.append(c);
			str.append(",");
		}
		if(str.length() > 0)
			str.delete(str.length()-1, str.length());
		cliente.getEscritor().println(ComandosExecuçao.LISTA_USUARIOS);
		cliente.getEscritor().println(str.toString());
	}

	public PrintWriter getEscritor() {
		return escritor;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public BufferedReader getLeitor() {
		return leitor;
	}
}