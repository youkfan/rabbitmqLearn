package com.shangkk.rabbitmq.exchange.none;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.shangkk.rabbitmq.common.MQUtils;

public class Consume {
	public static <V> void main(String[] args)throws Exception {
		Connection connection = MQUtils.getConnection();
		Channel channel = connection.createChannel();
		String queue_name="queue_test1";
		channel.queueDeclare(queue_name, false, false, false, null);
		System.out.println("consume1 receive message");
		DefaultConsumer defaultConsumer = new DefaultConsumer(channel){

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message= new String(body,"utf-8");
				System.out.println(message);
			}
			
		};
		channel.basicConsume(queue_name, true,defaultConsumer);
	}
}
