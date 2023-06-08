package com.example.web.repository;
import com.example.web.entity.Color;
import com.example.web.entity.Product;
import com.example.web.entity.ProductDetails;
import com.example.web.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface IProductDetailsRepository extends JpaRepository<ProductDetails, Long>  {

    @Query(value = "select  productDetails.color from ProductDetails productDetails  where productDetails.product.id = :id" )
    List<Color> getColor(@Param("id") String id);

    @Query(value = "select  productDetails.size  from ProductDetails productDetails  where productDetails.product.id = :id")
    List<Size> getSize(@Param("id") String id);

    @Query(value = "select  productDetails.product  from ProductDetails productDetails  where productDetails.product.id = :id")
    Product getProduct(@Param("id") String id);

    @Query(value = "select  productDetails.product  from ProductDetails productDetails  where productDetails.category.id = :id")
    List<Product> getProductCategory(@Param("id") String id);

    ProductDetails getProductDetailsByProduct_IdAndColor_IdAndSize_Id(String idProduct, String idColor, String idSize);

    @Override
    Optional<ProductDetails> findById(Long aLong);

//    @Query(value = "select  productDetails.color , productDetails.size from ProductDetails  productDetails where productDetails.product.id = ?1")
//    List<List<Object>> getProductDetails(Long id);

}
