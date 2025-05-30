package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.ChuyenNganh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuyenNganhRepository extends JpaRepository<ChuyenNganh, Integer> {
  }