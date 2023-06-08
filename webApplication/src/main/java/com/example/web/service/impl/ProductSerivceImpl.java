package com.example.web.service.impl;
import com.example.web.entity.Product;
import com.example.web.entity.ProductDetails;
import com.example.web.model.ProductDetailsModel;
import com.example.web.model.ProductModel;
import com.example.web.repository.IProductRepository;
import com.example.web.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductSerivceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductModel> getProducts() {
        List<ProductModel> productModels = productRepository.findAll()
                .stream().map(product -> modelMapper.map(product , ProductModel.class))
                .collect(Collectors.toList());
        return productModels;
    }

    @Override
    public void create(ProductModel productModel) {
         Product product = modelMapper.map(productModel , Product.class);
         productRepository.save(product);
    }

}
