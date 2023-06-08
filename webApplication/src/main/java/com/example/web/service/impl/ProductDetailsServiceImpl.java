package com.example.web.service.impl;
import com.example.web.entity.Color;
import com.example.web.entity.Product;
import com.example.web.entity.ProductDetails;
import com.example.web.model.ColorModel;
import com.example.web.model.ProductDetailsModel;
import com.example.web.model.ProductModel;
import com.example.web.model.SizeModel;
import com.example.web.repository.IProductDetailsRepository;
import com.example.web.service.IProductDetailsSerivce;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDetailsServiceImpl implements IProductDetailsSerivce {

    @Autowired
    private IProductDetailsRepository productDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ColorModel> geColorModel(String id) {
        List<ColorModel> colorModels = productDetailsRepository.getColor(id).stream()
                .map(color -> modelMapper.map(color, ColorModel.class))
                .collect(Collectors.toList());
        return colorModels;
    }

    @Override
    public List<SizeModel> getSizeModel(String id) {
        List<SizeModel> sizeModels = productDetailsRepository.getSize(id).stream()
                .map(size -> modelMapper.map(size, SizeModel.class))
                .collect(Collectors.toList());
        return sizeModels;
    }

    @Override
    public ProductModel getProductModel(String id) {
        Product product = productDetailsRepository.getProduct(id);
        ProductModel productModel = modelMapper.map(product, ProductModel.class);
//        List<List<Object>> objects = productDetailsRepository.getProductDetails(1l);
//        List<ColorModel> colorModels =  objects.get(0).stream().map(object -> modelMapper.map(object , ColorModel.class))
//                .collect(Collectors.toList());
//        System.out.println("color : " + colorModels);
        return productModel;
    }

    @Override
    public List<ProductModel> getAllByCategory_Id(String id) {
        List<ProductModel> productModels = productDetailsRepository.getProductCategory(id)
                .stream().map(product -> modelMapper.map(product , ProductModel.class))
                .collect(Collectors.toList());
        return productModels;
    }

}
