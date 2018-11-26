package com.ms.taojin.common.threadPool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyExecuteThread implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(ProxyExecuteThread.class);

	private Object obj;
	private String methodName;
	private Object[] args;

	public ProxyExecuteThread(Object obj, String methodName, Object... args) {
		super();
		this.obj = obj;
		this.methodName = methodName;
		this.args = args;
	}

	@Override
	public void run() {
		Method method = null;
		try {
			if (args == null || args.length == 0) {
				method = obj.getClass().getMethod(methodName);
			} else {
				Class<?>[] classTypes = new Class<?>[args.length];
				for (int i = 0; i < args.length; i++) {
					classTypes[i] = args[i].getClass();
				}
				method = obj.getClass().getMethod(methodName, classTypes);
			}
			method.invoke(obj, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

	}

	/**
	 * 在service中根据名字获取方法反射
	 * 
	 * @param service
	 * @param methodName
	 * @return
	 */
	public static Method getMethod(Object service, String methodName) {
		Method[] methods = service.getClass().getMethods();
		for (Method m : methods) {
			if (methodName.equals(m.getName())) {
				return m;
			}
		}
		logger.error("{}中找不到方法{}", service.getClass(), methodName);
		return null;
	}

}
