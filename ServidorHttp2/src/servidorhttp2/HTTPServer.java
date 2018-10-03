// AQUI FUNCIONA O HEADER
package servidorhttp2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Morris on 08/10/16.
 */
public class HTTPServer {
    private static String headerData;
    
    public static void main(String[] args) {
        new HTTPServer().startServer();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(80)) {
            boolean isClosed = false;
            System.out.println("Server is started");

            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    try (InputStream raw = socket.getInputStream()) { 
                        System.out.println("=================BEFORE STARTING READING HEADER =======================");
                        System.out.println("Collecting data to string array...");
                        headerData = getHeaderToArray(raw);
                        System.out.println("+++++++++++++++++ AFTER ENDING READING HEADER +++++++++++++++++++++++");
                    }
                    
                    
                } catch (MalformedURLException ex) {
                    System.err.println(socket.getLocalAddress() + " is not a parseable URL");

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println("error# " + ex.getMessage());
        }
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
        headerData = headerTempData;
        return headerTempData;
    }
}
