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
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @Column(name = "id_nguoi_dung", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "ma_nguoi_dung", length = 50)
    private String maNguoiDung;

    @Size(max = 100)
    @Nationalized
    @Column(name = "ho_ten", length = 100)
    private String hoTen;

    @Size(max = 50)
    @Column(name = "ten_dang_nhap", length = 50)
    private String tenDangNhap;

    @Size(max = 100)
    @Column(name = "email_pt", length = 100)
    private String emailPt;

    @Size(max = 100)
    @Column(name = "email_fe", length = 100)
    private String emailFe;

    @Size(max = 20)
    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @Size(max = 255)
    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_update")
    private LocalDate ngayUpdate;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}