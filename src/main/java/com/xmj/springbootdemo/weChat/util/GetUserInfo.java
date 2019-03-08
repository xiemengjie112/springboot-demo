package com.xmj.springbootdemo.weChat.util;

import com.xmj.springbootdemo.util.HttpClientUtil;
import com.xmj.springbootdemo.weChat.entity.AccessToken;
import net.sf.json.JSONObject;

import java.util.HashMap;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2019/3/3 16:11
 */
public class GetUserInfo {

     private static final String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info";

    /**
     * @param @param  openid
     * @param @return
     * @param @throws Exception
     * @Description: 通过openid获取用户微信信息
     * @author dapengniao
     * @date 2016年3月18日 下午2:01:30
     */

    public static HashMap<String, Object> Openid_userinfo(String openid)throws Exception {
        HashMap<String, Object> params = new HashMap();
        AccessToken accessToken = WeiXinUtil.getAccessToken();
        if (accessToken == null){
            System.out.println("没有获取到token");
            return null;
        }
        params.put("access_token", accessToken.getToken()); //定时器中获取到的token
        params.put("openid", openid); //需要获取的用户的openid
        params.put("lang", "zh_CN");
        String subscribers = HttpClientUtil.sendGet( getUserInfoUrl, params);
        System.out.println(subscribers);
        params.clear();
        //这里返回参数只取了昵称、头像、和性别
        params.put("nickname", JSONObject.fromObject(subscribers).getString("nickname")); //昵称
        params.put("headimgurl",JSONObject.fromObject(subscribers).getString("headimgurl")); //图像
        params.put("sex", JSONObject.fromObject(subscribers).getString("sex")); //性别
        return params;
    }


}
