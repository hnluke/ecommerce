package com.controller;

// import com.model.Goods;
import com.model.Users;
import com.service.IUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    IUsersService usersService;
    // 用户平台登录
    @RequestMapping("/login")
    public String login(Users users, HttpServletRequest request) {
        if(usersService.verify(users.getUserName(), users.getUserPwd())) {
            request.getSession().setAttribute("user", users);
            return "userpages/index";
        }

        return "redirect:/user";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/user";
    }

    // 商户平台登录
    @RequestMapping("/commlogin")
    public String loginComm(Users users, HttpServletRequest request) {
        if(usersService.verify(users.getUserName(), users.getUserPwd())) {
            request.getSession().setAttribute("user", users);
            return "commpages/index";
        }

        return "redirect:/comm";
    }

    @RequestMapping("/commlogout")
    public String logoutComm(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/comm";
    }

    @RequestMapping("/deliverlogin")
    public String loginDeliver(Users users, HttpServletRequest request) {
        if(usersService.verify(users.getUserName(), users.getUserPwd())) {
            request.getSession().setAttribute("user", users);
            return "deliverpages/index";
        }

        return "redirect:/deliver";
    }

    @RequestMapping("/deliverlogout")
    public String logoutDeliver(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/deliver";
    }


}
