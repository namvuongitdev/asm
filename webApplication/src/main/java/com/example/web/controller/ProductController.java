package com.example.web.controller;
import com.example.web.model.CategoryModel;
import com.example.web.model.ProductDetailsModel;
import com.example.web.model.ProductModel;
import com.example.web.service.ICategoryService;
import com.example.web.service.IColorService;
import com.example.web.service.IProductService;
import com.example.web.service.ISizeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISizeService sizeService;

    @Autowired
    private IColorService colorService;

    @Autowired
    private HttpServletRequest request;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @RequestMapping(value = "/products")
    public String getProducts(Model model) {
        List<ProductModel> products = productService.getProducts();
        List<CategoryModel> categoryModels = categoryService.getCategorys();
        model.addAttribute("products", products);
        model.addAttribute("categorys", categoryModels);
        return "/view/trang-chu";
    }

    @RequestMapping(value = "/admin/products")
    public String adminProduct(Model model) {
        List<ProductModel> productModels = productService.getProducts();
        model.addAttribute("products", productModels);
        model.addAttribute("request", request.getRequestURI());
        return "/view/admin";
    }

    @RequestMapping(value = "/admin/new")
    public String newProduct(Model model) {
        model.addAttribute("colors", colorService.getColors());
        model.addAttribute("sizes", sizeService.getSizes());
        model.addAttribute("categorys", categoryService.getCategorys());
        model.addAttribute("productModel", new ProductModel());
        model.addAttribute("productDetailsModel", new ProductDetailsModel());
        model.addAttribute("request", request.getRequestURI());
        return "/view/admin";
    }

    @RequestMapping(value = "/admin/create-product", method = RequestMethod.POST)
    public String newProduct(@ModelAttribute("productModel") ProductModel productModel, MultipartFile multipartFil) {
        // productService.create(productModel);
        return "redirect:/admin/new";
    }
}
