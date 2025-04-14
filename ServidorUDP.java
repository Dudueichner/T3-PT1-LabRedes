import java.io.*;
import java.net.*;

public class ServidorUDP {
  public static void main(String[] args) {
    int porta = 12346;
    byte[] bufferRecebimento = new byte[1024];

    try (DatagramSocket socket = new DatagramSocket(porta)) {
      System.out.println("Servidor UDP escutando na porta " + porta);
      BufferedWriter escritor = new BufferedWriter(new FileWriter("recebido_udp.txt"));

      while (true) {
        DatagramPacket pacoteRecebido = new DatagramPacket(bufferRecebimento, bufferRecebimento.length);
        socket.receive(pacoteRecebido);

        String mensagem = new String(pacoteRecebido.getData(), 0, pacoteRecebido.getLength());

        if (mensagem.equals("EOF")) {
          System.out.println("Transmissão finalizada.");
          break;
        }

        escritor.write(mensagem);
        escritor.newLine();
      }

      escritor.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
