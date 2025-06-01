package com.example.quanlyxuong.controller;

import com.example.quanlyxuong.dto.BoMonDto;
import com.example.quanlyxuong.entity.BoMon;
import com.example.quanlyxuong.exception.ResourceNotFoundException;
import com.example.quanlyxuong.service.BoMonService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.quanlyxuong.excel.ExcelExporter;
import org.springframework.http.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequestMapping("/admin/boMon")
@RequiredArgsConstructor
public class HomeController {

    private final BoMonService boMonService;

//    @GetMapping("/export")
//    public void exportToExcel(HttpServletResponse response) {
//        try {
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            String fileName = "Danh_sach_bo_mon.xlsx";
//            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//            boMonService.exportToExcel(response);
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Có thể ghi log hoặc trả response lỗi nếu cần
//        }
//    }
//
//
//
//    @GetMapping("/import")
//    public String showImportPage() {
//        return "/Admin/quanLyBoMon";
//    }
//
//    @PostMapping("/import")
//    public String importExcel(@RequestParam("file") MultipartFile file, Model model) {
//        try {
//            boMonService.importExcel(file);
//            model.addAttribute("message", "Import thành công!");
//        } catch (Exception e) {
//            model.addAttribute("message", "Lỗi khi import: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return "/Admin/quanLyBoMon";
//    }

    @GetMapping("/hien-thi")
    public String getBoMon(Model model) {
        List<BoMonDto> boMons = boMonService.getBoMon();
        model.addAttribute("boMons", boMons);
        return "/Admin/quanLyBoMon";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBoMon(@RequestBody @Valid BoMon boMon, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            BoMon saved = boMonService.addBoMon(boMon);
            return ResponseEntity.ok("Thêm bộ môn thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<BoMon> getBoMonCanTim(@PathVariable("id") Integer id) {

        BoMon boMon = boMonService.getBoMonCanTim(id);

        return ResponseEntity.ok(boMon);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateBoMon(@Valid @RequestBody BoMon boMon, @PathVariable("id") Integer id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            boMonService.updateBoMon(boMon, id);
            return ResponseEntity.ok("Cập nhật thành công");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteBoMon(@PathVariable("id") Integer id) {

        boMonService.deleteBoMon(id);

        return ResponseEntity.ok("Xóa thành công");
    }

    @GetMapping("/search")
    public ResponseEntity<List<BoMonDto>> searchBoMon(@RequestParam("keyword") String keyword) {
        List<BoMonDto> results = boMonService.searchAllFields(keyword);
        return ResponseEntity.ok(results);
    }

    @GetMapping
    public String getAllBoMon(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 5;

        Page<BoMon> boMons = boMonService.getBoMonPage(page, pageSize);
        model.addAttribute("boMons", boMons.getContent());
        model.addAttribute("currentPage", boMons.getNumber());
        model.addAttribute("totalPages", boMons.getTotalPages());
        return "/Admin/quanLyBoMon";
    }


}
