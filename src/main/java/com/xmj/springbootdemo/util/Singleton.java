package com.xmj.springbootdemo.util;

/**
 * Description: Singleton类被装载了，instance不一定被初始化。
 *              因为SingletonHolder类没有被主动使用，只有显示通过调用getInstance方法时，
 *              才会显示装载SingletonHolder类，从而实例化instance
 * Author: xieMengJie
 * CreateDate: 2019/2/22 20:50
 */
public class Singleton {

    private static class SingletonHolder{
        private static Singleton instance = new Singleton();
    }
    private Singleton(){

    }

    public static final Singleton getInsatance(){
        return SingletonHolder.instance;
    }

}
