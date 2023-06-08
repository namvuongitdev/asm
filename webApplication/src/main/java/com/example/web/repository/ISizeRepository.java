package com.example.web.repository;
import com.example.web.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ISizeRepository extends JpaRepository<Size , Long> {

    @Override
    List<Size> findAll();
}
