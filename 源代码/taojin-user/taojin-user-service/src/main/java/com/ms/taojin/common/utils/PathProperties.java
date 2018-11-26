package com.ms.taojin.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PathProperties {
	
	/** 属性文件路径 */
	private final static String fileName = "pathConf.properties";
	/** 属性集 */
	private static Properties props = new Properties();
	/** 外放对象 */
	private static PathProperties propertys;
	
	public static String FILE_UPLOAD_PATH = PathProperties.getInstans().getValue("file.manager.upload.path");
	public static String FILE_ACCESS_PATH = PathProperties.getInstans().getValue("file.manager.access.path");

	/** 构造私有 */
	private PathProperties() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			props.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 公共方法获取实例 */
	public static PathProperties getInstans() {
		synchronized (props) {
			if (propertys == null) {
				propertys = new PathProperties();
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
