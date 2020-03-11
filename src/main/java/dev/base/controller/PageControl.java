package dev.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageControl {

    //跳转到首页
    @RequestMapping("/index.html")
    public String welcome() {
        return "index";
    }

    //跳转到登录页面
    @RequestMapping("/login.html")
    public String toLogin() {
        return "/page/login";
    }

    //跳转到注册页
    @RequestMapping("/register.html")
    public String toRegister() {
        return "/page/register";
    }

    //跳转到404
    @RequestMapping("/404.html")
    public String to404() {
        return "/page/404";
    }

    //跳转到500
    @RequestMapping("/500.html")
    public String to500() {
        return "/page/500";
    }
}
