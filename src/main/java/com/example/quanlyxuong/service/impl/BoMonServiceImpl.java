package com.example.quanlyxuong.service.impl;

import com.example.quanlyxuong.dto.BoMonDto;
import com.example.quanlyxuong.entity.BoMon;
import com.example.quanlyxuong.exception.ResourceNotFoundException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import com.example.quanlyxuong.repository.BoMonRepository;
import com.example.quanlyxuong.service.BoMonService;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class BoMonServiceImpl  implements BoMonService {
    private final BoMonRepository boMonRepository;
    private final ModelMapper modelMapper;

    public BoMonServiceImpl(BoMonRepository boMonRepository, ModelMapper modelMapper) {
        this.boMonRepository = boMonRepository;
        this.modelMapper = modelMapper;

        TypeMap<BoMon, BoMonDto> propertyMapper = modelMapper.createTypeMap(BoMon.class, BoMonDto.class);

    }

    @Override
    public List<BoMonDto> getBoMon() {
        return boMonRepository.findAll().stream()
                .map(i -> modelMapper.map(i, BoMonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BoMon addBoMon(BoMon boMon) {
        if (boMonRepository.existsByMaBoMon(boMon.getMaBoMon())) {
            throw new IllegalArgumentException("Mã bộ môn đã tồn tại!");
        }
        return boMonRepository.save(boMon);
    }

    @Override
    public BoMon getBoMonCanTim(Integer id) {
        return boMonRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bo mon not found with id = " + id));
    }

    @Override
    public BoMon updateBoMon(BoMon boMon, Integer id) {

        BoMon exitstingBoMon = boMonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bộ môn không tồn tại với id = " + id));

        // Nếu mã bộ môn mới khác mã hiện tại thì kiểm tra trùng
        if (!exitstingBoMon.getMaBoMon().equals(boMon.getMaBoMon()) &&
                boMonRepository.existsByMaBoMon(boMon.getMaBoMon())) {
            throw new IllegalArgumentException("Mã bộ môn đã tồn tại!");
        }

        exitstingBoMon.setMaBoMon(boMon.getMaBoMon());
        exitstingBoMon.setTenBoMon(boMon.getTenBoMon());
        exitstingBoMon.setTrungBoMon(boMon.getTrungBoMon());
        exitstingBoMon.setSoThanhVien(boMon.getSoThanhVien());
        exitstingBoMon.setNgayThanhLap(boMon.getNgayThanhLap());
        exitstingBoMon.setTrangThai(boMon.getTrangThai());

        return boMonRepository.save(exitstingBoMon);
    }

    @Override
    public void deleteBoMon(Integer id) {

        BoMon exitstingBoMon = boMonRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diem not found with id = " + id));

        boMonRepository.delete(exitstingBoMon);
    }

    @Override
    public List<BoMonDto> searchAllFields(String keyword) {
        return boMonRepository.searchAllFields(keyword).stream()
                .map(b -> modelMapper.map(b, BoMonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<BoMon> getBoMonPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return boMonRepository.findAll(pageable);
    }

//    @Override
//    public void exportToExcel(HttpServletResponse response) throws IOException {
//        List<BoMon> listBoMon = boMonRepository.findAll();
//
//        try (XSSFWorkbook workbook = new XSSFWorkbook();
//             ServletOutputStream outputStream = response.getOutputStream()) {
//
//            Sheet sheet = workbook.createSheet("Bộ Môn");
//            Row headerRow = sheet.createRow(0);
//            String[] columns = {"Mã bộ môn", "Tên bộ môn", "Trưởng bộ môn", "Số thành viên", "Ngày thành lập", "Trạng thái"};
//
//            for (int i = 0; i < columns.length; i++) {
//                Cell cell = headerRow.createCell(i);
//                cell.setCellValue(columns[i]);
//            }
//
//            int rowIdx = 1;
//            for (BoMon bm : listBoMon) {
//                Row row = sheet.createRow(rowIdx++);
//                row.createCell(0).setCellValue(bm.getMaBoMon());
//                row.createCell(1).setCellValue(bm.getTenBoMon());
//                row.createCell(2).setCellValue(bm.getTrungBoMon());
//                row.createCell(3).setCellValue(bm.getSoThanhVien());
//
//                if (bm.getNgayThanhLap() != null) {
//                    CreationHelper createHelper = workbook.getCreationHelper();
//                    CellStyle cellStyle = workbook.createCellStyle();
//                    cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
//                    Cell dateCell = row.createCell(4);
//                    dateCell.setCellValue(bm.getNgayThanhLap());
//                    dateCell.setCellStyle(cellStyle);
//                } else {
//                    row.createCell(4).setCellValue("");
//                }
//
//                row.createCell(5).setCellValue(bm.getTrangThai() ? "Hoạt động" : "Ngừng hoạt động");
//            }
//
//            for (int i = 0; i < columns.length; i++) {
//                sheet.autoSizeColumn(i);
//            }
//
//            workbook.write(outputStream);
//            outputStream.flush();
//        }
//    }
//
//    @Override
//    public void importExcel(MultipartFile file) throws Exception {
//        try (InputStream is = file.getInputStream()) {
//            Workbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rows = sheet.iterator();
//
//            boolean firstRow = true;
//            while (rows.hasNext()) {
//                Row currentRow = rows.next();
//                if (firstRow) {
//                    firstRow = false; // skip header row
//                    continue;
//                }
//
//                Cell maBoMonCell = currentRow.getCell(0);
//                if (maBoMonCell == null || maBoMonCell.getCellType() == CellType.BLANK ||
//                        maBoMonCell.getStringCellValue().trim().isEmpty()) {
//                    continue; // skip empty code rows
//                }
//
//                String maBoMon = maBoMonCell.getStringCellValue().trim();
//
//                if (boMonRepository.findByMaBoMon(maBoMon).isPresent()) {
//                    continue; // skip duplicates
//                }
//
//                BoMon boMon = new BoMon();
//                boMon.setMaBoMon(maBoMon);
//
//                Cell tenBoMonCell = currentRow.getCell(1);
//                boMon.setTenBoMon(tenBoMonCell != null && tenBoMonCell.getCellType() != CellType.BLANK
//                        ? tenBoMonCell.getStringCellValue() : "");
//
//                Cell trungBoMonCell = currentRow.getCell(2);
//                boMon.setTrungBoMon(trungBoMonCell != null && trungBoMonCell.getCellType() != CellType.BLANK
//                        ? trungBoMonCell.getStringCellValue() : "");
//
//                Cell soThanhVienCell = currentRow.getCell(3);
//                boMon.setSoThanhVien(soThanhVienCell != null && soThanhVienCell.getCellType() == CellType.NUMERIC
//                        ? (int) soThanhVienCell.getNumericCellValue() : 0);
//
//                Cell ngayThanhLapCell = currentRow.getCell(4);
//                if (ngayThanhLapCell != null && ngayThanhLapCell.getCellType() == CellType.NUMERIC) {
//                    LocalDate date = ngayThanhLapCell.getDateCellValue().toInstant()
//                            .atZone(ZoneId.systemDefault()).toLocalDate();
//                    boMon.setNgayThanhLap(date);
//                } else {
//                    boMon.setNgayThanhLap(null);
//                }
//
//                Cell trangThaiCell = currentRow.getCell(5);
//                if (trangThaiCell != null) {
//                    if (trangThaiCell.getCellType() == CellType.BOOLEAN) {
//                        boMon.setTrangThai(trangThaiCell.getBooleanCellValue());
//                    } else if (trangThaiCell.getCellType() == CellType.NUMERIC) {
//                        boMon.setTrangThai(trangThaiCell.getNumericCellValue() == 1);
//                    } else if (trangThaiCell.getCellType() == CellType.STRING) {
//                        boMon.setTrangThai(trangThaiCell.getStringCellValue().equalsIgnoreCase("true"));
//                    } else {
//                        boMon.setTrangThai(false);
//                    }
//                } else {
//                    boMon.setTrangThai(false);
//                }
//
//                boMonRepository.save(boMon);
//            }
//            workbook.close();
//        }
//    }

}
