package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaiTroRepo extends JpaRepository<VaiTro, Integer> {
}
