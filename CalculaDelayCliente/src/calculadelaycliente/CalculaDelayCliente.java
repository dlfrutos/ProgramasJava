/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadelaycliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class CalculaDelayCliente {

    static Long t1, t2, delay_cliente, delay_servidor, delay_retorno;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ip = "localhost";
        t1 = System.currentTimeMillis();

        try {
            Socket s = new Socket(ip, 5000);
            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String answer = input.readLine();

            t2 = System.currentTimeMillis();
            delay_servidor = Long.parseLong(answer);
            delay_cliente = t2 - t1;
            delay_retorno = (delay_cliente + delay_servidor) / 2;
            
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println(String.valueOf(delay_retorno));
            
            
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(CalculaDelayCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("Opa: erro na criação do socket cliente.");
        } finally {
            System.out.println("t1: " + t1);
            System.out.println("t2: " + t2);

            System.out.println("\nDelay_Cliente(t2-t1): " + delay_cliente);
            System.out.println("Delay_Servidor(t4-t3): " + delay_servidor);
            System.out.println("Delay_Total(delay c+s): " + (delay_servidor + delay_cliente));

            System.out.println("\nDelay_Retorno: " + delay_retorno);

            System.out.println("\n--ciclo finalizado--");
        }
    }
}
