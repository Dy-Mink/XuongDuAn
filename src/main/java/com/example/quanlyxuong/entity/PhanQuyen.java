package com.example.quanlyxuong.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "phan_quyen")
public class PhanQuyen {
    @EmbeddedId
    private PhanQuyenId id;

    @MapsId("idNguoiDung")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_nguoi_dung", nullable = false)
    private NguoiDung idNguoiDung;

    @MapsId("idVaiTro")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vai_tro", nullable = false)
    private VaiTro idVaiTro;

}