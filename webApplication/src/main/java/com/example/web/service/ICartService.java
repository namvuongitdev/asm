package com.example.web.service;
import com.example.web.model.CartModel;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public interface ICartService {

    String addItem(String idColor , String idSize , String idProduct
                  , Integer quantity );

    List<CartModel> getCarts();

    Cookie newCookie(Long id , Integer quantity  , Integer age);


    String removeItem(Long id );

    void updateCart(Long id ,Integer quantity);
}
