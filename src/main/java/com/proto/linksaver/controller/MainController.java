package com.proto.linksaver.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class MainController {
    @RequestMapping("/signup")
    public ModelAndView toSignupPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ModelAndView("forward:/");
    }

    @RequestMapping("/signin")
    public ModelAndView toSigninPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ModelAndView("forward:/");
    }

    @RequestMapping("/links")
    public ModelAndView toLinksPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ModelAndView("forward:/");
    }

    @RequestMapping("/links/all")
    public ModelAndView toAllLinksPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ModelAndView("forward:/");
    }

    @RequestMapping("/links/favorites")
    public ModelAndView toFavoriteLinksPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ModelAndView("forward:/");
    }
}   