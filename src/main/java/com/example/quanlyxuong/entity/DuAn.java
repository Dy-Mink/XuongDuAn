package com.example.quanlyxuong.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "du_an")
public class DuAn {
    @Id
    @Column(name = "id_du_an", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "ma_du_an", length = 50)
    private String maDuAn;

    @Size(max = 100)
    @Nationalized
    @Column(name = "ten_du_an", length = 100)
    private String tenDuAn;

    @Column(name = "ngay_update")
    private LocalDate ngayUpdate;

    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc_du_kien")
    private LocalDate ngayKetThucDuKien;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}