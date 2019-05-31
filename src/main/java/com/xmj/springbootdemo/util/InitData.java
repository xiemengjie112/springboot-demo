package com.xmj.springbootdemo.util;

import com.xmj.springbootdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2018/11/10 15:25
 */

public class InitData {

    @Autowired
    private  StudentService studentService;

   // public static  String STUDENTS = "";

    public static String getStudents(){
        return "";
    }


}
