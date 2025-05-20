package com.example.quanlyxuong.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bo_mon_co_so")
public class BoMonCoSo {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_bo_mon", nullable = false)
    private BoMon idBoMon;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_co_so", nullable = false)
    private com.example.quanlyxuong.entity.CoSo idCoSo;

}