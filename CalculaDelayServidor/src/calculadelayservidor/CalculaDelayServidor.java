package calculadelayservidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author danie
 */
public class CalculaDelayServidor {

    static long t3, t4, delay_servidor, delay_retorno;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            while (true) {
                ServerSocket listener = new ServerSocket(5000);
                System.out.println("Aguardando conexão...");
                Socket socket = listener.accept();
                System.out.println("Conexão aceita!");

                t3 = System.currentTimeMillis();

                try {
                    Thread.sleep(500);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    t4 = System.currentTimeMillis();
                    delay_servidor = t4 - t3;
                    System.out.println("t3: " + t3);
                    System.out.println("t4: " + t4);
                    System.out.println("delay_servidor: " + delay_servidor);
                    out.println(String.valueOf(delay_servidor));
                    
                    delay_retorno = Long.parseLong(in.readLine());
                    System.out.println("delay_retorno: "+delay_retorno);

                } catch (ConnectException cone) {
                    System.out.println("Falha na conexão.");
                } finally {
                    System.out.println("Finalizando conexão.");
                    socket.close();
                    listener.close();
                }
            }
        } catch (BindException be) {
            System.out.println("Falha no bind: porta já está sendo utilizada.");
        }
    }
}
