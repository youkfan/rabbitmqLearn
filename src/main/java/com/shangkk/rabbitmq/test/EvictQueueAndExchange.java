package com.shangkk.rabbitmq.test;

import java.io.IOException;
import java.util.Arrays;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.shangkk.rabbitmq.common.MQUtils;

/**
 * 
 * @author shangkk
 *
 * 2019年7月11日
 */
public class EvictQueueAndExchange {
	static String[] queues = {"queue_test1","queue_test2"};
	static String[] exChanges = {"exchange_test1","exchange_test2"};
	public static void main(String[] args)throws Exception {
		Connection connection = MQUtils.getConnection();
		Channel channel = connection.createChannel();
		Arrays.asList(queues).forEach(info->{
			try {
				channel.queueDelete(info);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Arrays.asList(exChanges).forEach(info->{
			try {
				channel.exchangeDelete(info);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		channel.close();
		connection.close();
	}
}
