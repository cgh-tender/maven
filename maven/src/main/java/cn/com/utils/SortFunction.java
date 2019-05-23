package cn.com.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortFunction {
    private static Logger logger = LogManager.getLogger(SortFunction.class);
    volatile static List<Integer> list = new ArrayList<>();
    public static void main(String[] args){

        Thread b = new Thread(new testTest(){
            public void run() {
                for (int i = 200 ; i<300;i++){
                    list.add(i);
                }
            }
            });
        Thread a = new Thread(new testTest(){
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list.add(i);
                }
            }
        });
        b.start();
        a.start();
        logger.info(list);
        int len = list.size() - 1;
        for (int i = 0; i < len ; i++){
            for (int j = 0; j < len - i; j++) {
                if(list.get(j) > list.get(j + 1)){
                    int tem = list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,tem);
                }
            }
        }
        logger.info(list);
    }
}
abstract class testTest implements Runnable{

}