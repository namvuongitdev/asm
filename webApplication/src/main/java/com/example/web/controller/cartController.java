package com.example.web.controller;
import com.example.web.model.CartModel;
import com.example.web.service.ICartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class cartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping(value = "/add-item", method = RequestMethod.POST)
    public String addItem(@RequestParam(required = false) String color, @RequestParam(required = false) String size, @RequestParam(required = false) String quantity) {
        String result = cartService.addItem(color, size, "1", Integer.parseInt(quantity));
        System.out.println("message : "  + result);
        return "redirect:/products/" + 1;
    }

    @RequestMapping(value = "/gio-hang")
    public String cart(Model model) {
        List<CartModel> cartModels = cartService.getCarts();
        if (cartModels != null) {
            model.addAttribute("carts", cartModels);
        } else {
            model.addAttribute("message", "giỏ hàng đang trống");
        }
        return "/view/cart";

    }

    @RequestMapping(value = "/remove-item/{id}")
    public String remove(@PathVariable Long id) {
        String result = cartService.removeItem(id);
        return "redirect:/gio-hang";
    }

    @RequestMapping(value = "/update-cart", method = RequestMethod.POST)
    public String updateCart(@RequestParam("id") String id , @RequestParam String quantity){
        cartService.updateCart(Long.valueOf(id) , Integer.parseInt(quantity));
        return "redirect:/gio-hang";
    }


}
