package com.xmj.springbootdemo.java8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Description: java8特性
 * Author: xieMengJie
 * CreateDate: 2018/12/14 21:12
 */
public class Java8Test {

    public static final  DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static Logger LOGGER = LoggerFactory.getLogger(Java8Test.class);

    @Test
    public void testDate(){
        LocalDate date = LocalDate.now();
        System.out.println(date);
        LocalDateTime dateTime =  LocalDateTime.now();
        System.out.println(DF.format(dateTime));
        System.out.println(DateUtil.getCurDateTimeFull());
        System.out.println(DateUtil.beforeNDaysDate("yyyy-MM-dd HH:mm:ss",1));
    }


    int  count = 0;
    @Test
    public void testThread(){
        for (int i = 0; i <5; i++) {
            count ++;
            new Thread(() ->
            {
                System.out.println("进入第"+count+"个线程");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }).start();
        }
    }

    @Test
    public void testSortLength(){
        List<String> strs = Arrays.asList("websphere","nginx","weblogic","tomcat");
        //1.8之前按照字符串长度排序
        Collections.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });
        System.out.println("java8之前排序："+strs);
        List<String> strs2 = Arrays.asList("websphere","nginx","weblogic","tomcat");
        //1.8之后按照字符串长度排序
        Collections.sort(strs2,(a,b)-> a.length()-b.length());
        System.out.println("java8之后排序:"+strs2);


    }

    @Test
    public void testFunInterface() {
        FunInterface a = message -> System.out.println("hello"+message);
        a.sayMessage("函数式接口实现");
        System.out.println(LocalDate.now());
        //（1）Callable规定的方法是call()，而Runnable规定的方法是run()。
        //（2）Callable的任务执行后可返回值，而Runnable的任务是不能返回值的。
        //（3）call()方法可抛出异常，而run()方法是不能抛出异常的。
        //（4）运行Callable任务可拿到一个Future对象。
        Callable c1 = new Callable() {
            @Override
            public Object call() throws Exception {
                return "this not is lambda";
            }
        };
        Callable c2 = () -> {return "this is lambda";};//省略return 前
        Callable c3 = () ->  "this is lambda";//省略return 后
        try {
            System.out.println(c1.call());
            System.out.println(c2.call());
            System.out.println(c3.call());
        } catch (Exception e) {
            LOGGER.info("==================================》gg");
            e.printStackTrace();
        }


    }


}

