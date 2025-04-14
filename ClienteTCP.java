import java.io.*;
import java.net.*;

public class ClienteTCP {
  public static void main(String[] args) {
    String servidor = "localhost";
    int porta = 12345;
    String caminhoArquivo = "assets/arquivoPequeno.txt";

    try (Socket socket = new Socket(servidor, porta)) {
      System.out.println("Conectado ao servidor TCP.");

      OutputStream saida = socket.getOutputStream();
      BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(saida));
      BufferedReader leitorArquivo = new BufferedReader(new FileReader(caminhoArquivo));

      String linha;
      while ((linha = leitorArquivo.readLine()) != null) {
        escritor.write(linha);
        escritor.newLine();
      }

      System.out.println("Arquivo enviado com sucesso.");
      leitorArquivo.close();
      escritor.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
