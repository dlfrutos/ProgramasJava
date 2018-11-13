/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArquivosFila;

/**
 *
 * @author root
 */
import Janela.Chat;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hook
 */
public class Receptor {
    
    private static String USER = "guest";
    private static String PASSWD = "guest";
    private static String HOST = "localhost";
    private Connection connection = null;
    private Channel channel = null;
    private Chat chat = null;
    
    public Receptor(Chat chat){
        this.chat = chat;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USER);
        factory.setPassword(PASSWD);       
        try {
            connection = factory.newConnection();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (TimeoutException ex) {
            ex.printStackTrace();
        }
        try {
            channel = connection.createChannel();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            recebeMsg("frutos");
        } catch (IOException ex) {
            Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fecharConn() throws IOException, TimeoutException{
        channel.close();
        connection.close();
    }
    
    public void recebeMsg(String fila) throws IOException{
        
        channel.queueDeclare(fila, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                chat.inserirLinhaReceptor(message);
            }
        };
        channel.basicConsume(fila, true, consumer);
    }
    
    
    
}
