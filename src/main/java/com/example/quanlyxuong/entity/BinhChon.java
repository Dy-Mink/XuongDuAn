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
@Table(name = "binh_chon")
public class BinhChon {
    @EmbeddedId
    private BinhChonId id;

    @MapsId("idCongViec")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cong_viec", nullable = false)
    private CongViec idCongViec;

    @MapsId("idNguoiDung")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_nguoi_dung", nullable = false)
    private NguoiDung idNguoiDung;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ten_cong_viec")
    private String tenCongViec;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Size(max = 255)
    @Nationalized
    @Column(name = "du_lieu_binh_chon")
    private String duLieuBinhChon;

}