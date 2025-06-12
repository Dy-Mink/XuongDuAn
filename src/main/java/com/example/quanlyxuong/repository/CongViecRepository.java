package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.CongViec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongViecRepository extends JpaRepository<CongViec, Integer> {
    Page<CongViec> findByTenCongViecContainingIgnoreCaseAndTrangThai(String tenCongViec, Boolean trangThai, Pageable pageable);
    Page<CongViec> findByTenCongViecContainingIgnoreCase(String tenCongViec, Pageable pageable);
    Page<CongViec> findByTrangThai(Boolean trangThai, Pageable pageable);
    Page<CongViec> findByTenCongViecContainingIgnoreCaseAndTrangThaiAndDoUuTien(String tenCongViec, Boolean trangThai, Integer doUuTien, Pageable pageable);
    Page<CongViec> findByTenCongViecContainingIgnoreCaseAndDoUuTien(String tenCongViec, Integer doUuTien, Pageable pageable);
    Page<CongViec> findByTrangThaiAndDoUuTien(Boolean trangThai, Integer doUuTien, Pageable pageable);
    Page<CongViec> findByDoUuTien(Integer doUuTien, Pageable pageable);
}