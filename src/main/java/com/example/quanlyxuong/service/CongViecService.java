package com.example.quanlyxuong.service;

import com.example.quanlyxuong.entity.CongViec;
import com.example.quanlyxuong.repository.CongViecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongViecService {
    @Autowired
    private CongViecRepository congViecRepository;

    public List<CongViec> getAll() {
        return congViecRepository.findAll();
    }

    public void save(CongViec congViec) {
        congViecRepository.save(congViec);
    }

    public Page<CongViec> findAll(PageRequest pageable, String tenCongViec, Boolean trangThai, Integer priority) {
        if (tenCongViec != null && !tenCongViec.isEmpty() && trangThai != null && priority != null) {
            return congViecRepository.findByTenCongViecContainingIgnoreCaseAndTrangThaiAndDoUuTien(tenCongViec, trangThai, priority, pageable);
        } else if (tenCongViec != null && !tenCongViec.isEmpty() && trangThai != null) {
            return congViecRepository.findByTenCongViecContainingIgnoreCaseAndTrangThai(tenCongViec, trangThai, pageable);
        } else if (tenCongViec != null && !tenCongViec.isEmpty() && priority != null) {
            return congViecRepository.findByTenCongViecContainingIgnoreCaseAndDoUuTien(tenCongViec, priority, pageable);
        } else if (trangThai != null && priority != null) {
            return congViecRepository.findByTrangThaiAndDoUuTien(trangThai, priority, pageable);
        } else if (tenCongViec != null && !tenCongViec.isEmpty()) {
            return congViecRepository.findByTenCongViecContainingIgnoreCase(tenCongViec, pageable);
        } else if (trangThai != null) {
            return congViecRepository.findByTrangThai(trangThai, pageable);
        } else if (priority != null) {
            return congViecRepository.findByDoUuTien(priority, pageable);
        } else {
            return congViecRepository.findAll(pageable);
        }
    }

    public void deleteById(Integer id) {
        congViecRepository.deleteById(id);
    }

    public CongViec findById(Integer id) {
        return congViecRepository.findById(id).get();
    }
}
