package com.example.quanlyxuong.service;

import com.example.quanlyxuong.entity.DanhSachCongViec;
import com.example.quanlyxuong.repository.CongViecRepository;
import com.example.quanlyxuong.repository.DanhSachCongViecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhSachCongViecService {
    @Autowired
    private DanhSachCongViecRepository danhSachCongViecRepository;

    public List<DanhSachCongViec> getAll() {
        return danhSachCongViecRepository.findAll();
    }
}
