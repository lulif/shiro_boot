package com.mmall.demo2.controller;

import com.mmall.demo2.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin success";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit() {
        return "edit success";
    }

    /**
     * login
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //subject登陆
            subject.login(token);
            User user = (User) subject.getPrincipal();
            //Shiro会话管理
            subject.getSession().setAttribute("user", user);
            return "index";
        } catch (Exception e) {
            return "login";
        }
    }

    /**
     * logout
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            //subject注销
            subject.logout();
        }
        return "login";
    }

    /**
     * 启用注解
     * @return
     */
    @RequiresRoles("admin") //→当前用户必须有admin角色才能访问
    @RequiresPermissions("xxx")
    @ResponseBody
    @RequestMapping(value = "/testRole1", method = RequestMethod.GET)
    public String testRole() {
        return "testRole Success";
    }
}
