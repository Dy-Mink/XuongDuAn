package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.BinhChon;
import com.example.quanlyxuong.entity.BinhChonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinhChonRepository extends JpaRepository<BinhChon, Integer> {
}