package cn.com.utils;

import java.util.List;

public class MyCalBack{

    public Long Add(long a, long b, MyFunction<Long,Long> myFunction){
        return myFunction.hander(a,b);
    }

    public List<MyCalBack> Add(String  a, String b, MyFunction<String  ,List<MyCalBack> > myFunction){
        return myFunction.hander(a,b);
    }

    public interface CallBack<T>{
        T UserFunctionCB();
    }
    private static CallBack cbFunction;

    public static void  setCallBackFunction(CallBack cbFun) {
        cbFunction = cbFun;
    }
    private String name;
    private Integer age;

    public MyCalBack() {

    }
    public MyCalBack(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyCalBack{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
