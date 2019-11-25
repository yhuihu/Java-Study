package com.study.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Tiger
 * @date 2019-10-28
 * @see com.study.demo.controller
 **/
@Controller
public class FrontController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/websocket/{name}")
    public String webSocket(@PathVariable String name, Model model){
        try{
            logger.info("跳转到websocket的页面上");
            model.addAttribute("username",name);
            return "index";
        }
        catch (Exception e){
            logger.info("跳转到websocket的页面上发生异常，异常信息是："+e.getMessage());
            return "error";
        }
    }
}
