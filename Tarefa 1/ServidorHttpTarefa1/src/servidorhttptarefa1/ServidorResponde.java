/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorhttptarefa1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class ServidorResponde implements Runnable {
    ServerSocket server;
    Socket cliente;
//    PrintWriter out;
//    BufferedReader in;
    
    public ServidorResponde() throws IOException{
        this.server = new ServerSocket(80);
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Esperando aceitar o cliente...");
                this.cliente = server.accept();
                String headerData = getHeaderToArray(this.cliente.getInputStream());

                if (headerData.contains("/data")) {
                    retornaData(this.cliente);
                } else if (headerData.contains("/sorteio")) {
                    retornaNumRandomico(this.cliente);
                } else {  //Se o usuário inserir um URL que não foi tratado
                    retorna404(this.cliente);
                }
                cliente.close();
            } catch (IOException ex) {
                System.out.println("Deu merda.");
                Logger.getLogger(ServidorResponde.class.getName()).log(Level.SEVERE, null, ex);
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
