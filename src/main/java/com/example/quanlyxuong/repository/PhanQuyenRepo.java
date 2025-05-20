package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.PhanQuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhanQuyenRepo extends JpaRepository<PhanQuyen, Integer> {
}
