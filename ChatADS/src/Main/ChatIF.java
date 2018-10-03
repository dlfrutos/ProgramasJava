/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Janela.Chat;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author root
 */
public class ChatIF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        Chat chat = null;   
        chat = new Chat();
        chat.setVisible(true);
    }
    
}
