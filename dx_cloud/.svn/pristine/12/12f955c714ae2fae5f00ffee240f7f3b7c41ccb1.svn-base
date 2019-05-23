package com.dw.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableHeadPropertiesUtils {
	private static final Logger logger = LoggerFactory.getLogger(TableHeadPropertiesUtils.class);
	private static final String CONFIG_NAME = "tableheadconfig.properties";
	private static Properties prop = null;
	private static Long lastModified = 0L;
	
	private static boolean isPropertiesModified() {
		boolean returnValue = false;
		File file = new File(TableHeadPropertiesUtils.class.getClassLoader().getResource(CONFIG_NAME).getPath());
		if (file.lastModified() > lastModified) {
			lastModified = file.lastModified();
			returnValue = true;
		}
		return returnValue;
	}

	/**
	 * 默认构造函数，用于sh运行，自动找到classpath下的config.properties。
	 */
	public TableHeadPropertiesUtils() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void init() throws IOException {
		if (prop == null) {
			System.out.println(" 加载table Head properties 信息!");
			prop = new Properties();
			String filepath = TableHeadPropertiesUtils.class.getClassLoader().getResource(CONFIG_NAME).getPath();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(filepath);
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取对应的值
	 * 
	 * @param key 值
	 * @return
	 */
	public String getPropertyValue(String key) {
		String value = "";
		try {
			if (prop == null || isPropertiesModified()) {
				init();
			}
			value = prop.get(key).toString();
		} catch (NullPointerException e) {
			logger.error(key + " 这个参数为空!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 读取properties的全部信息
	 * 
	 * @throws FileNotFoundException 配置文件没有找到
	 * @throws IOException           关闭资源文件，或者加载配置文件错误
	 * 
	 */
	public Map<String, String> readAllProperties() throws FileNotFoundException, IOException {
		// 保存所有的键值
		Map<String, String> map = new HashMap<String, String>();
		Enumeration en = prop.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String Property = prop.getProperty(key);
			map.put(key, Property);
		}
		return map;
	}

	public void setValue(String key,String value) throws IOException {
    	Properties prop = new Properties();
    	String path = TableHeadPropertiesUtils.class.getClassLoader().getResource(CONFIG_NAME).getPath();
        InputStream fis = new FileInputStream(path);
        prop.load(fis);
        OutputStream fos = new FileOutputStream(path);
        prop.setProperty(key, value);
        prop.store(fos,"last update");
        fis.close();
        fos.close();
    }
}
