package com.lx.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HiController {

    @RequestMapping("/hi")
    public String hi()
    {
        return "index";
    }
}
