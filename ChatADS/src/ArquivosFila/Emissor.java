/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArquivosFila;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

public class Emissor {

    private static String USER = "guest";
    private static String PASSWD = "guest";
    private static String HOST = "localhost";
    private Connection connection = null;
    private Channel channel = null;
//
    
    public Emissor() throws IOException, TimeoutException{    
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USER);
        factory.setPassword(PASSWD);       
        connection = factory.newConnection();
        channel = connection.createChannel();
    }
    
    public void fechaConn() throws IOException, TimeoutException{
        channel.close();
        connection.close();
    }

    public void enviaMsg(String destinatario, String m) throws UnsupportedEncodingException, IOException{
        //System.out.println("enviado para: "+destinatario+"\n Mensagem:"+m);
        channel.queueDeclare(destinatario, false, false, false, null);
        channel.basicPublish("", destinatario.toLowerCase(), null, m.getBytes("UTF-8"));
        //System.out.println("enviado para: "+destinatario+"\n Mensagem:"+m);
    }
    
   
}

    

