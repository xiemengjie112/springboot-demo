package com.xmj.springbootdemo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2019/1/18 20:54
 */
public interface IUserDao {
    void save();

    void find();

}
 interface IStudentDao{
    void save();

    void find();
}
class StudentDao implements IStudentDao{

    @Override
    public void save() {
        System.out.println("student保存操作");
    }

    @Override
    public void find() {
        System.out.println("student查找操作");
    }
}
//目标对象
class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("模拟：保存用户！");
    }

    @Override
    public void find() {
        System.out.println("模拟：查询用户");
    }
}

/**
 * 静态代理
 * 特点：
 * 2. 目标对象必须要实现接口
 * 2. 代理对象，要实现与目标对象一样的接口
 */
class UserDaoProxy implements IUserDao {

    // 代理对象，需要维护一个目标对象
    private IUserDao target = new UserDao();

    @Override
    public void save() {
        System.out.println("代理操作： 开启事务...");
        target.save();   // 执行目标对象的方法
        System.out.println("代理操作：提交事务...");
    }

    @Override
    public void find() {
        target.find();
    }
}

/**
 * 动态代理：
 * 代理工厂，给多个目标对象生成代理对象！
 *
 */
class ProxyFactory {

    // 接收一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 返回对目标对象(target)代理后的对象(proxy)
    public Object getProxyInstance() {
        Object proxy = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),  // 目标对象使用的类加载器
                target.getClass().getInterfaces(),   // 目标对象实现的所有接口
                new InvocationHandler() {            // 执行代理对象方法时候触发

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {

                        // 获取当前执行的方法的方法名
                        String methodName = method.getName();
                        // 方法返回值
                        Object result = null;
                        if ("find".equals(methodName)) {
                            // 直接调用目标对象方法
                            result = method.invoke(target, args);
                        } else {
                            System.out.println("开启事务...");
                            // 执行目标对象方法
                            result = method.invoke(target, args);
                            System.out.println("提交事务...");
                        }
                        return result;
                    }
                }
        );
        return proxy;
    }
}