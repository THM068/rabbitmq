/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitmqapp;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author thandomafela
 */
public class RabbitMQApp {

    public static final String QUENAME = "hello_world";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        String uri = "amqp://ytsoedex:Qu2LCiBJ5x9fhRUyLYkMhJqsURJ9dkSP@chicken.rmq.cloudamqp.com/ytsoedex";
        factory.setUri(uri);
        
        //Recommended settings
        factory.setRequestedHeartbeat(30);
        factory.setConnectionTimeout(30000);
    
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        channel.queueDeclare(QUENAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUENAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        
        channel.close();
        connection.close();
    
    }
    
}
