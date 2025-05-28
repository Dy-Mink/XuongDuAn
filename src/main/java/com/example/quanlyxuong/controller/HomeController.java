package com.example.quanlyxuong.controller;

import com.example.quanlyxuong.dto.BoMonDto;
import com.example.quanlyxuong.entity.BoMon;
import com.example.quanlyxuong.exception.ResourceNotFoundException;
import com.example.quanlyxuong.repository.BoMonRepository;
import com.example.quanlyxuong.service.BoMonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/boMon")
@RequiredArgsConstructor
public class HomeController {

    private final BoMonService boMonService;

    @GetMapping
    public ResponseEntity<List<BoMonDto>> getBoMon() {
        List<BoMonDto> boMons = boMonService.getBoMon();
        return ResponseEntity.ok(boMons);
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

    @GetMapping("{id}")
    public ResponseEntity<BoMon> getBoMonCanTim(@PathVariable("id") Integer id) {

        BoMon boMon = boMonService.getBoMonCanTim(id);

        return ResponseEntity.ok(boMon);
    }

    @PutMapping("{id}")
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

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDiem(@PathVariable("id") Integer id) {

        boMonService.deleteBoMon(id);

        return ResponseEntity.ok("Deleted diem with id =" +id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BoMonDto>> searchBoMon(@RequestParam("keyword") String keyword) {
        List<BoMonDto> results = boMonService.searchAllFields(keyword);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<BoMonDto>> getAllBoMon(@RequestParam(defaultValue = "0") int page) {
        int size = 5;
        Page<BoMonDto> boMonPage = boMonService.getBoMonPage(page, size);
        return ResponseEntity.ok(boMonPage);
    }


}
