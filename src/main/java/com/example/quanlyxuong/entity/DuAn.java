package com.example.quanlyxuong.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "du_an", schema = "dbo")
public class DuAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bo_mon")
    private BoMon idBoMon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_co_so")
    private CoSo idCoSo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chuyen_nganh")
    private ChuyenNganh idChuyenNganh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_loai_du_an")
    private LoaiDuAn idLoaiDuAn;

    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc_du_kien")
    private LocalDate ngayKetThucDuKien;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}