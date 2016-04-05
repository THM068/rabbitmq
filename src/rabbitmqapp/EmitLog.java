/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitmqapp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;

/**
 *
 * @author tm1c14
 */
public class EmitLog {
    
    private static final String EXCHANGE_TYPE="fanout";
    
    public static void main(String ...args) throws Exception  {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(Cons.RABBIT_MQ_URL);
        factory.setConnectionTimeout(3000);
        factory.setRequestedHeartbeat(30);
        
        //Create a connection
        Connection connection = factory.newConnection();
        //create a channel from connection
        Channel channel = connection.createChannel();
        
        channel.exchangeDeclare(Cons.EXCHANGE_NAME, EXCHANGE_TYPE);
        String message = "Hello Dolly";
        
        channel.basicPublish(Cons.EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
