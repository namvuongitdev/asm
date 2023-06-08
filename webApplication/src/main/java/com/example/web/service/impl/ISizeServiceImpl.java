package com.example.web.service.impl;
import com.example.web.model.SizeModel;
import com.example.web.repository.ISizeRepository;
import com.example.web.service.ISizeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ISizeServiceImpl implements ISizeService {

    @Autowired
    private ISizeRepository iSizeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SizeModel> getSizes() {
        List<SizeModel> sizeModels = iSizeRepository.findAll()
                .stream().map(size -> modelMapper.map(size , SizeModel.class))
                .collect(Collectors.toList());
        return sizeModels;
    }
}
