/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArquivosTodos;

/**
 *
 * @author root
 */
import Janela.Chat;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author hook
 */
public class ReceptorSub {
    private static String USER = "guest";
    private static String PASSWD = "guest";
    private static String HOST = "localhost";
    private Connection connection = null;
    private Channel channel = null;
    private Chat chat = null;
    
    public ReceptorSub(Chat chat) throws IOException, TimeoutException{
        this.chat = chat;
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
    
    public void recebeMen(String emissor) throws IOException{
       channel.exchangeDeclare(emissor, BuiltinExchangeType.FANOUT);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, emissor, "");

        //consome as mensagens
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                chat.inserirLinhaReceptor(message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
    
}
