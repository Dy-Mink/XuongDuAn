package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.PhanCong;
import com.example.quanlyxuong.entity.PhanCongId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhanCongRepository extends JpaRepository<PhanCong, Integer> {
}