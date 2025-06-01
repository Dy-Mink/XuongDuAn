package com.example.quanlyxuong.service;

import com.example.quanlyxuong.dto.BoMonDto;
import com.example.quanlyxuong.entity.BoMon;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoMonService {
    List<BoMonDto> getBoMon();

    BoMon  addBoMon(BoMon boMon);

    BoMon getBoMonCanTim(Integer id);

    BoMon updateBoMon(BoMon boMon, Integer id);

    void deleteBoMon(Integer id);

    List<BoMonDto> searchAllFields(String keyword);

    Page<BoMon> getBoMonPage(int page, int size);

//    void exportToExcel(MultipartFile file);
//
//    void importExcel(MultipartFile file) throws Exception;
}
