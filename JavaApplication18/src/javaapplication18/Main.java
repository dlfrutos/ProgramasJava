/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication18;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author aldir
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        
        String retorno;
        while (true){
            Servidor server = new Servidor();
            retorno = server.receberMsg();
            if(retorno.contains("data")){
                ResponderData r = new ResponderData(server.getSocket());
                r.run();
            } else if (retorno.contains("sorteio")){
                ResponderSorteio resposta = new ResponderSorteio(server.getSocket());
                resposta.run();
            } else {
                server.enviarMsg("<h1><b> URL Invalida - Page not found - 404 </h1>");
            }
            
//            System.out.println(retorno);
//            System.out.println(retorno.contains("GET /data HTTP/1.1"));
            server.fechaSocket();
        }
    }
}
