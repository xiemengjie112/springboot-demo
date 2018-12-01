package com.xmj.springbootdemo.exception;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:全局异常捕获
 * Author: xieMengJie
 * CreateDate: 2018/12/1 18:19
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    //ResponesBody返回JSON格式
    //ModelAndView返回页面
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JSONObject errorResult(Exception e){
        logger.info("错误信息："+e.getMessage());
        //实际开发中将错误信息记录到日志中
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","500");
        jsonObject.put("msg","系统错误");
        return jsonObject;
    }




}
