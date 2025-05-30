package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.DuAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuAnRepository extends JpaRepository<DuAn, Integer> {
  }