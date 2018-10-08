package servidorsimples;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author daniel
 */
public class ServidorSimples {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        try {
            while (true) {
                ServerSocket listener = new ServerSocket(5000);
                System.out.println("Aguardando conexão...");
                Socket socket = listener.accept();
                System.out.println("Conexão aceita!");
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Servidor: Daniel.");
                    out.println("InetAddress:" + socket.getInetAddress());
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
