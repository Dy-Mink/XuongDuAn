package com.example.quanlyxuong.service;

import com.example.quanlyxuong.dto.BoMonDto;
import com.example.quanlyxuong.entity.BoMon;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoMonService {
    List<BoMonDto> getBoMon();

    BoMon  addBoMon(BoMon boMon);

    BoMon getBoMonCanTim(Integer id);

    BoMon updateBoMon(BoMon boMon, Integer id);

    void deleteBoMon(Integer id);

    List<BoMonDto> searchAllFields(String keyword);

    Page<BoMonDto> getBoMonPage(int page, int size);

}
