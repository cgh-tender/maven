package cn.com.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class xmlFactory {

	public void testXml() {
		ArrayList<?> list = Lists.newArrayList("aa",1,2);
		System.out.println(list);
		
		String join = StringUtils.join(list, ",");
		System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "constance_name_String"));
		InputStream inputStream = IOUtils.toInputStream(join,Charsets.toCharset("UTF-8"));
		System.setIn(inputStream);
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			System.out.println(scanner.next());
		}
		
		XMLInputFactory factory = XMLInputFactory.newFactory();
		try {
			XMLEventReader reader = factory.createXMLEventReader(new FileReader("aa"));
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
