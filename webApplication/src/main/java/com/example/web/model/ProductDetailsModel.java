package com.example.web.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductDetailsModel {

    private Long id;
    private Integer quantity;
    private Float importPrice;
    private ProductModel product;
    private ColorModel color;
    private SizeModel size;
}
