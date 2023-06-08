package com.example.web.controller;

import com.example.web.config.ShowUserDetails;
import com.example.web.model.InvoiceModel;
import com.example.web.model.UserModel;
import com.example.web.service.ICartService;
import com.example.web.service.InvoiceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ICartService cartService;

    @RequestMapping(value = "/thanh-toan")
    public String pay(Model model, HttpServletRequest request) {
        model.addAttribute("invoiceModel", new InvoiceModel());
        model.addAttribute("carts", cartService.getCarts());
        return "/view/thanh-toan";
    }

    @RequestMapping(value = "/dat-hang", method = RequestMethod.POST)
    public String oder(@ModelAttribute("invoice") InvoiceModel invoiceModel, @AuthenticationPrincipal ShowUserDetails user, HttpServletRequest request, HttpServletResponse response) {
        invoiceModel.setDateCreated(LocalDateTime.now());
        if (user!= null) {
            invoiceModel.setUser(user.getUserModel());
        }
        Boolean isCheck = invoiceService.saveInvoice(invoiceModel, request, response);
        if (isCheck) {
            return "/view/don-hang";
        } else {
            return "redirect:/gio-hang";
        }

    }
}
