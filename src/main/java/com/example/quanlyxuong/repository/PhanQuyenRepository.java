package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.PhanQuyen;
import com.example.quanlyxuong.entity.PhanQuyenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhanQuyenRepository extends JpaRepository<PhanQuyen, Integer> {
}