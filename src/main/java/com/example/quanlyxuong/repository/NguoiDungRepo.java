package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDungRepo extends JpaRepository<NguoiDung, Integer> {
}
