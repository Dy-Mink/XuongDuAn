package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.entity.BoMonCoSo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoMonCoSoRepo extends JpaRepository<BoMonCoSo, Integer> {
}
