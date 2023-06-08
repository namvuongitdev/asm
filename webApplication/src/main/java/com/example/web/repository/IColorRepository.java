package com.example.web.repository;
import com.example.web.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IColorRepository extends JpaRepository<Color , Long> {

    @Override
    List<Color> findAll();
}
