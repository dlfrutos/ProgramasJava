/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplopublishsubs;

/**
 *
 * @author luciano
 */
import com.rabbitmq.client.*;

import java.io.IOException;

public class Receptorps {

    private static final String EXCHANGE_NAME = "logs";
    public final static String USER = "guest";
    public final static String PASSWD = "guest";
    public final static String HOST = "localhost";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USER);
        factory.setPassword(PASSWD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" Aguardando mensagens do tópico " + EXCHANGE_NAME);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" Mensagem: " + message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
