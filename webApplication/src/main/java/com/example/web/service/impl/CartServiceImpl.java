package com.example.web.service.impl;

import com.example.web.entity.ProductDetails;
import com.example.web.model.CartModel;
import com.example.web.repository.IProductDetailsRepository;
import com.example.web.service.ICartService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private IProductDetailsRepository productDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


    @Override
    public String addItem(String idColor, String idSize, String idProduct, Integer quantity) {
        Cookie[] cookies = request.getCookies();
        if (idColor == null || idSize == null) {
            return "lựa chọn các tuỳ chọn trước khi thêm vào giỏ hàng";
        } else {
            if (quantity == null) {
                quantity = 1;
            }
            ProductDetails productDetails = productDetailsRepository.getProductDetailsByProduct_IdAndColor_IdAndSize_Id(idProduct, idColor, idSize);
            if (productDetails.getQuantity() < quantity) {
                return "sản phẩm trong kho chỉ lại " + productDetails.getQuantity();
            }
            if (cookies != null) {
                for (Cookie getCookie : cookies) {
                    if (getCookie.getName().equalsIgnoreCase("JSESSIONID")) {
                        continue;
                    }
                    if (Long.parseLong(getCookie.getName()) == productDetails.getId()) {
                        Integer updateValue = quantity + Integer.parseInt(getCookie.getValue());
                        Cookie cookie = newCookie(productDetails.getId(), updateValue, 1);
                        response.addCookie(cookie);
                        return "sản phẩm đã được thêm vào giỏ hàng";
                    }
                }
            }
            Cookie cookie = newCookie(productDetails.getId(), quantity, 1);
            response.addCookie(cookie);
            return "sản phẩm đã được thêm vào giỏ hàng";
        }
    }

    @Override
    public List<CartModel> getCarts() {
        Cookie[] cookies = request.getCookies();
        List<CartModel> cartModels = new ArrayList<>();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("JSESSIONID")) {
                    continue;
                }
                Optional<ProductDetails> productDetails = productDetailsRepository.findById(Long.parseLong(cookie.getName()));
                if (productDetails.isPresent()) {
                    CartModel cartModel = modelMapper.map(productDetails.get(), CartModel.class);
                    cartModel.setQuantityValueCookie(Integer.parseInt(cookie.getValue()));
                    cartModels.add(cartModel);
                } else {
                    continue;
                }
            }
        } else {
            return null;
        }
        return cartModels;
    }

    @Override
    public Cookie newCookie(Long id, Integer quantity, Integer day) {
        Cookie cookie = new Cookie(id.toString(), quantity.toString());
        cookie.setMaxAge(day * 60 * 60);
        return cookie;
    }


    @Override
    public String removeItem(Long id) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(String.valueOf(id))) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    return "sản phẩm đã được xoá khỏi giỏ hàng";
                }
            }
        }
        return null;
    }

    @Override
    public void updateCart(Long id, Integer quantity) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("JSESSIONID")) {
                    continue;
                }
                if (Long.parseLong(cookie.getName()) == id) {
                    cookie.setValue(quantity.toString());
                    response.addCookie(cookie);
                }
            }
        }
    }
}
