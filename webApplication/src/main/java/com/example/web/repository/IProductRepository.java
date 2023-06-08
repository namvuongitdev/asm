package com.example.web.repository;
import com.example.web.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product ,Long> {

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    <S extends Product> S save(S entity);
}
