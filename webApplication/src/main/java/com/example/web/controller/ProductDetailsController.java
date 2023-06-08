package com.example.web.controller;
import com.example.web.model.ColorModel;
import com.example.web.model.ProductModel;
import com.example.web.model.SizeModel;
import com.example.web.service.IProductDetailsSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductDetailsController {

    @Autowired
    private IProductDetailsSerivce productDetailsSerivce;

    @RequestMapping(value = "/{id}")
    public String getProductDetais(@PathVariable String id, Model model) {
        List<ColorModel> colorModels = productDetailsSerivce.geColorModel(id);
        List<SizeModel> sizeModels = productDetailsSerivce.getSizeModel(id);
        ProductModel productModel = productDetailsSerivce.getProductModel(id);
        model.addAttribute("colors", colorModels);
        model.addAttribute("sizes", sizeModels);
        model.addAttribute("product", productModel);
        return "/view/details";
    }

    @RequestMapping(value = "/category")
    public String getProductCategory(@RequestParam String id , Model model){
        List<ProductModel> productModels = productDetailsSerivce.getAllByCategory_Id(id);
        model.addAttribute("products" , productModels);
        return "/view/trang-chu";
    }
 }
