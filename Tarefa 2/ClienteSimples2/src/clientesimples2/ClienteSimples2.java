/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesimples2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class ClienteSimples2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int pos = 10;
        Integer i = 0;
        String pre = "192.168.1.";

        for (i = 1; i < 200; i++) {
            String ip = pre + String.valueOf(pos+i);
            new Thread("" + i) {
                public void run() {
                    try {
                        System.out.println("Thread: " + getName() + " criando socket no IP:" + ip);
                        Socket s = new Socket(ip, 5000);
                        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        String answer = input.readLine();
                        System.out.println("Thread("+getName()+") [ip:"+ip+"] >> "+answer);
                        System.exit(0);
                        s.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ClienteSimples2.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception e) {
                        System.out.println("Opa: erro na criação do socket cliente.");
                    } finally {
                        System.out.println(getName() + ": cliente (ciclo) finalizado.");
                    }
                }
            }.start();
        }

    }
}
