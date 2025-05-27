package com.example.quanlyxuong.service.impl;

import com.example.quanlyxuong.dto.BoMonDto;
import com.example.quanlyxuong.entity.BoMon;
import com.example.quanlyxuong.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import com.example.quanlyxuong.repository.BoMonRepository;
import com.example.quanlyxuong.service.BoMonService;
import org.modelmapper.TypeMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoMonServiceImpl  implements BoMonService {
    private final BoMonRepository boMonRepository;
    private final ModelMapper modelMapper;

    public BoMonServiceImpl(BoMonRepository boMonRepository, ModelMapper modelMapper) {
        this.boMonRepository = boMonRepository;
        this.modelMapper = modelMapper;

        TypeMap<BoMon, BoMonDto> propertyMapper = modelMapper.createTypeMap(BoMon.class, BoMonDto.class);

    }

    @Override
    public List<BoMonDto> getBoMon() {
        return boMonRepository.findAll().stream()
                .map(i -> modelMapper.map(i, BoMonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BoMon addBoMon(BoMon boMon) {
        return boMonRepository.save(boMon);
    }

    @Override
    public BoMon getBoMonCanTim(Integer id) {
        return boMonRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bo mon not found with id = " + id));
    }

    @Override
    public BoMon updateBoMon(BoMon boMon, Integer id) {

        BoMon exitstingBoMon = boMonRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bo mon not found with id = " + id));

        exitstingBoMon.setMaBoMon(boMon.getMaBoMon());
        exitstingBoMon.setTenBoMon(boMon.getTenBoMon());
        exitstingBoMon.setTrungBoMon(boMon.getTrungBoMon());
        exitstingBoMon.setSoThanhVien(boMon.getSoThanhVien());
        exitstingBoMon.setNgayThanhLap(boMon.getNgayThanhLap());
        exitstingBoMon.setTrangThai(boMon.getTrangThai());

        return boMonRepository.save(exitstingBoMon);
    }

    @Override
    public void deleteBoMon(Integer id) {

        BoMon exitstingBoMon = boMonRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diem not found with id = " + id));

        boMonRepository.delete(exitstingBoMon);
    }
}
