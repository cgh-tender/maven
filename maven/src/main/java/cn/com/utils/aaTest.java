package cn.com.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class aaTest {

	public static void getPorperty(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("message", new Locale("en","US"));
		System.out.println(bundle.getString(key));
	}
	public static void getaaaPorperty(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("test");
		System.out.println(bundle.getString(key));
		
	}
	
}
