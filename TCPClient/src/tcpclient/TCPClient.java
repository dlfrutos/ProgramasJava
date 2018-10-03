/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Daniel
 */
public class TCPClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        int i;
        for(i=0; i<256;i++){
            String ip = "10.141.114."+ String.valueOf(i);
            
            System.out.println("Testando IP:"+ip);    
            try (Socket clientSocket = new Socket(ip, 5000)) {
                    BufferedReader messagesFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String clientSentence = messagesFromClient.readLine();
                    System.out.println("Received " + clientSentence);
                
            }catch(Exception e){}
            
    }    }
}
