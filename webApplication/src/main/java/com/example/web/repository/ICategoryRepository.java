package com.example.web.repository;
import com.example.web.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category ,Long> {

    @Override
    List<Category> findAll();


}
