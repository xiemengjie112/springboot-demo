package com.xmj.springbootdemo.threadpoolexecutor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Auther: xieMengJie
 * @Date: 2019/5/18 12:35
 * @Description:
 */
@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {



    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        log.info("start executeAsync");
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        log.info("end executeAsync");
    }
}
