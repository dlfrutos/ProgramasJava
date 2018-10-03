/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication18;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aldir
 */
public class ResponderSorteio implements Runnable{

    private Socket socket = null;

    public ResponderSorteio(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        Random gerador = new Random();
        String dStr = "<h1> "+gerador.nextInt()+" </h1>";
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeBytes(dStr); // send the response to client
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ResponderData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
    
