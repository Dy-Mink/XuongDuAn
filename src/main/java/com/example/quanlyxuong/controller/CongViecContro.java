package com.example.quanlyxuong.controller;

import com.example.quanlyxuong.entity.CongViec;
import com.example.quanlyxuong.entity.GiaiDoan;
import com.example.quanlyxuong.repository.PhanCongRepository;
import com.example.quanlyxuong.service.CongViecService;
import com.example.quanlyxuong.service.DanhSachCongViecService;
import com.example.quanlyxuong.service.GiaiDoanService;
import com.example.quanlyxuong.service.NguoiDungService;
import com.example.quanlyxuong.service.PhanCongService;
import com.example.quanlyxuong.service.CongViecExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequestMapping("/congviec")
@Controller
public class CongViecContro {
    private static final Pattern NUMBER_EXTRACT_PATTERN = Pattern.compile("(\\d+)");

    @Autowired
    private CongViecService congViecService;

    @Autowired
    private GiaiDoanService giaoDoanService;

    @Autowired
    private DanhSachCongViecService danhSachCongViecService;

    @Autowired
    private PhanCongService phanCongService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private CongViecExcelService congViecExcelService;

    @GetMapping // Giả sử đây là endpoint của bạn
    public String getTheLoaiPage(
            @RequestParam(defaultValue = "0") Optional<Integer> page,
            @RequestParam(defaultValue = "5") Optional<Integer> size,
            @RequestParam(name = "taskName", required = false) String taskName,
            @RequestParam(name = "priority", required = false) String priority,
            @RequestParam(name = "taskStatus", required = false) String taskStatus,
            Model model) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);

        Boolean trangThaiBoolean = null;
        if (taskStatus != null && !taskStatus.isEmpty()) {
            if ("1".equals(taskStatus)) { // Chưa hoàn thành
                trangThaiBoolean = true;
            } else if ("2".equals(taskStatus)) { // Đã hoàn thành
                trangThaiBoolean = false;
            }
        }

        Integer priorityInt = null;
        if (priority != null && !priority.isEmpty()) {
            try {
                priorityInt = Integer.parseInt(priority);
            } catch (NumberFormatException e) {
                // Handle invalid priority value if necessary
            }
        }

        Page<CongViec> congViecPage = congViecService.findAll(PageRequest.of(currentPage, pageSize), taskName, trangThaiBoolean, priorityInt);

        int totalPages = congViecPage.getTotalPages();
        if (totalPages == 0) {
            totalPages = 1;
        }
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("congViec", congViecPage.getContent());
        model.addAttribute("currentTaskName", taskName);
        model.addAttribute("currentTaskStatus", taskStatus);
        model.addAttribute("currentPriority", priority);

        List<GiaiDoan> allGiaiDoan = giaoDoanService.getAll();

        List<GiaiDoan> filteredAndSortedGiaiDoan = allGiaiDoan.stream()
                .filter(gd -> {
                    String tenGiaiDoan = gd.getTenGiaiDoan();
                    if (tenGiaiDoan == null || tenGiaiDoan.trim().isEmpty()) {
                        System.err.println("Cảnh báo: 'tenGiaiDoan' trống hoặc null, bỏ qua.");
                        return false;
                    }

                    tenGiaiDoan = tenGiaiDoan.trim();

                    Matcher matcher = NUMBER_EXTRACT_PATTERN.matcher(tenGiaiDoan);
                    if (matcher.find()) {
                        try {
                            int phaseNumber = Integer.parseInt(matcher.group(1));
                            // Vẫn giữ điều kiện lọc >= 1 và <= 3
                            return phaseNumber >= 1 && phaseNumber <= 3;
                        } catch (NumberFormatException e) {
                            System.err.println("Cảnh báo: Lỗi phân tích số từ chuỗi đã tìm thấy: '" + matcher.group(1) + "'. Chuỗi đầy đủ: '" + tenGiaiDoan + "'");
                            return false;
                        }
                    } else {
                        System.err.println("Cảnh báo: 'tenGiaiDoan' không chứa số nào: '" + tenGiaiDoan + "', bỏ qua.");
                        return false;
                    }
                })
                .distinct() // <--- THÊM ĐOẠN NÀY ĐỂ LOẠI BỎ CÁC ĐỐI TƯỢNG GIAI ĐOẠN TRÙNG LẶP
                //      Để distinct() hoạt động đúng, lớp GiaiDoan cần override equals() và hashCode()
                .sorted(Comparator.comparingInt(gd -> { // <--- THÊM ĐOẠN NÀY ĐỂ SẮP XẾP
                    // Cần trích xuất số để sắp xếp theo thứ tự số
                    Matcher matcher = NUMBER_EXTRACT_PATTERN.matcher(gd.getTenGiaiDoan().trim());
                    if (matcher.find()) {
                        return Integer.parseInt(matcher.group(1));
                    }
                    return 0; // Trả về 0 hoặc một giá trị mặc định nếu không tìm thấy số (nên được loại bỏ bởi filter trước đó)
                }))
                .collect(Collectors.toList());

        model.addAttribute("giaiDoan", filteredAndSortedGiaiDoan);
        // --- KẾT THÚC PHẦN CHỈNH SỬA ---
        model.addAttribute("danhSachCongViec", danhSachCongViecService.getAll());
        model.addAttribute("phanCong", phanCongService.getAll());
        model.addAttribute("nguoiDung", nguoiDungService.getAll());

        // Dữ liệu của trang hiện tại
        if (!model.containsAttribute("congViecnew")) {
            model.addAttribute("congViecnew", new CongViec());
        }
        return "QuanLyDuAn/CongViec/congviec";
    }

    @PostMapping("/addCongViec")
    public String addCongViec(@ModelAttribute CongViec congViec, RedirectAttributes redirectAttributes) {
        if (congViec.getTenCongViec() == null || congViec.getTenCongViec().trim().isEmpty()) {
            congViec.setTenCongViec("Không có tiêu đề");
        } else {
            // Trim if it's not empty, to remove leading/trailing spaces
            congViec.setTenCongViec(congViec.getTenCongViec().trim());
        }
        congViec.setTrangThai(true); // Hoặc một trạng thái mặc định khác

        // Lưu công việc vào cơ sở dữ liệu
        congViecService.save(congViec);

        redirectAttributes.addFlashAttribute("message", "Thêm công việc thành công!");
        return "redirect:/congviec";
    }

    @GetMapping("/delete/{id}")
    public String deleteCongViec(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            congViecService.deleteById(id); // Xóa công việc bằng ID
            redirectAttributes.addFlashAttribute("message", "Xóa công việc thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Không thể xóa công việc: " + e.getMessage());
        }
        return "redirect:/congviec";
    }

    @PostMapping("/import")
    public String importCongViec(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            congViecExcelService.importCongViecFromExcel(file);
            redirectAttributes.addFlashAttribute("message", "Import công việc thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Import công việc thất bại: " + e.getMessage());
        }
        return "redirect:/congviec";
    }

    @GetMapping("/download-template")
    public void downloadTemplate(HttpServletResponse response) {
        try {
            congViecExcelService.exportCongViecTemplate(response);
        } catch (Exception e) {
            // Xử lý lỗi nếu cần
            e.printStackTrace();
        }
    }

    @GetMapping("/history")
    public String viewImportHistory(Model model) {
        // TODO: Implement logic to fetch and display import history
        model.addAttribute("message", "Chức năng lịch sử đang được phát triển.");
        return "QuanLyDuAn/CongViec/congviec-history"; // Tạo một view mới cho lịch sử nếu cần
    }

    @GetMapping("/download-import-errors")
    public void downloadImportErrors(HttpServletResponse response) {
        try {
            congViecExcelService.exportImportErrors(response);
        } catch (Exception e) {
            // Xử lý lỗi nếu cần
            e.printStackTrace();
        }
    }
}
