package com.shangkk.rabbitmq.exchange.direct;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.shangkk.rabbitmq.common.MQUtils;


public class Product {
	public static <V> void main(String[] args) throws Exception {
		Connection connection = MQUtils.getConnection();
		Channel channel = connection.createChannel();
		String exchange_name="exchange_test2";
		channel.exchangeDeclare(exchange_name,"direct", false);
		System.out.println("start send message,please write 'exit' to exit");
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String message = scanner.nextLine();
			if(message.equals("exit")){
				break;
			}
			//绑定 routingKey
			if("kang".equals(message)){
				channel.basicPublish(exchange_name, "go-home", null, message.getBytes("utf-8"));
			}else{
				channel.basicPublish(exchange_name, "go-school", null, message.getBytes("utf-8"));
			}
		}
		channel.close();
		connection.close();
	}
}
