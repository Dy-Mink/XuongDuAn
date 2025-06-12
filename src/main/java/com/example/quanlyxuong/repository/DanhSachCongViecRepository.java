package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.DanhSachCongViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DanhSachCongViecRepository extends JpaRepository<DanhSachCongViec, Integer> {
    Optional<DanhSachCongViec> findByTenDanhSachCongViec(String tenDanhSachCongViec);
}