package com.proto.linksaver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/{path:[^\\.]*}")
    public String forward() {
        return "forward:/index.html";
    }
}   