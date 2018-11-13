/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesimples2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author daniel
 */
public class ClienteSimples2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ConnectException {
        int pos = 110;
//        Integer i = 0;
        String pre = "192.168.1.105";

        JSONObject obj = new JSONObject();
//        obj.append("parametro1", 1);
//        obj.append("parametro2", 2);
        obj.put("parametro1", 4);
        obj.put("parametro2", 2);
        
        System.out.println("obj="+obj);
        String jMessage = obj.toString();

        
        String ip = pre;
        new Thread() {
            public void run() {
                try {
                    System.out.println("Thread: " + getName() + " criando socket no IP:" + ip);

                    Socket s = new Socket(ip, 4050);

                    PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

                    pw.println(jMessage);

                    String answer = br.readLine();
                    System.out.println("[ip:" + ip + "] >> " + answer);
                    
                    pw.close();
                    br.close();
                    
                    System.exit(0);

                } catch (ConnectException ce) {
                    System.err.println("Falha na geração do socket.");
                } catch (IOException ex) {
                    Logger.getLogger(ClienteSimples2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception e) {
                    System.out.println("Opa: erro na criação do socket cliente.");
                } finally {
                    System.out.println(getName() + ": cliente (ciclo) finalizado.");
                }
            }
        }
                .start();
        //}

    }
}
