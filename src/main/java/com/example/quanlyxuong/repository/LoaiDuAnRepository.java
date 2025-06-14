package com.example.quanlyxuong.repository;

import com.example.quanlyxuong.dto.TheLoaiDto;
import com.example.quanlyxuong.entity.LoaiDuAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LoaiDuAnRepository extends JpaRepository<LoaiDuAn, Integer> {

//    List<LoaiDuAn> findByTenContainingIgnoreCase(String tenLoaiDuAn);

    @Query("SELECT l FROM LoaiDuAn l WHERE l.tenLoaiDuAn LIKE %:tenLoaiDuAn%")
    List<LoaiDuAn> findByTenContainingIgnoreCase(@Param("tenLoaiDuAn") String tenLoaiDuAn);
}