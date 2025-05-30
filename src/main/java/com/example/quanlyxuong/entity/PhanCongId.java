package com.example.quanlyxuong.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PhanCongId implements java.io.Serializable {
    private static final long serialVersionUID = -115466175272507295L;
    @NotNull
    @Column(name = "id_danh_sach_cong_viec", nullable = false)
    private Integer idDanhSachCongViec;

    @NotNull
    @Column(name = "id_nguoi_dung", nullable = false)
    private Integer idNguoiDung;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PhanCongId entity = (PhanCongId) o;
        return Objects.equals(this.idDanhSachCongViec, entity.idDanhSachCongViec) &&
                Objects.equals(this.idNguoiDung, entity.idNguoiDung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDanhSachCongViec, idNguoiDung);
    }

}