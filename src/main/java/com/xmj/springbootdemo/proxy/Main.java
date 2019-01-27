package com.xmj.springbootdemo.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2019/1/18 20:55
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("qaaaa");
        Stundets stundets1 = new Stundets("xmj",111);
        Stundets stundets2 = new Stundets("xmj2",111);
        Stundets stundets3 = new Stundets("xmj3",111);
        List<Stundets> stundets = new ArrayList<>();
        stundets.add(stundets1);
        stundets.add(stundets2);
        stundets.add(stundets3);
       /* stundets.stream().forEach(v ->{
            System.out.println(v.getName());
            System.out.println(v.getSex());
        });*/
        Map map  =stundets.stream().collect(Collectors.toMap(Stundets::getName,Stundets::getSex));
        map.forEach((k,v)-> System.out.println("key:"+k+"value:"+v) );
        String abc = new String("abc");
    /*    List<Stundets> stundets4 = stundets.stream().filter(v->!v.getName().equals("xmj")).collect(Collectors.toList());
        stundets4.stream().forEach(v-> System.out.println(v.getName()));*/
       /* //静态代理
        IUserDao proxy = new UserDaoProxy();
        proxy.save();
        //动态代理
        IUserDao target = new UserDao();//创建目标对象
        System.out.println("目标对象："+target.getClass());
        IUserDao dyProxy = (IUserDao) new ProxyFactory(target).getProxyInstance();//代理对象
        System.out.println("代理对象："+dyProxy.getClass());
        dyProxy.save();//执行代理对象的方法
        IStudentDao studentTarget = new StudentDao();//创建目标对象
        System.out.println("目标对象："+studentTarget.getClass());
        IStudentDao dyStudentProxy = (IStudentDao) new ProxyFactory(studentTarget).getProxyInstance();//代理对象
        dyStudentProxy.save();*/
    }


}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Stundets{

    private String name;

    private Integer sex;

}
