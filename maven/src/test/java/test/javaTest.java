package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.INTERNAL;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.*;
//TestNG

public class javaTest {
private static Logger logger = LogManager.getLogger("javaTest");

    public static void main(String[] args) {
        List<Integer> list = asList(1,2,3,4,5,2,4,6);
        Object[] c = list.parallelStream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                logger.info(o1+"-"+o2);
                logger.info(o1.compareTo(o2));
                return o1.compareTo(o2);
            }
        }).toArray();
//        Converter<String,Integer> converter = Integer::valueOf;
//        Integer i = converter.convert("11");
//        logger.info(i);
        Converter<Integer,String> converter =  new Something()::start;
        logger.info(converter.convert(11));

        Collections.sort(list, Integer::compareTo);
        logger.info(list);


    }

    @FunctionalInterface
    interface Converter<F, T>{
        T convert(F from);
    }
    static class Something{
        String start(Integer from){
            return "1222";
        }
        String end(Integer from){

            return "ccc";
        }
    }
}
