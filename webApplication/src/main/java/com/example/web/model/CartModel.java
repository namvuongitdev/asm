package com.example.web.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CartModel {
    private Long id;
    private Integer quantity;
    private Integer quantityValueCookie;
    private Float importPrice;
    private ProductModel product;
    private ColorModel color;
    private SizeModel size;

    public Float thanhTien(){
      return this.quantityValueCookie * this.product.getPrice();
    }
}


