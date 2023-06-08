package com.example.web.service;
import com.example.web.model.ProductModel;
import java.util.List;

public interface IProductService {

    List<ProductModel> getProducts();

    void create(ProductModel productModel);

}
