package com.example.quanlyxuong.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "giai_doan", schema = "dbo")
public class GiaiDoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_giai_doan", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_du_an")
    private DuAn idDuAn;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ten_giai_doan")
    private String tenGiaiDoan;

    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    @Column(name = "ngay_update")
    private LocalDate ngayUpdate;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Override
    public boolean equals(Object o) {
        // Kiểm tra xem đối tượng có phải là chính nó không
        if (this == o) return true;
        // Kiểm tra xem đối tượng có null hoặc không cùng loại không
        if (o == null || getClass() != o.getClass()) return false;
        // Ép kiểu đối tượng sang GiaiDoan
        GiaiDoan giaiDoan = (GiaiDoan) o;
        // So sánh dựa trên tenGiaiDoan để định nghĩa tính duy nhất
        // Bạn muốn "Giai đoạn 1" (từ đối tượng A) == "Giai đoạn 1" (từ đối tượng B)
        // ngay cả khi ID của chúng khác nhau.
        return Objects.equals(tenGiaiDoan, giaiDoan.tenGiaiDoan);
    }

    @Override
    public int hashCode() {
        // Hash code cũng phải được tạo dựa trên các thuộc tính được sử dụng trong equals()
        // Nếu equals() chỉ dùng tenGiaiDoan, thì hashCode() cũng chỉ dùng tenGiaiDoan
        return Objects.hash(tenGiaiDoan);
    }
    // --- KẾT THÚC: CÁC PHƯƠNG THỨC CẦN OVERRIDE ---

    // Bạn có thể thêm toString() để debug dễ hơn
    @Override
    public String toString() {
        return "GiaiDoan{" +
                "id=" + id +
                ", tenGiaiDoan='" + tenGiaiDoan + '\'' +
                '}';
    }

}