package servidorsimples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;

/**
 *
 * @author daniel
 */
public class ServidorSimples {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        try {
            while (true) {
                ServerSocket listener = new ServerSocket(4040);
                System.out.println("Aguardando conexão...");

                Socket s = listener.accept();
                System.out.println("Conexão aceita!");
                System.out.println("");

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);

                try {
                    String answer = br.readLine();
                    System.out.println("Mensagem Cliente:" + answer);
                    
                    JSONObject jo = new JSONObject(answer);
                    
                    String v1 = jo.optString("parametro1");
                    String v2 = jo.optString("parametro2");
                    
//                  CONVERSÃO DE STRING PARA NUMERO
//                    int v1_val = Integer.parseInt(v1.substring(1, (v1.length()-1)));
//                    int v2_val = Integer.parseInt(v2.substring(1, (v2.length()-1)));
                    int v1_val = Integer.parseInt(v1);
                    int v2_val = Integer.parseInt(v2);
          
                    int res = (v1_val+v2_val);
                    
                    JSONObject jres = new JSONObject();
                    jres.put("resultado", res);
                    
//                  SOMA
//                    System.out.println("valores:"+v1+", "+v2+"|| SOMA → "+res);
                    System.out.println(jres);
//                    out.println("Equação: "+v1+"+"+v2+"="+res);
                    out.println(jres);
                    br.close();
                } catch (ConnectException cone) {
                    System.out.println("Falha na conexão.");
                } finally {

                    System.out.println("Finalizando conexão.");
                    System.out.println("****************************************");
                    s.close();
                    listener.close();
                }
            }
        } catch (BindException be) {
            System.out.println("Falha no bind: porta já está sendo utilizada.");
        }
    }
}
