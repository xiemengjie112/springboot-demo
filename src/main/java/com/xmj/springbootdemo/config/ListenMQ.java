package com.xmj.springbootdemo.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2018/12/8 16:52
 */
@Component
public class ListenMQ {



    @RabbitListener(queues = {"queueA"})
    public void listenterA(Message message, Channel channel) throws IOException {
        try {
            System.out.println("A监听到消息并且消费" + message);
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("A正常消费");
        } catch (Exception e) {
            //告诉服务这条消息消费失败了，需要重新发送。
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            System.out.println("A消费失败");
        }
    }

    @RabbitListener(queues = {"queueB"})
    public void listenterB(Message message, Channel channel) throws IOException {
        try {
            System.out.println("B监听到消息并且消费" + message);
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("B正常消费");
        } catch (Exception e) {
            //告诉服务这条消息消费失败了，需要重新发送。
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            System.out.println("B消费失败");
        }
    }

}
