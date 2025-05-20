package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.GiaoViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiaoViecRepo extends JpaRepository<GiaoViec, Integer> {
}
