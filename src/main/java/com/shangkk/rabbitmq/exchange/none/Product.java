package com.shangkk.rabbitmq.exchange.none;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.shangkk.rabbitmq.common.MQUtils;


public class Product {
	public static <V> void main(String[] args) throws Exception {
		Connection connection = MQUtils.getConnection();
		Channel channel = connection.createChannel();
		String queueName="queue_test1";
		//声明队列
		channel.queueDeclare(queueName, false, false, false, null);
		System.out.println("start send message,please write 'exit' to exit");
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String message = scanner.nextLine();
			if(message.equals("exit")){
				break;
			}
			//发送消息到队列中
			channel.basicPublish("", queueName, null, message.getBytes("utf-8"));
		}
		channel.close();
		connection.close();
	}
}
