package com.example.web.service.impl;
import com.example.web.model.CategoryModel;
import com.example.web.repository.ICategoryRepository;
import com.example.web.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryModel> getCategorys() {

        List<CategoryModel> categoryModels = categoryRepository.findAll()
                .stream().map(category -> modelMapper.map(category , CategoryModel.class))
                .collect(Collectors.toList());

        return categoryModels;
    }
}
