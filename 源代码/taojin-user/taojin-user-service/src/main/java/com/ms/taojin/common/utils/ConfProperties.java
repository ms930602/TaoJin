package com.ms.taojin.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ConfProperties {

	/** 属性文件路径 */
	private final static String fileName = "conf.properties";
	/** 属性集 */
	private static Properties props = new Properties();
	/** 外放对象 */
	private static ConfProperties propertys;

	public static String TODAY_COUNT_USER_LOGINS_FLAG = ConfProperties.getInstans().getValue("today.count.user.logins.flag");

	/** 构造私有 */
	private ConfProperties() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			props.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 公共方法获取实例 */
	public static ConfProperties getInstans() {
		synchronized (props) {
			if (propertys == null) {
				propertys = new ConfProperties();
			}
		}
		return propertys;
	}

	/**
	 * 获取字符串对应值
	 * 
	 * @throws IOException
	 */
	public String getValue(String key) {
		// 取出相关值
		try {
			return props.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Map<String, String> getAllProps() {
		Map<String, String> allProp = new HashMap<String, String>();
		Set<String> propSet = props.stringPropertyNames();
		Iterator<String> iprop = propSet.iterator();// 先迭代出来
		while (iprop.hasNext()) {// 遍历
			String key = iprop.next();
			allProp.put(key, getValue(key));
		}
		return allProp;
	}

	public Iterator<String> getAllPropNames() {
		Set<String> propSet = props.stringPropertyNames();
		Iterator<String> iprop = propSet.iterator();// 先迭代出来
		return iprop;
	}

}
