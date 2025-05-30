package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.CongViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongViecRepository extends JpaRepository<CongViec, Integer> {
}