package com.ms.center;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Setup {
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) throws Exception {
		System.setProperty("dubbo.application.name", "center-demo");
		System.setProperty("dubbo.consumer.timeout", "80000");
		System.setProperty("dubbo.consumer.retries", "0");
		System.setProperty("dubbo.registry.address", "zookeeper://10.2.15.82:2181?register=false");
		System.setProperty("dubbo.protocol.port", "20105");
		// 加载spring容器
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext-provider.xml");
		context.start();
		System.out.println("Press any key to exit.");
		System.in.read();
	}
}
