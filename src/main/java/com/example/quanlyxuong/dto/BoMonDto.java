package com.example.quanlyxuong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoMonDto {
    private Integer id;

    private String maBoMon;

    private String tenBoMon;

    private String trungBoMon;

    private Integer soThanhVien;

    private LocalDate ngayThanhLap;

    private Boolean trangThai;

}
