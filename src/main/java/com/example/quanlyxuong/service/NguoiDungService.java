package com.example.quanlyxuong.service;

import com.example.quanlyxuong.entity.NguoiDung;
import com.example.quanlyxuong.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NguoiDungService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public List<NguoiDung> getAll() {
        return nguoiDungRepository.findAll();
    }
}
