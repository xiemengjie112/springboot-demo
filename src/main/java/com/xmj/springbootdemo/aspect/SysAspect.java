package com.xmj.springbootdemo.aspect;

import com.xmj.springbootdemo.annotation.MyLog;
import com.xmj.springbootdemo.entity.student.SysLog;
import com.xmj.springbootdemo.mapper.student.SysLogMapper;
import com.xmj.springbootdemo.util.DateUtils;
import com.xmj.springbootdemo.util.IpUtils;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Description: 系统切面日志
 * Author: xieMengJie
 * CreateDate: 2018/11/17 14:48
 */
@Aspect
@Component
public class SysAspect {

    private Logger logger = LoggerFactory.getLogger(SysAspect.class);

    @Autowired
    private SysLogMapper sysLogMapper;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.xmj.springbootdemo.annotation.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        //保存日志
        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            sysLog.setOperation(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求的参数
        Object[] args = joinPoint.getArgs();
        if(null != args && args.length > 0) {
            try {
                // controller 中所有包含 HttpServletRequest 参数的方法都要拿到请求参数。
                if (joinPoint.getArgs()[0] instanceof HttpServletRequest) {
                    HttpServletRequest paramReq = (HttpServletRequest) joinPoint.getArgs()[0];
                    String params = JSONObject.fromObject(paramReq.getParameterMap()).toString();
                    if (null != params && params.length() > 2000) {
                        params = params.substring(0, 1999);
                    }
                    sysLog.setParams(params);
                }else{
                    sysLog.setParams("no params, casue no request found.");
                }
            } catch (Exception e) {
                sysLog.setParams(e.getMessage());
                logger.error("==================================> saveSysLog args: " + args, e);
            }
        }

        sysLog.setCreateDate(DateUtils.getStringDate());
        //获取用户名
        sysLog.setUserName("用户名");

        //获取用户ip地址
        sysLog.setIp(IpUtils.getIpAddr(request));
        //调用service保存SysLog实体类到数据库
        sysLogMapper.insert(sysLog);
    }

}
