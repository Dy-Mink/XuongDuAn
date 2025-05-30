package com.example.quanlyxuong.Service.Impl;

import com.example.quanlyxuong.Service.CoSoService;
import com.example.quanlyxuong.entity.CoSo;
import com.example.quanlyxuong.repository.CoSoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CoSoServiceImpl implements CoSoService {
    @Autowired
    private CoSoRepository coSoRepository;

    @Override
    public boolean isMaCoSoDaTonTai(String maCoSo) {
        return coSoRepository.existsByMaCoSo(maCoSo);
    }

//    @Override
//    public Page<CoSo> getAllCoSo(int pageNo, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//        return coSoRepository.findAll(pageable);
//    }
//    @Override
//    public Page<CoSo> search(String tenCoSo, Boolean trangThai, int pageNo, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//        if (tenCoSo != null && trangThai != null) {
//            return coSoRepository.findByTenCoSoContainingAndTrangThai(tenCoSo, trangThai, pageable);
//        } else if (tenCoSo != null) {
//            return coSoRepository.findByTenCoSoContaining(tenCoSo, pageable);
//        } else if (trangThai != null) {
//            return coSoRepository.findByTrangThai(trangThai, pageable);
//        } else {
//            return coSoRepository.findAll(pageable);
//        }
//    }

}
