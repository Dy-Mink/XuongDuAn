package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.ChuyenNganh;
import com.example.quanlyxuong.entity.ChuyenNganhBoMonCoSo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuyenNganhBoMonCoSoRepository extends JpaRepository<ChuyenNganhBoMonCoSo, Integer> {
}