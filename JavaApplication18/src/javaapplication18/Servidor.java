package javaapplication18;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Servidor{
    
    ServerSocket serverSocket = new ServerSocket(80);
    Socket clientSocket = null;
    DataOutputStream out = null;
    BufferedReader in = null;

    public Servidor() throws IOException {
        //JOptionPane.showMessageDialog(null, "IP do Servidor: " + getIp());
       
    }
    
    public void fechaSocket() throws IOException{
        this.serverSocket.close();
    }
    
    public void enviarMsg(String msg) throws IOException{
        out.writeBytes(msg); // send the response to client
        out.flush();
        out.close();
    }    
    
    
    public Socket getSocket(){
        return this.clientSocket;
    }
    
    public String receberMsg() throws IOException{
        
        this.clientSocket = serverSocket.accept();
        this.out = new DataOutputStream(clientSocket.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        String textFromClient;
        textFromClient = in.readLine(); // read the text from client
        return textFromClient;
    }

//    public ArrayList<String> receberMsg() throws IOException{
//       
//        ArrayList<String> textFromClient = new ArrayList<String>();
//        String linha;
//        linha = in.readLine();
//        while (linha != null){
//            textFromClient.add(linha);
//            linha = in.readLine();
//        }
//        return textFromClient;
//    }
    
    public void fechaConexao() throws IOException{
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    } 
    
    public String getIp() throws UnknownHostException{
        return InetAddress.getLocalHost().getHostAddress();
    }
    
}