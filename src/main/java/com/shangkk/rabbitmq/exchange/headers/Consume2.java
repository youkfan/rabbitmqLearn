package com.shangkk.rabbitmq.exchange.headers;

import java.io.IOException;
import java.util.HashMap;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.shangkk.rabbitmq.common.MQUtils;

public class Consume2 {
	public static <V> void main(String[] args)throws Exception {
		Connection connection = MQUtils.getConnection();
		Channel channel = connection.createChannel();
		String exchange_name="exchange_test4";
		String queue_name="queue_test2";
		channel.exchangeDeclare(exchange_name, "headers", false);
		channel.queueDeclare(queue_name, false, false, false, null);
		HashMap<String, Object> headerMap = new HashMap<>();
		// 匹配规则   any任意和all全部   
		headerMap.put("x-match", "any");
		headerMap.put("name", "shangkk");
		headerMap.put("age", 269);
		channel.queueBind(queue_name, exchange_name, "",headerMap);
		System.out.println("consume2 receive message");
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
