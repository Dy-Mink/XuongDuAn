package com.example.quanlyxuong.controller;

import com.example.quanlyxuong.dto.BoMonDto;
import com.example.quanlyxuong.entity.BoMon;
import com.example.quanlyxuong.repository.BoMonRepository;
import com.example.quanlyxuong.service.BoMonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String addBoMon(@RequestBody @Valid BoMon boMon, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        boMonService.addBoMon(boMon);
        return "add success";
    }

    @GetMapping("{id}")
    public ResponseEntity<BoMon> getBoMonCanTim(@PathVariable("id") Integer id) {

        BoMon boMon = boMonService.getBoMonCanTim(id);

        return ResponseEntity.ok(boMon);
    }

    @PutMapping("{id}")
    public ResponseEntity<BoMon> updateBoMon(@Valid @RequestBody BoMon boMon, @PathVariable("id") Integer id) {

        BoMon updateBoMon = boMonService.updateBoMon(boMon, id);

        return ResponseEntity.ok(updateBoMon);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDiem(@PathVariable("id") Integer id) {

        boMonService.deleteBoMon(id);

        return ResponseEntity.ok("Deleted diem with id =" +id);
    }
}
