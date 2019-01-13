package com.xmj.springbootdemo.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.UUID;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2019/1/13 15:29
 */
public class PubTools {





    /**
     * 小数四舍五入方法
     *
     * @param d 小数
     * @return
     */
    public static double doubleFormat(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(d));
    }

    /**
     * 隐藏手机号中间四位，错误手机号不做处理
     * @param telPhone
     * @return
     */
    public static String hidePhone(String telPhone){
        if(StringUtils.isEmpty(telPhone)){
            return null;
        }else{
            return telPhone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
    }

    /**
     * 赋值属性值到另一个对象
     * @param object
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T copyProperties(Object object,Class<T> clazz){
        String json = JSONObject.toJSONString(object);
        return JSONObject.parseObject(json,clazz);
    }

    /**
     * 生成唯一的key用于表主键
     *
     * @return
     */
    public static synchronized String genUUID() {
        //获取UUID
        String uuid = UUID.randomUUID().toString();
        //使用spring自带的MD5加密工具类进行加密
        return DigestUtils.md5DigestAsHex(uuid.getBytes()).toUpperCase();
    }


}
