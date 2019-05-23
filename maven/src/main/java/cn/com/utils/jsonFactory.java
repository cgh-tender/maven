package cn.com.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonFactory {
	public void testJson() {
		JsonFactory jsonFactory = new JsonFactory();
		String s = "{\"id\": 1,\"name\": \"小明\",\"array\": [\"1\", \"2\"]}";
        ObjectMapper mapper = new ObjectMapper();
        //Json映射为对象
        Student student;
		try {
			student = mapper.readValue(s, Student.class);
			String json = mapper.writeValueAsString(student);
	        System.out.println(json);
	        String bs = mapper.writeValueAsString(student);
	        
	        System.out.println(student.toString());
	        System.out.println(bs);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
