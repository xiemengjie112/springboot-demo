package com.xmj.springbootdemo.java8;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2019/3/4 20:07
 */
@Data
@AllArgsConstructor
public class User {

    private int num;

    private String type;

    private String name;

    private String text;

}
