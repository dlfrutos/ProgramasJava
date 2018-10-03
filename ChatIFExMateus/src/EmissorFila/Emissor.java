/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmissorFila;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

public class Emissor {

    private static String USER = "mqadmin";
    private static String PASSWD = "mqadmin";
    private static String HOST = "192.168.1.41";
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
    
    public void fecharConexao() throws IOException, TimeoutException{
        channel.close();
        connection.close();
    }

    public void enviarMensagem(String fila, String message) throws UnsupportedEncodingException, IOException{
        System.out.println("enviado para: "+fila+"\n Mensagem:"+message);
    
        channel.queueDeclare(fila, false, false, false, null);
        channel.basicPublish("", fila.toLowerCase(), null, message.getBytes("UTF-8"));
        System.out.println("enviado para: "+fila+"\n Mensagem:"+message);
    }
    
   
}

    

