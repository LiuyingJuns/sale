package com.springboot.sale.controller;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    private WxMpService wxMpService;
    public static final Logger log = LoggerFactory.getLogger(WechatController.class);


    @GetMapping("/authorize")
    public String authorize(@RequestParam(name = "returnUrl") String returnUrl) throws UnsupportedEncodingException {
        //1.配置
        //2.调用方法
       String url = "http://liuyingjun.nat300.top/sell/wechat/userInfo";

        String redirectUrl = null;
        try {
            redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl,"GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:"+redirectUrl;
    }


    @GetMapping("/userInfo")
    public String userInfo(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state") String returnUrl
    ){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+returnUrl + "?openid=" + openId;
    }
}
