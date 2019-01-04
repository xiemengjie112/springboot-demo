package com.xmj.springbootdemo.application;

import com.xmj.springbootdemo.service.StudentService;
import com.xmj.springbootdemo.util.RedisService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2018/11/10 15:22
 */
@Component
@Order(1)
@Slf4j
public class StartupRunner implements CommandLineRunner {

    /**
     * 存放公共属性
     */
    private static final List<Map<String, Object>> STUDENTS = new ArrayList<>();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RedisService redisService;

    @Autowired
    private StudentService studentService;

    @Override
    public void run(String... args) throws Exception {
        log.info("========================>启动加载资源操作");
        webApplicationContext.getServletContext().setAttribute("students", STUDENTS);
        studentService.initStudents(STUDENTS);
        redisService.set("sutedents", JSONArray.fromObject(STUDENTS).toString(), 10L);
    }


}
