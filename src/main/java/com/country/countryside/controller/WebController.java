package com.country.countryside.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通用配置接口
 */
@Controller
public class WebController {

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }
}
