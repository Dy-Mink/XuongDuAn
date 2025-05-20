package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.LoaiDuAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiDuAnRepository extends JpaRepository<LoaiDuAn, Integer> {
}