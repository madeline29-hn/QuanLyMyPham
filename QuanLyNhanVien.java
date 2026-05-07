package QuanLyMyPham;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

public class QuanLyNhanVien implements iQuanLyDanhSach, iDocGhiFile {

    private NhanVien[] dsNhanVien = new NhanVien[100];
    private int soLuongNV = 0;
    static Scanner sc = new Scanner(System.in);


    public NhanVien[] getDsNhanVien() {
        return dsNhanVien;
    }

    public void setDsNhanVien(NhanVien[] ds) {
        this.dsNhanVien = ds;
    }

    public int getSoLuongNV() {
        return soLuongNV;
    }

    public void setSoLuongNV(int soLuong) {
        this.soLuongNV = soLuong;
    }

    public boolean isTrungMaNV(String ma) {
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i].getMaNhanVien().equalsIgnoreCase(ma)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTrungCCCD(String cccd) {
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i].getSoCCCD().equals(cccd)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTrungSDT(String sdt) {
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i].getSdt().equals(sdt)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTrungEmail(String email) {
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i].getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public void themVaoDanhSach(NhanVien nv) {

        if (soLuongNV >= dsNhanVien.length) {
            dsNhanVien = Arrays.copyOf(dsNhanVien, dsNhanVien.length + 5);
        }
        dsNhanVien[soLuongNV] = nv;
        soLuongNV++;
    }

    @Override
    public void themVaoDanhSach() {

        while (true) {
            NhanVien nv = null;

            System.out.println("\n----- NHAP THONG TIN NHAN VIEN MOI -----");
            System.out.println("1. Quan Ly | 2. Thu Ngan | 3. CSKH");
            System.out.print("Chon loai: ");
            int loai = Integer.parseInt(sc.nextLine());

            if (loai == 1) nv = new QuanLy();
            else if (loai == 2) nv = new ThuNgan();
            else nv = new NhanVienCSKH();

            nv.nhap(this.dsNhanVien, this.soLuongNV);
            themVaoDanhSach(nv);
            System.out.println(">>> Da them nhan vien thanh cong!");
            break;
        }
    }

    @Override
    public void nhapDanhSach() {
        System.out.print("Nhap so luong nhan vien: ");

        String input = sc.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Ban chua nhap!");
            return;
        }

