package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.BinhChon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinhTronRepo extends JpaRepository<BinhChon, Integer> {
}
