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
@Table(name = "cong_viec", schema = "dbo")
public class CongViec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cong_viec", nullable = false)
    private Integer id;

    @Column(name = "id_phan_cong")
    private Integer idPhanCong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_danh_sach_cong_viec")
    private DanhSachCongViec idDanhSachCongViec;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ten_cong_viec")
    private String tenCongViec;

    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    @Column(name = "thoi_gian")
    private Double thoiGian;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "ngay_update")
    private LocalDate ngayUpdate;

}