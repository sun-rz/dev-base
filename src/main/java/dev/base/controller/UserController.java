package dev.base.controller;

import dev.base.common.WebComm;
import dev.base.entity.User;
import dev.base.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    //登录操作
    @ResponseBody
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {

        String msg = "";
        int refStatus = WebComm.validateReferURL(request,"/login.html");
        if (refStatus < 2) {
            msg = WebComm.getReturnJSON("非法请求", false);
            return msg;
        }

        JSONObject object = WebComm.getRequestBodyJSON(request);

        if (object.size() < 1) {
            msg = WebComm.getReturnJSON("参数错误", false);
            return msg;
        }
        String captchacode = (String) object.get("captchacode");
        String captcha_code = (String) request.getSession().getAttribute("captcha_code");

        if (WebComm.isBlank(captchacode) || !(captchacode.equalsIgnoreCase(captcha_code))) {
            msg = WebComm.getReturnJSON("验证码错误", false);
            return msg;
        }
        String username = (String) object.get("username");
        if (WebComm.isBlank(username)) {
            msg = WebComm.getReturnJSON("用户名不能为空", false);
            return msg;
        }
        String password = (String) object.get("password");
        if (WebComm.isBlank(password)) {
            msg = WebComm.getReturnJSON("密码不能为空", false);
            return msg;
        }

        if ("login".equals(object.get("action"))) {
            password = WebComm.getMD5Encode(password);

            User user = userService.login(username, password);
            if (user == null) {
                msg = WebComm.getReturnJSON("用户名或密码错误", false);
                return msg;
            } else {
                request.getSession().setAttribute("session_user", user);//登录成功后将用户放入session中
                msg = WebComm.getReturnJSON("登录成功", true);
                return msg;
            }
        }
        msg = WebComm.getReturnJSON("无效请求", false);
        return msg;
    }

    //注册操作
    @ResponseBody
    @RequestMapping("/register")
    public String register(HttpServletRequest request) {

        String msg = "";
        int refStatus = WebComm.validateReferURL(request,"/register.html");
        if (refStatus < 2) {
            msg = WebComm.getReturnJSON("非法请求", false);
            return msg;
        }

        JSONObject object = WebComm.getRequestBodyJSON(request);

        if (object.size() < 1) {
            msg = WebComm.getReturnJSON("参数错误", false);
            return msg;
        }
        String captchacode = (String) object.get("captchacode");
        String captcha_code = (String) request.getSession().getAttribute("captcha_code");

        if (WebComm.isBlank(captchacode) || !(captchacode.equalsIgnoreCase(captcha_code))) {
            msg = WebComm.getReturnJSON("验证码错误", false);
            return msg;
        }
        String username = (String) object.get("username");
        if (WebComm.isBlank(username)) {
            msg = WebComm.getReturnJSON("用户名不能为空", false);
            return msg;
        }
        String password = (String) object.get("password");
        if (WebComm.isBlank(password)) {
            msg = WebComm.getReturnJSON("密码不能为空", false);
            return msg;
        }

        if ("register".equals(object.get("action"))) {
            password = WebComm.getMD5Encode(password);

            List list = userService.getListBySql("select * from [user] where userName='"+username+"'");

            if(list.size()>0){
                msg = WebComm.getReturnJSON("用户名已存在", false);
                return msg;
            }

            int result=userService.register(username,password);

            if(result>1){
                msg = WebComm.getReturnJSON("注册失败，服务器异常", false);
                return msg;
            }else{
                request.setAttribute("username",username);
                msg = WebComm.getReturnJSON("注册成功", true);
                return msg;
            }
        }

        msg = WebComm.getReturnJSON("无效请求", false);
        return msg;
    }

    //退出登录
    @RequestMapping("/logout")
    public void outUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("session_user");
        response.sendRedirect("/login.html");
    }

}