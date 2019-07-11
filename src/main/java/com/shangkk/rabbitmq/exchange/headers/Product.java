package com.shangkk.rabbitmq.exchange.headers;

import java.util.HashMap;
import java.util.Scanner;

import com.rabbitmq.client.AMQP.BasicProperties.Builder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.shangkk.rabbitmq.common.MQUtils;


public class Product {
	public static <V> void main(String[] args) throws Exception {
		Connection connection = MQUtils.getConnection();
		Channel channel = connection.createChannel();
		String exchange_name="exchange_test4";
		channel.exchangeDeclare(exchange_name,"headers", false);
		System.out.println("start send message,please write 'exit' to exit");
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String message = scanner.nextLine();
			if(message.equals("exit")){
				break;
			}
			//指定请求头信息
			HashMap<String, Object> headerMap = new HashMap<>();
			headerMap.put("name", "shangkk");
			headerMap.put("age", 26);
			Builder builder = new Builder();
			Builder headers = builder.headers(headerMap);
			channel.basicPublish(exchange_name, "", headers.build(), message.getBytes("utf-8"));
		}
		channel.close();
		connection.close();
	}
}
