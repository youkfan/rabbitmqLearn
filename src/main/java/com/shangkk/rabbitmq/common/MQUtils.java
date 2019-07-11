package com.shangkk.rabbitmq.common;

import static com.shangkk.rabbitmq.common.Constant.host;
import static com.shangkk.rabbitmq.common.Constant.password;
import static com.shangkk.rabbitmq.common.Constant.port;
import static com.shangkk.rabbitmq.common.Constant.username;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
public class MQUtils {
	/**
	 * 创建rabbitmq连接
	 * @return
	 */
	public static Connection getConnection(){
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		try {
			return connectionFactory.newConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MQException("rabbitmq消息服务器连接异常");
		}
	}
}
