package com.xmj.springbootdemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Describe:
 * author:谢孟杰
 * Date:2018/10/10
 * Time:21:38
 */
public class Demo {

   private static  Logger logger = LoggerFactory.getLogger(Demo.class);
    public static void main(String[] args) {



      /*  int [] num = {1,5,6,2,7};
        for (int i = 0; i < num.length-1; i++) {
            for (int j = 0; j < num.length-1-i; j++) {
                if(num[j]>num[j+1]){
                int temp=num[j];
                num[j]=num[j+1];
                num[j+1]=temp;
                }
            }
        }
        for (int i : num) {
            System.out.print(i);
        }*/

       /* for (int i = 1; i <=20; i++) {
            new Thread(){
                @Override
                public void run() {
                    Long startRunTime = System.currentTimeMillis();
                    System.out.println("开启第"+this.getName()+"个线程");
                    synchronized (startRunTime){
                        Long a = startRunTime;
                    }
                    try {
                        this.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Long endRunTime = System.currentTimeMillis();
                    Long runTime =startRunTime-endRunTime;
                    System.out.println("========="+this.getName()+"运行时间为"+runTime);
                }
            }.start();
            
        }*/

     /*   try{
            String test = null ;
            System.out.print(test.toString());
        }catch (RuntimeException e){
            e.printStackTrace();
            logger.info("======================================异常");
        }
        System.out.print("哈哈");*/

      /*  int [] array = {1,3,5,2,4};
        int temp ;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
                
            }
            
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
            
        }*/



    }

}
