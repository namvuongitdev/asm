package com.example.web.service;
import com.example.web.model.ColorModel;
import com.example.web.model.ProductModel;
import com.example.web.model.SizeModel;
import java.util.List;

public interface IProductDetailsSerivce {

    List<ColorModel> geColorModel(String id);

    List<SizeModel> getSizeModel(String id);

    ProductModel getProductModel(String id);

    List<ProductModel> getAllByCategory_Id(String id);

}