        int n;
        try {
            n = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang so!");
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("\n=== Nhan vien thu " + (i + 1) + " ===");
            themVaoDanhSach();
        }
        System.out.println("\n>>> Da hoan tat nhap danh sach " + n + " nhan vien.");
    }



    @Override
    public void xoaKhoiDanhSach(String ma) {

        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i].getMaNhanVien().equalsIgnoreCase(ma)) {
                for (int j = i; j < soLuongNV - 1; j++) {
                    dsNhanVien[j] = dsNhanVien[j + 1];
                }
                dsNhanVien[--soLuongNV] = null;
                System.out.println("Da xoa!");
                return;
            }
        }
        System.out.println("Khong tim thay!");
    }

    @Override
    public void suaDanhSach(String ma) {
        NhanVien nvCanSua = null;
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i].getMaNhanVien().equalsIgnoreCase(ma)) {
                nvCanSua = dsNhanVien[i];
                break;
            }
        }

        if (nvCanSua == null) {
            System.out.println("Khong tim thay nhan vien co ma: " + ma);
            return;
        }

        int luaChon;
        do {
            System.out.println("\n===== CHINH SUA THONG TIN NHAN VIEN =====");
            System.out.println("1. Sua Ho Ten      (Hien tai: " + nvCanSua.getHoTen() + ")");
            System.out.println("2. Sua Ngay Sinh   (Hien tai: " + nvCanSua.getNgaySinh() + ")");
            System.out.println("3. Sua Gioi Tinh   (Hien tai: " + nvCanSua.getGioiTinh() + ")");
            System.out.println("4. Sua So CCCD     (Hien tai: " + nvCanSua.getSoCCCD() + ")");
            System.out.println("5. Sua SDT         (Hien tai: " + nvCanSua.getSdt() + ")");
            System.out.println("6. Sua Email       (Hien tai: " + nvCanSua.getEmail() + ")");
            System.out.println("7. Sua Ngay Cong   (Hien tai: " + nvCanSua.getNgayCong() + ")");
            System.out.println("8. Sua Trang Thai  (Hien tai: " + (nvCanSua.isTrangThai() ? "Dang lam" : "Nghi") + ")");
            System.out.println("9. Sua Ma Nhan Vien (Hien tai: " + nvCanSua.getMaNhanVien() + ")");
            System.out.println("0. Luu va Thoat");
            System.out.print("Chon thong tin can sua (0-9): ");

            try {
                luaChon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                luaChon = -1;
            }

            switch (luaChon) {
                case 1:
                    while (true) {
                        System.out.println("Nhap ho ten moi: ");
                        String hoTen = sc.nextLine().trim();
                        if (hoTen.matches("^[^0-9]+$")) {
                            nvCanSua.setHoTen(hoTen);
                            break;
                        } else {
                            System.out.println("Ho ten khong duoc chua so, vui long nhap lai!");
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        try {
                            System.out.print("Nhap Ngay Sinh (yyyy-mm-dd) moi: ");
                            LocalDate ns = LocalDate.parse(sc.nextLine().trim());

                            int tuoi = LocalDate.now().getYear() - ns.getYear();
                            if (tuoi >= 18 && tuoi <= 100) {
                                nvCanSua.setNgaySinh(ns);
                                break;
                            } else {
                                System.out.println("Tuoi phai tu 18 den 100!");
                            }
                        } catch (Exception e) {
                            System.out.println("Sai dinh dang ngay!");
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.print("Nhap Gioi Tinh (Nam/Nu) moi: ");
                        String gt = sc.nextLine().trim();

                        if (gt.equalsIgnoreCase("Nam") || gt.equalsIgnoreCase("Nu")) {
                            nvCanSua.setGioiTinh(gt);
                            break;
                        } else {
                            System.out.println("Chi duoc nhap Nam hoac Nu!");
                        }
                    }
                    break;
                case 4:
                    while (true) {
                        System.out.print("Nhap CCCD moi (12 so) moi: ");
                        String cccd = sc.nextLine().trim();

                        if (!cccd.matches("0\\d{12}")) {
                            System.out.println("CCCD phai 12 so!");
                            continue;
                        }

                        if (isTrungCCCD(cccd) && !cccd.equals(nvCanSua.getSoCCCD())) {
                            System.out.println("CCCD bi trung!");
                            continue;
                        }

                        nvCanSua.setSoCCCD(cccd);
                        break;
                    }
                    break;
                case 5:
                    while (true) {
                        System.out.print("Nhap so dien thoai (10 so) moi: ");
                        String sdt = sc.nextLine().trim();
                        if (!sdt.matches("0\\d{9}")) {
                            System.out.println("SDT phai 10 so va bat dau bang 0!");
                            continue;
                        }
                            if (isTrungSDT(sdt) && !sdt.equals(nvCanSua.getSdt())) {
                                System.out.println("SDT bi trung!");
                                continue;
                            }

                            nvCanSua.setSdt(sdt);
                            break;
                            }
                    break;
                case 6:
                    while (true) {
                        System.out.print("Nhap ten email (truoc @) moi: ");
                        String emailName = sc.nextLine().trim();

                        if (emailName.isEmpty() || emailName.contains(" ")) {
                            System.out.println("Ten email khong hop le!");
                            continue;
                        }

                        String email = emailName + "@gmail.com";

                        if (isTrungEmail(email) && !email.equalsIgnoreCase(nvCanSua.getEmail())) {
                            System.out.println("Email bi trung!");
                            continue;
                        }

                        nvCanSua.setEmail(email);
                        break;
                    }
                    break;
                case 7:
                    while (true) {
                        try {
                            System.out.print("Nhap Ngay Cong (0-31) moi: ");
                            double nc = Double.parseDouble(sc.nextLine());

                            if (nc >= 0 && nc <= 31) {
                                nvCanSua.setNgayCong(nc);
                                break;
                            } else {
                                System.out.println("Ngay cong phai tu 0-31!");
                            }
                        } catch (Exception e) {
                            System.out.println("Nhap sai!");
                        }
                    }
                    break;
                case 8:
                    while (true) {
                        System.out.print("Trang thai (1-Dang lam | 0-Nghi) moi: ");
                        String st = sc.nextLine();

                        if (st.equals("1") || st.equals("0")) {
                            nvCanSua.setTrangThai(st.equals("1"));
                            break;
                        } else {
                            System.out.println("Nhap 1 hoac 0!");
                        }
                    }
                    break;
                case 9:
                    while (true) {
                        System.out.print("Nhap ma nhan vien moi (5 chu so): ");
                        String input = sc.nextLine().trim();
                        if (!input.matches("\\d{5}")) {
                            System.out.println("Ma NV phai co dung 5 chu so!");
                            continue;
                        }
                        String maMoi = "NV" + input;
                        if (isTrungMaNV(maMoi) && !maMoi.equalsIgnoreCase(nvCanSua.getMaNhanVien())) {
                            System.out.println("Ma NV da ton tai!");
                            continue;
                        }
                        nvCanSua.setMaNhanVien(maMoi);
                        break;
                    }
                    break;
                case 0:
                    System.out.println("Dang luu thay doi...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (luaChon != 0);

        write();
        System.out.println("Sua thong tin nhan vien thanh cong!");
    }

    @Override
    public void timKiemChinhXac(String ma) {
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i].getMaNhanVien().equalsIgnoreCase(ma)) {
                dsNhanVien[i].xuat();
                return;
            }
        }
        System.out.println("Khong tim thay!");
    }

    @Override
    public void timKiemTuongDoi(String ten) {
        boolean found = false;
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i].getHoTen().toLowerCase().contains(ten)) {
                dsNhanVien[i].xuat();
                found = true;
            }
        }
        if (!found) System.out.println("Khong tim thay!");
    }

    @Override
    public void thongKeTheoKhoa() {
        System.out.println("Tong so nhan vien: " + soLuongNV);
    }

    public void xepLoaiNhanVien() {
        for (int i = 0; i < soLuongNV; i++) {
            double nc = dsNhanVien[i].getNgayCong();
            String loai;

            if (nc >= 26) loai = "Xuat sac";
            else if (nc >= 22) loai = "Tot";
            else if (nc >= 18) loai = "Trung binh";
            else loai = "Yeu";

            System.out.println(dsNhanVien[i].getHoTen() + " - " + loai);
        }
    }

    @Override
    public void xuatDanhSach() {
        if (soLuongNV == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        System.out.println("\n" + "=".repeat(52) + " DANH SACH QUAN LY " + "=".repeat(53));
        System.out.printf("| %-8s | %-22s | %-11s | %-13s | %-11s | %-5s | %-22s | %-10s | %-10s | %-15s |\n",
                "Ma NV", "Ho Ten", "Ngay Sinh", "CCCD", "SDT", "GT", "Email", "Chuc Vu", "Trang Thai", "Kinh Nghiem");
        System.out.println("-".repeat(141));
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i] instanceof QuanLy) {
                dsNhanVien[i].xuat();
            }
        }
        System.out.println("=".repeat(141));
        System.out.println("\n" + "=".repeat(54) + " DANH SACH THU NGAN " + "=".repeat(54));
        System.out.printf("| %-8s | %-22s | %-11s | %-13s | %-11s | %-5s | %-22s | %-10s | %-10s | %-8s | %-8s |\n",
                "Ma NV", "Ho Ten", "Ngay Sinh", "CCCD", "SDT", "GT", "Email", "Chuc Vu", "Trang Thai", "Ma Quay", "Ca Lam");
        System.out.println("-".repeat(145));
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i] instanceof ThuNgan) {
                dsNhanVien[i].xuat();
            }
        }
        System.out.println("=".repeat(145));
        System.out.println("\n" + "=".repeat(55) + " DANH SACH CSKH " + "=".repeat(55));
        System.out.printf("| %-8s | %-22s | %-11s | %-13s | %-11s | %-5s | %-22s | %-10s | %-10s | %-9s | %-8s |\n",
                "Ma NV", "Ho Ten", "Ngay Sinh", "CCCD", "SDT", "GT", "Email", "Chuc Vu", "Trang Thai", "Danh Gia", "Ca Lam");
        System.out.println("-".repeat(146));
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i] instanceof NhanVienCSKH) {
                dsNhanVien[i].xuat();
            }
        }
        System.out.println("=".repeat(146));
    }

    public double tinhThucLanh(NhanVien nv) {
        if (nv == null) return 0;
        return nv.tinhLuong() + tinhThuongChuyenCan(nv.getNgayCong());
    }

    public double tinhThuongChuyenCan(double ngayCong) {
        if (ngayCong >= 26) return 1000000;
        if (ngayCong >= 22) return 500000;
        if (ngayCong >= 18) return 200000;
        return 0;
    }

    public void xuatBangLuong() {
        if (soLuongNV == 0) {
            System.out.println("\n(!) Danh sach trong, khong the thiet lap bang luong.");
            return;
        }
        System.out.println("\n" + "=".repeat(110));
        System.out.printf("%-60s %48s\n", " ", "NGAY XUAT: " + java.time.LocalDate.now());
        System.out.println(" ".repeat(38) + "BANG TINH LUONG CHI TIET NHAN VIEN");
        System.out.println("=".repeat(110));
        System.out.printf("| %-8s | %-22s | %-10s | %-12s | %-15s | %-15s |\n",
                "MA NV", "HO TEN", "NGAY CONG", "LUONG CB", "THUONG CHUYEN", "THUC LANH");
        System.out.println("-".repeat(110));

        double tongThuong = 0;
        double tongThucLanh = 0;
        for (int i = 0; i < soLuongNV; i++) {
            NhanVien nv = dsNhanVien[i];
            if (nv == null) continue;

            double thuong = tinhThuongChuyenCan(nv.getNgayCong());
            double thucLanh = tinhThucLanh(nv);

            tongThuong += thuong;
            tongThucLanh += thucLanh;

            System.out.printf("| %-8s | %-22s | %-10.1f | %-12.0f | %-15.0f | %-15.0f |\n",
                    nv.getMaNhanVien(),
                    nv.getHoTen(),
                    nv.getNgayCong(),
                    nv.getLuongCoBan(),
                    thuong,
                    thucLanh);
        }
        System.out.println("-".repeat(110));
        System.out.printf("| %-61s | %-15.0f | %-15.0f |\n",
                "TONG CONG TOAN CUA HANG", tongThuong, tongThucLanh);
        System.out.println("=".repeat(110));
    }

    public void xepHangNhanVien() {
        for (int i = 0; i < soLuongNV - 1; i++) {
            for (int j = i + 1; j < soLuongNV; j++) {
                if (dsNhanVien[i].getNgayCong() < dsNhanVien[j].getNgayCong()) {
                    NhanVien t = dsNhanVien[i];
                    dsNhanVien[i] = dsNhanVien[j];
                    dsNhanVien[j] = t;
                }
            }
        }
        System.out.println("Da sap xep theo ngay cong!");
    }

    public void hienThiTopNV() {
        if (soLuongNV == 0) return;

        double max = dsNhanVien[0].getNgayCong();
        for (int i = 1; i < soLuongNV; i++) {
            if (dsNhanVien[i].getNgayCong() > max) {
                max = dsNhanVien[i].getNgayCong();
            }
        }

        System.out.println("\n--- NHAN VIEN CO NGAY CONG CAO NHAT ---");
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i].getNgayCong() == max) {
                dsNhanVien[i].xuat();
            }
        }
    }

    public void thongKeSoLuong() {
        System.out.println("Tong so nhan vien trong he thong : " + soLuongNV);
    }

    public void thongKeNhanVienDangLamViec() {
        int dem = 0;
        System.out.println("\n--- DANH SACH NHAN VIEN DANG LAM VIEC  ---");
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i].isTrangThai()) {
                dsNhanVien[i].xuat();
                dem++;
            }
        }
        System.out.println("=>Tong cong co " + dem + " nhan vien dang lam viec .");
    }

    public void suaThongTinCaNhan(String maNV) {
        NhanVien nv = timNhanVienTheoMa(maNV);
        if (nv == null) {
            System.out.println("(!) Khong tim thay ho so cho ma: " + maNV);
            return;
        }

        int chon;
        do {
            System.out.println("\n--- CHINH SUA THONG TIN CA NHAN (NV: " + maNV + ") ---");
            System.out.println("1. Sua Ho Ten      (Hien tai: " + nv.getHoTen() + ")");
            System.out.println("2. Sua CCCD        (Hien tai: " + nv.getSoCCCD() + ")");
            System.out.println("3. Sua So Dien Thoai (Hien tai: " + nv.getSdt() + ")");
            System.out.println("4. Sua Email       (Hien tai: " + nv.getEmail() + ")");
            System.out.println("5. Sua Gioi Tinh   (Hien tai: " + nv.getGioiTinh() + ")");
            System.out.println("0. Hoan tat & Luu");
            System.out.print("Nhap lua chon: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                chon = -1;
            }

            switch (chon) {
                case 1:
                    System.out.print("Nhap Ho Ten moi: ");
                    String hoTen = sc.nextLine().trim();
                    if (hoTen.matches("^[^0-9]+$")) nv.setHoTen(hoTen);
                    else System.out.println("(!) Loi: Ho ten khong hop le.");
                    break;
                case 2:
                    System.out.print("Nhap CCCD moi (12 so): ");
                    String cccd = sc.nextLine().trim();
                    if (cccd.matches("0\\d{11}")) {
                        if (isTrungCCCD(cccd) && !cccd.equals(nv.getSoCCCD())) {
                            System.out.println("(!) Loi: So CCCD nay da thuoc ve nhan vien khac.");
                        } else {
                            nv.setSoCCCD(cccd);
                        }
                    } else System.out.println("(!) Loi: CCCD phai co 12 so.");
                    break;
                case 3:
                    System.out.print("Nhap SDT moi: ");
                    String sdt = sc.nextLine().trim();
                    if (sdt.matches("0\\d{9}")) {
                        if (isTrungSDT(sdt) && !sdt.equals(nv.getSdt())) {
                            System.out.println("(!) Loi: So dien thoai da ton tai trên hệ thống.");
                        } else {
                            nv.setSdt(sdt);
                        }
                    } else System.out.println("(!) Loi: SDT khong hop le.");
                    break;
                case 4:
                    System.out.print("Nhap Email moi: ");
                    String email = sc.nextLine().trim();
                    if (email.contains("@")) nv.setEmail(email);
                    else System.out.println("(!) Loi: Email sai định dạng.");
                    break;
                case 5:
                    System.out.print("Nhap Gioi Tinh (Nam/Nu): ");
                    String gt = sc.nextLine().trim();
                    if (gt.equalsIgnoreCase("Nam") || gt.equalsIgnoreCase("Nu")) nv.setGioiTinh(gt);
                    else System.out.println("(!) Loi: Chi nhap Nam hoac Nu.");
                    break;
            }
        } while (chon != 0);

        write();
        System.out.println(">>> Da cap nhat va luu ho so nhan vien thành công!");
    }

    public void hienThiHoSoNhanVien(String maNV) {
        NhanVien nv = timNhanVienTheoMa(maNV);

        if (nv != null) {
            System.out.println("\n--- HO SO NHAN VIEN CHI TIET ---");
            if (nv instanceof QuanLy) {
                System.out.printf("| %-8s | %-22s | %-11s | %-13s | %-11s | %-5s | %-22s | %-10s | %-10s | %-15s |\n",
                        "Ma NV", "Ho Ten", "Ngay Sinh", "CCCD", "SDT", "GT", "Email", "Chuc Vu", "Trang Thai", "Kinh Nghiem");
            } else if (nv instanceof ThuNgan) {
                System.out.printf("| %-8s | %-22s | %-11s | %-13s | %-11s | %-5s | %-22s | %-10s | %-10s | %-8s | %-8s |\n",
                        "Ma NV", "Ho Ten", "Ngay Sinh", "CCCD", "SDT", "GT", "Email", "Chuc Vu", "Trang Thai", "Ma Quay", "Ca Lam");
            } else if (nv instanceof NhanVienCSKH) {
                System.out.printf("| %-8s | %-22s | %-11s | %-13s | %-11s | %-5s | %-22s | %-10s | %-10s | %-9s | %-8s |\n",
                        "Ma NV", "Ho Ten", "Ngay Sinh", "CCCD", "SDT", "GT", "Email", "Chuc Vu", "Trang Thai", "Danh Gia", "Ca Lam");
            }

            System.out.println("-".repeat(140));
            nv.xuat();
            System.out.println("-".repeat(140));

            double luongChucVu = nv.tinhLuong();
            double thuongCC = tinhThuongChuyenCan(nv.getNgayCong());
            double thucLanh = tinhThucLanh(nv);

            System.out.println("--- CHI TIET THU NHAP THANG NAY ---");
            System.out.printf("+ Luong chuc vu: %,.0f VND\n", luongChucVu);
            System.out.printf("+ Thuong chuyen can (%.1f ngay): %,.0f VND\n", nv.getNgayCong(), thuongCC);
            System.out.println("-----------------------------------");
            System.out.printf("=> TONG LUONG THUC LANH:       %,.0f VND\n", thucLanh);
            System.out.println("=".repeat(145));
        } else {
            System.out.println("(!) Khong tim thay thong tin nhan vien cho ma: " + maNV);
        }
    }

    @Override
    public void write() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Java\\QuanLyMyPham\\dsNhanVien.txt"));
            for (int i = 0; i < soLuongNV; i++) {
                bw.write(dsNhanVien[i].toDataString());
                bw.newLine();
            }
            bw.close();
            System.out.println("Luu file thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi ghi file!");
        }
    }
    @Override
    public void read() {
        File f = new File("D:\\Java\\QuanLyMyPham\\dsNhanVien.txt");
        if (!f.exists()) {
            System.out.println("Loi: File dsNhanVien.txt không tồn tại!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            this.soLuongNV = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(";");
                if (data.length >= 1) {
                    String loaiNV = data[0].trim();
                    NhanVien nv;
                    if (loaiNV.equalsIgnoreCase("Quan Ly")) {
                        nv = new QuanLy();
                    } else if (loaiNV.equalsIgnoreCase("Thu Ngan")) {
                        nv = new ThuNgan();
                    } else {
                        nv = new NhanVienCSKH();
                    }
                    nv.fromString(data);
                    if (soLuongNV < dsNhanVien.length) {
                        dsNhanVien[soLuongNV++] = nv;
                    }
                }
            }
            System.out.println("Doc file Nhan Vien thanh cong! So luong: " + soLuongNV);
        } catch (Exception e) {
            System.out.println("Loi doc file NV: " + e.getMessage());
        }
    }

    public NhanVien timNhanVienTheoMa(String maNV) {
        for (int i = 0; i < soLuongNV; i++) {
            if (dsNhanVien[i] != null && dsNhanVien[i].getMaNhanVien().equalsIgnoreCase(maNV)) {
                return dsNhanVien[i];
            }
        }
        return null;
    }


    public void menu() {
        read();
        int chon;
        do {
            System.out.println("\n===== MENU QUAN LY NHAN VIEN =====");
            System.out.println("1. Nhap them nhan vien ");
            System.out.println("2. Xuat danh sach nhan vien");
            System.out.println("3. Xoa nhan vien");
            System.out.println("4. Sua thong tin nhan vien");
            System.out.println("5. Tim chinh xac (theo ma)");
            System.out.println("6. Tim tuong doi (theo ten)");
            System.out.println("7. Xuat bang luong");
            System.out.println("8. Xep loai nhan vien");
            System.out.println("9. Hien thi top nhan vien");
            System.out.println("10. Sap xep theo ngay cong");
            System.out.println("11. Thong ke so luong nhan vien");
            System.out.println("12. Hien thi nhan vien dang lam viec");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                chon = -1;
            }

            switch (chon) {
                case 1: nhapDanhSach(); write(); break;
                case 2: xuatDanhSach(); break;
                case 3:
                    System.out.print("Nhap ma NV can xoa: ");
                    xoaKhoiDanhSach(sc.nextLine().trim());
                    write();
                    break;
                case 4:
                    System.out.print("Nhap ma NV can sua: ");
                    suaDanhSach(sc.nextLine().trim());
                    write();
                    break;
                case 5:
                    System.out.print("Nhap ma NV: ");
                    timKiemChinhXac(sc.nextLine().trim());
                    break;
                case 6:
                    System.out.print("Nhap ten NV: ");
                    String tuKhoa = sc.nextLine().toLowerCase().trim();
                    timKiemTuongDoi(tuKhoa);
                    break;
                case 7: xuatBangLuong(); break;
                case 8: xepLoaiNhanVien(); break;
                case 9: hienThiTopNV(); break;
                case 10: xepHangNhanVien(); break;
                case 11: thongKeSoLuong(); break;
                case 12: thongKeNhanVienDangLamViec(); break;
                case 0:
                    write();
                    System.out.println("Da luu va thoat!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }
}

