package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.ThanhVienDuAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThanhVienDuAnRepository extends JpaRepository<ThanhVienDuAn, Integer> {
  }