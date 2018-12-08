package com.xmj.springbootdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2018/12/8 16:20
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue QueueA() {
        return new Queue("queueA");
    }

    @Bean
    public Queue QueueB() {
        return new Queue("queueB");
    }

    /*
    * @Author: xieMengJie
    * @Date: 2018/12/8 17:09
    * @Param: []
    * @return: org.springframework.amqp.core.FanoutExchange
    * @Description:  创建一个名为fanoutExchange的广播路由器
    */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    /*
    * @Author: xieMengJie
    * @Date: 2018/12/8 17:09
    * @Param: []
    * @return: org.springframework.amqp.core.Binding
    * @Description: 将queueA队列绑定到路由fanoutExchange
    */
   @Bean
    public Binding bindingQueueA(){
        return BindingBuilder.bind(QueueA()).to(fanoutExchange());
   }

    /*
     * @Author: xieMengJie
     * @Date: 2018/12/8 17:09
     * @Param: []
     * @return: org.springframework.amqp.core.Binding
     * @Description: 将queueB队列绑定到路由fanoutExchange
     */
    @Bean
    public Binding bindingQueueB(){
        return BindingBuilder.bind(QueueB()).to(fanoutExchange());
    }

}
