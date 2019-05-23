package cn.com.utils;

import com.google.common.base.Splitter;

public class splitFactory {

	public void testSplit() {
		Iterable<String> split = Splitter.on(",").trimResults().omitEmptyStrings().split("aaa,cc,, dd");
		System.out.println(split);
		for(String aString : split)
			System.out.println(aString);
		
	}
}
