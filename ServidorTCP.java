import java.io.*;
import java.net.*;

public class ServidorTCP {
  public static void main(String[] args) {
    int porta = 12345;

    try (ServerSocket servidorSocket = new ServerSocket(porta)) {
      System.out.println("Servidor TCP esperando conexão na porta " + porta + "...");

      Socket clienteSocket = servidorSocket.accept();
      System.out.println("Cliente conectado: " + clienteSocket.getInetAddress());

      InputStream entrada = clienteSocket.getInputStream();
      BufferedReader leitor = new BufferedReader(new InputStreamReader(entrada));
      BufferedWriter escritor = new BufferedWriter(new FileWriter("recebido_tcp.txt"));

      String linha;
      while ((linha = leitor.readLine()) != null) {
        escritor.write(linha);
        escritor.newLine();
      }

      System.out.println("Arquivo recebido e salvo como 'recebido_tcp.txt'");
      escritor.close();
      clienteSocket.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
