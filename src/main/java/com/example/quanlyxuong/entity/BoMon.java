package com.example.quanlyxuong.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "bo_mon", schema = "dbo")
public class BoMon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bo_mon", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "ma_bo_mon", length = 50)
    @NotBlank(message = "Mã bộ môn không được để trống")
    private String maBoMon;

    @Size(max = 100)
    @Nationalized
    @Column(name = "ten_bo_mon", length = 100)
    @NotBlank(message = "Tên bộ môn không được để trống")
    private String tenBoMon;

    @Size(max = 100)
    @Nationalized
    @Column(name = "trung_bo_mon", length = 100)
    @NotBlank(message = "Trưởng bộ môn không được để trống")
    private String trungBoMon;

    @Column(name = "so_thanh_vien")
    @NotNull(message = "Số thành viên không được để trống")
    private Integer soThanhVien;

    @Size(max = 255)
    @Nationalized
    @Column(name = "mo_ta_chuc_nang")
    private String moTaChucNang;

    @Column(name = "ngay_thanh_lap")
    @NotNull(message = "Ngày thành lập không được để trống")
    private LocalDate ngayThanhLap;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_update")
    private LocalDate ngayUpdate;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}