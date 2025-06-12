package com.example.quanlyxuong.service;

import com.example.quanlyxuong.entity.PhanCong;
import com.example.quanlyxuong.repository.PhanCongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhanCongService {
    @Autowired
    private PhanCongRepository phanCongRepository;

    public List<PhanCong> getAll() {
        return phanCongRepository.findAll();
    }
}
