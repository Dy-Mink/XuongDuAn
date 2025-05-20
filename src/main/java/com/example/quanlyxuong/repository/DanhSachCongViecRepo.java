package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.DanhSachCongViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhSachCongViecRepo extends JpaRepository<DanhSachCongViec, Integer> {
}
