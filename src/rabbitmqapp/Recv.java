/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitmqapp;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
/**
 *
 * @author thandomafela
 */
public class Recv {
    public static final String QUENAME = "hello_world";
    
    public static void main(String ...args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        String uri = "amqp://ytsoedex:Qu2LCiBJ5x9fhRUyLYkMhJqsURJ9dkSP@chicken.rmq.cloudamqp.com/ytsoedex";
        factory.setUri(uri);
        
        Connection connection = factory.newConnection();
       Channel channel = connection.createChannel();

        channel.queueDeclare(QUENAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        
        Consumer consumer = new DefaultConsumer(channel) {
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
        ProductOrder message = (ProductOrder)RabbitUtility.getObject(body);
        
        //String message = new String(body, "UTF-8");
        System.out.println(" [x] Received name'" + message.getName() + "'");
        System.out.println(" [x] Received price'" + message.getPrice() + "'");
      }
    };
    channel.basicConsume(QUENAME, true, consumer);
    }
    
}
