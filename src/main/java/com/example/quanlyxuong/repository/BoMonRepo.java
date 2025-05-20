package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.BoMon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoMonRepo extends JpaRepository<BoMon, Integer> {
}
