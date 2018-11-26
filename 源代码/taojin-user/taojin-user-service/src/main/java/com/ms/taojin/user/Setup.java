package com.ms.taojin.user;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Setup {
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) throws Exception {
		System.setProperty("dubbo.application.name", "taojin-user-local");
		System.setProperty("dubbo.consumer.timeout", "80000");
		System.setProperty("dubbo.consumer.retries", "0");
		System.setProperty("dubbo.registry.address", "zookeeper://127.0.0.1:2181?register=true");
		System.setProperty("dubbo.protocol.port", "20101");
		// 加载spring容器
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext-provider.xml");
		context.start();
		System.out.println("Press any key to exit.");
		System.in.read();
	}
}
