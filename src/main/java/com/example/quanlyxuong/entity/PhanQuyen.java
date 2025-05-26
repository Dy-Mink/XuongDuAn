package com.example.quanlyxuong.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "phan_quyen", schema = "dbo")
public class PhanQuyen {
    @EmbeddedId
    private PhanQuyenId id;

    @MapsId("idNguoiDung")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_nguoi_dung", nullable = false)
    private NguoiDung idNguoiDung;

    @MapsId("idCoSo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_co_so", nullable = false)
    private CoSo idCoSo;

}