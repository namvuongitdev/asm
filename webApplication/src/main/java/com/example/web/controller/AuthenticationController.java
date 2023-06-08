package com.example.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
public class AuthenticationController {


    @RequestMapping(value = "/login")
    public String login(Principal principal) {
        if (principal != null) {
            return "redirect:/products";
        }
        return "/view/login";

    }

    @RequestMapping(value = "/login/error")
    public String error(Model model) {
        model.addAttribute("error", "email hoặc mật khẩu không chính xác");
        return "/view/login";
    }


}
