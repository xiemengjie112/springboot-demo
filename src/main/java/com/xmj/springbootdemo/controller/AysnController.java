package com.xmj.springbootdemo.controller;

import com.xmj.springbootdemo.threadpoolexecutor.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: xieMengJie
 * @Date: 2019/5/18 12:36
 * @Description:
 */
@RestController
@Slf4j
public class AysnController {


    @Autowired
    private AsyncService asyncService;


    @RequestMapping("/aysnSubmit")
    public String submit() {
        log.info("start submit");

        //调用service层的任务
        asyncService.executeAsync();

        log.info("end submit");

        return "success";
    }


}
