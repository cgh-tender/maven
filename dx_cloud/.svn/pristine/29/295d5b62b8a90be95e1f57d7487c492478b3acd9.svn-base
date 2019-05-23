package com.dw.cxfservice;
 
import java.util.ArrayList;
import java.util.List;
 
import javax.jws.WebService;
 
 @WebService
public class PersonServiceImpl implements IPersonService {
	@Override
	public Person getUserById(long id) {
	Person user = new Person(100000, "testhao", 29);
	System.out.println(user.toString());
	return user;
	}
 
 
	@Override
	public List<Person> getAllUsers() {
	List<Person> list = new ArrayList<>();
	Person user = new Person(100000, "test", 29);
	Person user1 = new Person(100001, "test1", 19);
	Person user2 = new Person(100002, "test2", 39);
	list.add(user);
	list.add(user1);
	list.add(user2);
	System.out.println(list.toString());
	return list;
	}
 
}