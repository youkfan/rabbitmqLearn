package com.shangkk.rabbitmq.exchange.topic;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.shangkk.rabbitmq.common.MQUtils;


public class Product {
	public static <V> void main(String[] args) throws Exception {
		Connection connection = MQUtils.getConnection();
		Channel channel = connection.createChannel();
		String exchange_name="exchange_test3";
		channel.exchangeDeclare(exchange_name,"topic", false);
		System.out.println("start send message,please write 'exit' to exit");
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String message = scanner.nextLine();
			if(message.equals("exit")){
				break;
			}
			if("kang".equals(message)){
				channel.basicPublish(exchange_name, "my.name.is.shangkang", null, message.getBytes("utf-8"));
			}else{
				channel.basicPublish(exchange_name, "i.have.a.son", null, message.getBytes("utf-8"));
			}
		}
		channel.close();
		connection.close();
	}
}
