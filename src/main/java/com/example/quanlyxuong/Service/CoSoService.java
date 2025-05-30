package com.example.quanlyxuong.Service;

import com.example.quanlyxuong.entity.CoSo;
import org.springframework.data.domain.Page;

public interface CoSoService {

    public boolean isMaCoSoDaTonTai(String maCoSo);

//    Page<CoSo> getAllCoSo(int pageNo, int pageSize); // thêm dòng này
//
//    Page<CoSo> search(String tenCoSo, Boolean trangThai, int pageNo, int pageSize);

}
