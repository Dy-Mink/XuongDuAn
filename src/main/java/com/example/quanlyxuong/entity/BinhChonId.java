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
public class BinhChonId implements java.io.Serializable {
    private static final long serialVersionUID = -2628102000611987218L;
    @NotNull
    @Column(name = "id_cong_viec", nullable = false)
    private Integer idCongViec;

    @NotNull
    @Column(name = "id_nguoi_dung", nullable = false)
    private Integer idNguoiDung;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BinhChonId entity = (BinhChonId) o;
        return Objects.equals(this.idCongViec, entity.idCongViec) &&
                Objects.equals(this.idNguoiDung, entity.idNguoiDung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCongViec, idNguoiDung);
    }

}