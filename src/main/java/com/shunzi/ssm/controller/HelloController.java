package com.shunzi.ssm.controller;

import com.shunzi.ssm.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/4/11.
 */
@Controller
@RequestMapping(value="/hello")
public class HelloController {
    @RequestMapping(value="/index")
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("hello","Hello world!!");
        return mv;
    }
    @RequestMapping(value="/json")
    public @ResponseBody User jsonTest(){
        User user = new User();
        user.setUserName("teset");
        user.setRealName("张三");
        user.setMobile("186070962378");
        user.setUserPassword("123456");
        return user;
    }


}
