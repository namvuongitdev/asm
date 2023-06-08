package com.example.web.service.impl;
import com.example.web.model.ColorModel;
import com.example.web.repository.IColorRepository;
import com.example.web.service.IColorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorServiceImpl implements IColorService {

    @Autowired
    private IColorRepository colorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ColorModel> getColors() {
        List<ColorModel> colorModels = colorRepository.findAll().stream()
                .map(color -> modelMapper.map(color , ColorModel.class))
                .collect(Collectors.toList());
        return colorModels;
    }
}
