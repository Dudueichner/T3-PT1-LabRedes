import java.io.*;
import java.net.*;

public class ClienteUDP {
  public static void main(String[] args) {
    String servidor = "localhost";
    int porta = 12346;
    String caminhoArquivo = "assets/arquivoPequeno.txt";

    try (DatagramSocket socket = new DatagramSocket()) {
      BufferedReader leitorArquivo = new BufferedReader(new FileReader(caminhoArquivo));

      String linha;
      while ((linha = leitorArquivo.readLine()) != null) {
        byte[] dados = linha.getBytes();
        DatagramPacket pacote = new DatagramPacket(dados, dados.length, InetAddress.getByName(servidor), porta);
        socket.send(pacote);
      }

      // Enviar mensagem de fim
      byte[] fim = "EOF".getBytes();
      DatagramPacket pacoteFim = new DatagramPacket(fim, fim.length, InetAddress.getByName(servidor), porta);
      socket.send(pacoteFim);

      System.out.println("Arquivo enviado via UDP.");
      leitorArquivo.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
