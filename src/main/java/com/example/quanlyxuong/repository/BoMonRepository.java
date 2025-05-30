package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.BoMon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoMonRepository extends JpaRepository<BoMon, Integer> {
}