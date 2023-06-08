package com.example.web.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/admin")
    public String admin(Model model){
        return "/view/admin";
    }
}
