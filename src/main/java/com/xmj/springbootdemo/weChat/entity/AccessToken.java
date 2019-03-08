package com.xmj.springbootdemo.weChat.entity;

import lombok.Data;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2019/3/3 16:21
 */
@Data
public class AccessToken {


    private String token;
    //有效时间
    private int expiresIn;

}
