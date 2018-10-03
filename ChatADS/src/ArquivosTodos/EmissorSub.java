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
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author hook
 */
public class EmissorSub {

    private static String USER = "guest";
    private static String PASSWD = "guest";
    private static String HOST = "localhost";
    private Connection connection = null;
    private Channel channel = null;
//

    public EmissorSub() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USER);
        factory.setPassword(PASSWD);
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public void fecharConexao() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
    public void enviaMen(String fila, String m) throws UnsupportedEncodingException, IOException {
        channel.exchangeDeclare(fila, BuiltinExchangeType.FANOUT);
        channel.basicPublish(fila, "", null, m.getBytes("UTF-8"));

    }

}
