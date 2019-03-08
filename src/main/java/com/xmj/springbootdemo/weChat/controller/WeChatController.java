package com.xmj.springbootdemo.weChat.controller;

import com.xmj.springbootdemo.util.DateUtils;
import com.xmj.springbootdemo.weChat.MessageUtil;
import com.xmj.springbootdemo.weChat.TextMessage;
import com.xmj.springbootdemo.weChat.TulingApiUtil;
import com.xmj.springbootdemo.weChat.WeChatAccessUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Description: 微信接口
 * Author: xieMengJie
 * CreateDate: 2018/12/31 16:33
 */
@RestController
@Slf4j
public class WeChatController {




    /**
     * 微信接入服务器
     * get请求是接入功能
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/weChat/Access")
    public void AccessWeChat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        if(WeChatAccessUtil.checkSignature(signature, timestamp, nonce)){
            //如果校验成功，将得到的随机字符串原路返回
            out.print(echostr);
        }
    }

    @RequestMapping(value = "/weChat/Access",method = RequestMethod.POST)
    public void WeChatCallBack(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out =response.getWriter();
        try {
            String message=null;
            Map<String, String> map=MessageUtil.xmlToMap(request);
            String toUserName=map.get("ToUserName");//开发者微信号
            String fromUserName=map.get("FromUserName");//发送方帐号（一个OpenID）
            String msgType=map.get("MsgType");//消息类型
            String content=map.get("Content");//客户端发送的消息
            String event = map.get("Event");//事件类型:subscribe(订阅)、unsubscribe(取消订阅)
            TextMessage text =new TextMessage();
            text.setFromUserName(toUserName);
            text.setToUserName(fromUserName);
            text.setCreateTime(new Date().getTime());
            //事件推送
            //获取用户基本信息
            //Map<String,Object> userinfo = GetUserInfo.Openid_userinfo(fromUserName);
           /* userinfo.forEach((k,v)->{
                System.out.println("key:"+k);
                System.out.println("value:"+v);
            });*/
            if("text".equals(msgType)){
                text.setMsgType("text");
                //这里填写回复内容
                //图灵机器人
                System.out.println("test..............");
                text.setContent(TulingApiUtil.getTulingResult(content));
                message=MessageUtil.textMessageToXml(text);
            }else if("event".equals(msgType)){
                if ("unsubscribe".equals(event)){
                    log.info("{}在{}取消了关注",fromUserName, DateUtils.dateToStr(new Date()));
                }else if ("subscribe".equals(event)){
                    log.info("{}在{}进行了关注",toUserName,DateUtils.dateToStr(new Date()));
                    text.setContent(fromUserName +": 感谢您的关注");
                    message=MessageUtil.textMessageToXml(text);
                }
            }
            out.print(message);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }

    }

}
