package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.CoSo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoSoRepo extends JpaRepository<CoSo, Integer> {
}
