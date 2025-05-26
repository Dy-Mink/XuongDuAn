CREATE DATABASE QuanLyPhanCong;
GO

USE QuanLyPhanCong;
GO


	-- 1. vai_tro
CREATE TABLE vai_tro (
    id_vai_tro INT IDENTITY(1,1) PRIMARY KEY,
    ten_vai_tro NVARCHAR(100),
    ma_vai_tro NVARCHAR(50)
);

-- 2. nguoi_dung
CREATE TABLE nguoi_dung (
    id_nguoi_dung INT IDENTITY(1,1) PRIMARY KEY,
    ten_dang_nhap NVARCHAR(100),
    mat_khau NVARCHAR(100),
    ho_ten NVARCHAR(100),
    email NVARCHAR(100),
    sdt NVARCHAR(20),
    ma NVARCHAR(50),
    trang_thai INT,
    id_vai_tro INT,
    FOREIGN KEY (id_vai_tro) REFERENCES vai_tro(id_vai_tro)
);

-- 3. phan_quyen
CREATE TABLE phan_quyen (
    id_nguoi_dung INT,
    ma_quyen NVARCHAR(100),
    PRIMARY KEY (id_nguoi_dung, ma_quyen),
    FOREIGN KEY (id_nguoi_dung) REFERENCES nguoi_dung(id_nguoi_dung)
);

-- 4. co_so
CREATE TABLE co_so (
    id_co_so INT IDENTITY(1,1) PRIMARY KEY,
    ten_co_so NVARCHAR(100),
    ma_co_so NVARCHAR(50),
    dia_chi NVARCHAR(200),
    sdt NVARCHAR(20),
    nguoi_tao NVARCHAR(50),
    nguoi_cap_nhat NVARCHAR(50),
    ngay_tao DATETIME,
    ngay_cap_nhat DATETIME,
    trang_thai INT
);

-- 5. bo_mon
CREATE TABLE bo_mon (
    id_bo_mon INT IDENTITY(1,1) PRIMARY KEY,
    ten_bo_mon NVARCHAR(100),
    ma_bo_mon NVARCHAR(50),
    mo_ta NVARCHAR(200),
    id_co_so INT,
    nguoi_tao NVARCHAR(50),
    nguoi_cap_nhat NVARCHAR(50),
    ngay_tao DATETIME,
    ngay_cap_nhat DATETIME,
    trang_thai INT,
    FOREIGN KEY (id_co_so) REFERENCES co_so(id_co_so)
);

-- 6. chuyen_mon
CREATE TABLE chuyen_mon (
    id_chuyen_mon INT IDENTITY(1,1) PRIMARY KEY,
    ten_chuyen_mon NVARCHAR(100),
    ma_chuyen_mon NVARCHAR(50),
    mo_ta NVARCHAR(200),
    nguoi_tao NVARCHAR(50),
    nguoi_cap_nhat NVARCHAR(50),
    ngay_tao DATETIME,
    ngay_cap_nhat DATETIME,
    trang_thai INT
);

-- 7. chuyen_mon_bo_mon_cs
CREATE TABLE chuyen_mon_bo_mon_cs (
    id_chuyen_mon INT,
    id_bo_mon INT,
    id_co_so INT,
    PRIMARY KEY (id_chuyen_mon, id_bo_mon, id_co_so),
    FOREIGN KEY (id_chuyen_mon) REFERENCES chuyen_mon(id_chuyen_mon),
    FOREIGN KEY (id_bo_mon) REFERENCES bo_mon(id_bo_mon),
    FOREIGN KEY (id_co_so) REFERENCES co_so(id_co_so)
);

-- 8. du_an
CREATE TABLE du_an (
    id_du_an INT IDENTITY(1,1) PRIMARY KEY,
    ten_du_an NVARCHAR(200),
    mo_ta NVARCHAR(500),
    id_co_so INT,
    nguoi_tao NVARCHAR(50),
    nguoi_cap_nhat NVARCHAR(50),
    ngay_tao DATETIME,
    ngay_cap_nhat DATETIME,
    trang_thai INT,
    FOREIGN KEY (id_co_so) REFERENCES co_so(id_co_so)
);

-- 9. thanh_vien_du_an
CREATE TABLE thanh_vien_du_an (
    id_du_an INT,
    id_nguoi_dung INT,
    PRIMARY KEY (id_du_an, id_nguoi_dung),
    FOREIGN KEY (id_du_an) REFERENCES du_an(id_du_an),
    FOREIGN KEY (id_nguoi_dung) REFERENCES nguoi_dung(id_nguoi_dung)
);

-- 10. lich_su_du_an
CREATE TABLE lich_su_du_an (
    id_du_an INT,
    ngay_bat_dau DATETIME,
    ngay_ket_thuc DATETIME,
    trang_thai INT,
    PRIMARY KEY (id_du_an, ngay_bat_dau),
    FOREIGN KEY (id_du_an) REFERENCES du_an(id_du_an)
);

-- 11. bao_cao
CREATE TABLE bao_cao (
    id_bao_cao INT IDENTITY(1,1) PRIMARY KEY,
    id_nguoi_dung INT,
    id_du_an INT,
    ngay_bao_cao DATETIME,
    noi_dung NVARCHAR(MAX),
    ngay_tao DATETIME,
    ngay_update DATETIME,
    thoi_gian TIME,
    trang_thai INT,
    FOREIGN KEY (id_nguoi_dung) REFERENCES nguoi_dung(id_nguoi_dung),
    FOREIGN KEY (id_du_an) REFERENCES du_an(id_du_an)
);

-- 12. danh_sach_cong_viec
CREATE TABLE danh_sach_cong_viec (
    id_danh_sach_cong_viec INT IDENTITY(1,1) PRIMARY KEY,
    id_du_an INT,
    ten_cong_viec NVARCHAR(200),
    mo_ta NVARCHAR(500),
    ngay_tao DATETIME,
    nguoi_tao NVARCHAR(50),
    trang_thai INT,
    FOREIGN KEY (id_du_an) REFERENCES du_an(id_du_an)
);

-- 13. cong_viec
CREATE TABLE cong_viec (
    id_cong_viec INT IDENTITY(1,1) PRIMARY KEY,
    id_danh_sach_cong_viec INT,
    ten_cong_viec NVARCHAR(200),
    mo_ta NVARCHAR(500),
    ngay_tao DATETIME,
    nguoi_tao NVARCHAR(50),
    trang_thai INT,
    FOREIGN KEY (id_danh_sach_cong_viec) REFERENCES danh_sach_cong_viec(id_danh_sach_cong_viec)
);

-- 14. phan_cong
CREATE TABLE phan_cong (
    id_phan_cong INT IDENTITY(1,1) PRIMARY KEY,
    id_danh_sach_cong_viec INT,
    id_nguoi_dung INT,
    ngay_phan_cong DATETIME,
    FOREIGN KEY (id_danh_sach_cong_viec) REFERENCES danh_sach_cong_viec(id_danh_sach_cong_viec),
    FOREIGN KEY (id_nguoi_dung) REFERENCES nguoi_dung(id_nguoi_dung)
);

-- 15. lich_su_cong_viec
CREATE TABLE lich_su_cong_viec (
    id_cong_viec INT,
    ngay_bat_dau DATETIME,
    ngay_ket_thuc DATETIME,
    noi_dung NVARCHAR(MAX),
    trang_thai INT,
    PRIMARY KEY (id_cong_viec, ngay_bat_dau),
    FOREIGN KEY (id_cong_viec) REFERENCES cong_viec(id_cong_viec)
);
