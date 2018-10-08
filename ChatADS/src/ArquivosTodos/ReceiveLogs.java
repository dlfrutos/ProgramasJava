package ArquivosTodos;

import Janela.Chat;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiveLogs {

    private static final String EXCHANGE_NAME = "amq";
    private final Chat chat;
    Channel channel;
    Connection connection;
    ConnectionFactory factory;
    String queueName;

    public ReceiveLogs(Chat chat) throws IOException, TimeoutException {
        this.chat = chat;
        this.factory = new ConnectionFactory();
        factory.setHost("localhost");
        this.connection = factory.newConnection();
        this.channel = connection.createChannel();

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    }

    //  public static void main(String[] argv) throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
//        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName, EXCHANGE_NAME, "");
//
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    
    public void recebeMenLog(String emissor) throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
//}
