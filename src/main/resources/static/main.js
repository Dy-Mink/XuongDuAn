let selectedBoMonId = null;

function openPopup(title, data = null) {
    document.getElementById("popupTitle").innerText = title;
    selectedBoMonId = data?.id || null;

    document.getElementById("popupBoMonId").value = selectedBoMonId || "";
    document.getElementById("popupMaBoMon").value = data?.maBoMon || "";
    document.getElementById("popupTenBoMon").value = data?.tenBoMon || "";
    document.getElementById("popupTruongBoMon").value = data?.trungBoMon || "";
    document.getElementById("popupSoThanhVien").value = data?.soThanhVien || "";
    document.getElementById("popupNgayThanhLap").value = data?.ngayThanhLap || "";
    document.getElementById("popupTrangThai").value = data?.trangThai ? "true" : "false";

    document.getElementById("popup").style.display = "block";
    document.getElementById("overlay").style.display = "block"; // hiện overlay
}

function closePopup() {
    document.getElementById("popup").style.display = "none";
    document.getElementById("overlay").style.display = "none"; // ẩn overlay
    document.getElementById("popupBoMonId").value = "";
    selectedBoMonId = null;
}

function submitForm() {
    // Xóa lỗi cũ
    document.querySelectorAll('.error-message').forEach(el => el.innerText = "");

    let isValid = true;

    const maBoMon = document.getElementById("popupMaBoMon").value.trim();
    const tenBoMon = document.getElementById("popupTenBoMon").value.trim();
    const trungBoMon = document.getElementById("popupTruongBoMon").value.trim();
    const soThanhVien = document.getElementById("popupSoThanhVien").value.trim();
    const ngayThanhLap = document.getElementById("popupNgayThanhLap").value;

    if (!maBoMon) {
        document.getElementById("errorMaBoMon").innerText = "Mã bộ môn không được để trống";
        isValid = false;
    }

    if (!tenBoMon) {
        document.getElementById("errorTenBoMon").innerText = "Tên bộ môn không được để trống";
        isValid = false;
    }

    if (!trungBoMon) {
        document.getElementById("errorTruongBoMon").innerText = "Trưởng bộ môn không được để trống";
        isValid = false;
    }

    if (!soThanhVien || isNaN(soThanhVien) || parseInt(soThanhVien) < 0) {
        document.getElementById("errorSoThanhVien").innerText = "Phải là số hợp lệ lớn hơn 0";
        isValid = false;
    }

    if (!ngayThanhLap) {
        document.getElementById("errorNgayThanhLap").innerText = "Ngày không được để trống";
        isValid = false;
    }

    if (!isValid) return;

    const id = document.getElementById("popupBoMonId").value;

    const data = {
        maBoMon,
        tenBoMon,
        trungBoMon,
        soThanhVien: parseInt(soThanhVien),
        ngayThanhLap,
        trangThai: document.getElementById("popupTrangThai").value === "true"
    };

    const url = id ? `/admin/boMon/update/${id}` : `/admin/boMon/add`;

    fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(res => {
            if (!res.ok) throw new Error("Gửi dữ liệu thất bại!");
            return res.text();
        })
        .then(msg => {
            alert(msg);
            location.reload();
        })
        .catch(err => alert("Lỗi: " + err.message));
}


function confirmDelete(id) {
    if (confirm("Bạn có chắc chắn muốn xóa bộ môn này không?")) {
        fetch(`/admin/boMon/delete/${id}`)
            .then(res => {
                if (!res.ok) throw new Error("Xóa không thành công!");
                return res.text();
            })
            .then(msg => {
                alert(msg);
                location.reload();
            })
            .catch(err => alert("Lỗi: " + err.message));
    }
}

function showDetail(id) {
    fetch(`/admin/boMon/detail/${id}`)
        .then(res => res.json())
        .then(data => openPopup("Chi tiết Bộ môn", data))
        .catch(err => alert("Lỗi khi lấy chi tiết: " + err.message));
}

// -----------------------------------------------------------------------------
document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('tenBoMon');
    const tableBody = document.getElementById('boMonTableBody');

    if (!searchInput || !tableBody) return; // tránh lỗi nếu chưa có phần tử

    let debounceTimeout;
    searchInput.addEventListener('input', function () {
        clearTimeout(debounceTimeout);

        debounceTimeout = setTimeout(() => {
            const keyword = searchInput.value.trim();

            if (!keyword) {
                // Nếu bỏ trống tìm kiếm thì reload về danh sách mặc định
                window.location.href = '/admin/boMon';
                return;
            }

            fetch(`/admin/boMon/search?keyword=${encodeURIComponent(keyword)}`)
                .then(res => res.json())
                .then(data => {
                    tableBody.innerHTML = '';

                    if (data.length === 0) {
                        tableBody.innerHTML = `<tr><td colspan="8" class="text-center">Không tìm thấy kết quả</td></tr>`;
                        return;
                    }

                    data.forEach((boMon, index) => {
                        const tr = document.createElement('tr');

                        tr.innerHTML = `
                            <td>${index + 1}</td>
                            <td>${boMon.maBoMon}</td>
                            <td>${boMon.tenBoMon}</td>
                            <td>${boMon.trungBoMon}</td>
                            <td>${boMon.soThanhVien}</td>
                            <td>${boMon.ngayThanhLap}</td>
                            <td>${boMon.trangThai ? 'Hoạt động' : 'Ngừng hoạt động'}</td>
                            <td>
                                <button type="button" onclick="showDetail(${boMon.id})" class="btn btn-primary">Chi tiết</button>
                                <button type="button" onclick="confirmDelete(${boMon.id})" class="btn btn-danger">Xóa</button>
                            </td>
                        `;

                        tableBody.appendChild(tr);
                    });
                })
                .catch(err => {
                    console.error('Lỗi khi tìm kiếm:', err);
                });
        }, 300);
    });
});

const filterTrangThai = document.getElementById('trangThai');
const tableRows = document.querySelectorAll('table tbody tr');

filterTrangThai.addEventListener('change', () => {
    const selectedValue = filterTrangThai.value;

    tableRows.forEach(row => {
        const trangThaiCell = row.cells[6].innerText.trim(); // cột trạng thái (cột 7, index 6)
        if (selectedValue === "") {
            // Hiện tất cả khi chưa chọn
            row.style.display = '';
        } else if ((selectedValue === "true" && trangThaiCell === "Hoạt động") ||
            (selectedValue === "false" && trangThaiCell === "Ngừng hoạt động")) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
});
// ------------------------------------------------------------------------------
const importBtn = document.getElementById('importBtn');
const fileInput = document.getElementById('fileInput');
const form = document.getElementById('importForm');

importBtn.addEventListener('click', () => {
    fileInput.click();  // Mở hộp thoại chọn file
});

fileInput.addEventListener('change', () => {
    if (fileInput.files.length > 0) {
        form.submit();   // Tự động submit form khi đã chọn file
    }
});
