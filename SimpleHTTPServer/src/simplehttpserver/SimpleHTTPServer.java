package simplehttpserver;

/*
TAREFA 1 - O SITE QUE RESPONDE A DATA E SORTEIO.
FALTA IMPLEMENTAR THREAD, 
POSSIVELMENTE DUAS NOVAS CLASSES.
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Daniel Frutos
 */
public class SimpleHTTPServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        ServerSocket server = new ServerSocket(80);
        System.out.println("Listening for connection on port 80 ....");

        while (true) {
            try (Socket socket = server.accept()) {
                String headerData = getHeaderToArray(socket.getInputStream());

                if (headerData.contains("/data")) {
                    retornaData(socket);
                }
                else if (headerData.contains("/sorteio")) {
                    retornaNumRandomico(socket);
                } else {  //Se o usuário inserir um URL que não foi tratado
                    retorna404(socket);
                }
            }
        }
    }

    //    Funções de retorno são 3
    public static void retornaData(Socket socket) throws IOException {
        Date today = new Date();
//        String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
        String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + "Data atual: " + today;
        socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
    }

    public static void retornaNumRandomico(Socket socket) throws IOException {
        Random rand = new Random();
        int n = rand.nextInt(50) + 1;
        System.out.println("Num=" + String.valueOf(n));
        String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + "Numero Randomico:" + String.valueOf(n);
        socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
    }

    public static void retorna404(Socket socket) throws IOException {
        String mensagem = "Pagina non existe. Utilizar 'localhost/data' ou 'localhost/sorteio', obrigado.";
        String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + mensagem;
        socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
    }

    public static String getHeaderToArray(InputStream inputStream) {
        String headerTempData = "";
        // chain the InputStream to a Reader
        Reader reader = new InputStreamReader(inputStream);
        try {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
                headerTempData += (char) c;
                if (headerTempData.contains("\r\n\r\n")) {
                    break;
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        String headerData = headerTempData;
        return headerTempData;
    } //Lê o header.
}
