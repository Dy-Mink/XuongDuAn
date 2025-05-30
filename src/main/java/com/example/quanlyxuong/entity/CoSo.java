package com.example.quanlyxuong.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "co_so")
public class CoSo {
    @Id
    @Column(name = "id_co_so", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "ma_co_so", length = 50)
    @NotBlank(message = "Mã không được để trống")
    private String maCoSo;

    @Size(max = 100)
    @Nationalized
    @Column(name = "ten_co_so", length = 100)
    @NotBlank(message = "Tên không được để trống")
    private String tenCoSo;

    @Size(max = 255)
    @Nationalized
    @Column(name = "dia_chi")
    @NotBlank(message = "Địa chỉ không được để trống")
    private String diaChi;

    @Size(max = 20)
    @Column(name = "so_dien_thoai", length = 20)
    @Pattern(regexp = "^(0|\\+84)(\\d{9,10})$", message = "Số điện thoại không hợp lệ")
    @NotBlank(message = "SDT không được để trống")
    private String soDienThoai;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    @Email(message = "Email không đúng định dạng")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @Column(name = "ngay_thanh_lap")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày thành lập không được để trống")
    private LocalDate ngayThanhLap;

    @Column(name = "ngay_tao")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày tạo không được để trống")
    private LocalDate ngayTao;

    @Column(name = "ngay_update")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày Update không được để trống")
    private LocalDate ngayUpdate;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}