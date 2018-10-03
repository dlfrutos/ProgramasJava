/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication18;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aldir
 */
public class ResponderData implements Runnable{

    private Socket socket = null;

    public ResponderData(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        Date d = new Date();
        String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
        
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
