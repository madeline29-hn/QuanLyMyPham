package manager;
import model.*;
import model.iDocGhiFile;     
import model.iQuanLyDanhSach;  
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class QuanLyKhachHang implements iQuanLyDanhSach, iDocGhiFile {
    private KhachHang[] dsKhachHang = new KhachHang[50];
    private int soLuongKH = 0;
    static Scanner sc = new Scanner(System.in);


    public KhachHang[] getDsKhachHang() {
        return dsKhachHang;
    }

    public void setDsKhachHang(KhachHang[] dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    public int getSoLuongKH() {
        return soLuongKH;
    }

    public void setSoLuongKH(int soLuongKH) {
        this.soLuongKH = soLuongKH;
    }

    public QuanLyKhachHang() {}

    public QuanLyKhachHang(KhachHang[] dsKhachHang, int soLuongKH) {
        this.dsKhachHang = dsKhachHang;
        this.soLuongKH = soLuongKH;
    }

    public boolean isTrungMaKH(String ma) {
        if (ma == null) return false;

        for (int i = 0; i < soLuongKH; i++) {
            if (dsKhachHang[i] != null &&
                    dsKhachHang[i].getMaKH() != null &&
                    dsKhachHang[i].getMaKH().equalsIgnoreCase(ma.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean isTrungSDT(String sdt) {
        if (sdt == null) return false;

        for (int i = 0; i < soLuongKH; i++) {
            if (dsKhachHang[i] != null &&
                    dsKhachHang[i].getSdt() != null &&
                    dsKhachHang[i].getSdt().equals(sdt.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean isTrungEmail(String email) {
        if (email == null) return false;

        for (int i = 0; i < soLuongKH; i++) {
            if (dsKhachHang[i] != null &&
                    dsKhachHang[i].getEmail() != null &&
                    dsKhachHang[i].getEmail().equalsIgnoreCase(email.trim())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void themVaoDanhSach() {
        KhachHang khThem = new KhachHang();
        khThem.nhap(dsKhachHang, soLuongKH);
        String hang = khThem.getThuHang();
        if (hang != null && (hang.equalsIgnoreCase("Vang") || hang.equalsIgnoreCase("Kim cuong"))) {
            KhachHangVIP vip = new KhachHangVIP();
            vip.fromDataString(khThem.toDataString());
            themVaoDanhSach(vip);
        } else {
            themVaoDanhSach(khThem);
        }
        System.out.println(">>> Da them khach hang thanh cong!");
        write();
    }

    public void capNhatSauHoaDon(HoaDon hd) {
        if (hd == null || hd.getThongTinKH() == null) return;
        String maKH = hd.getThongTinKH().getMaKH();

        for (int i = 0; i < soLuongKH; i++) {
            if (dsKhachHang[i].getMaKH().equalsIgnoreCase(maKH)) {
                dsKhachHang[i].capNhatSauMua(hd.tinhTongTien());
                dsKhachHang[i].themHoaDon(hd);

                String hangMoi = dsKhachHang[i].getThuHang();
                if (hangMoi.equalsIgnoreCase("Vang") || hangMoi.equalsIgnoreCase("Kim cuong")) {
                    if (!(dsKhachHang[i] instanceof KhachHangVIP)) {
                        KhachHangVIP vip = new KhachHangVIP();
                        vip.fromDataString(dsKhachHang[i].toDataString());
                        dsKhachHang[i] = vip;
                    }
                } else {
                    if (dsKhachHang[i] instanceof KhachHangVIP) {
                        KhachHang khThuong = new KhachHang();
                        khThuong.fromDataString(dsKhachHang[i].toDataString());
                        dsKhachHang[i] = khThuong;
                    }
                }
                write();
                System.out.println(">>> Da dong bo du lieu khach hang sau khi mua hang.");
                return;
            }
        }
    }

    public void thongKeLichSuMuaHang(QuanLyHoaDon qlhd) {
        if (qlhd == null || qlhd.getSoLuongHoaDon() == 0) {
            System.out.println("Du lieu hoa don dang trong! Vui long doc file hoa don truoc.");
            return;
        }

        if (soLuongKH == 0) {
            System.out.println("Danh sach khach hang dang trong.");
            return;
        }

        System.out.println("\n" + "=".repeat(95));
        System.out.println("                     TONG HOP LICH SU MUA HANG CUA TOAN BO KHACH HANG");
        System.out.println("=".repeat(95));
        HoaDon[] dsHD = qlhd.getDsHoaDon();
        for (int i = 0; i < soLuongKH; i++) {
            KhachHang kh = dsKhachHang[i];
            String maKH = kh.getMaKH();

            System.out.println("\n>>> KHACH HANG: " + kh.getHoTen().toUpperCase() + " (" + maKH + ") - Hang: " + kh.getThuHang());
            System.out.println("-".repeat(95));

            double tongChiTieu = 0;
            int soDonThanhCong = 0;
            boolean coDonHang = false;
            for (int j = 0; j < qlhd.getSoLuongHoaDon(); j++) {
                HoaDon hd = dsHD[j];

                if (hd != null && hd.getThongTinKH() != null && hd.getThongTinKH().getMaKH().equalsIgnoreCase(maKH)) {
                    coDonHang = true;
                    double thanhTien = hd.tinhTongTien();

                    System.out.printf("  + Ma HD: %-8s | Ngay lap: %-10s | Trang thai: %-15s | Tong: %,.2f\n",
                            hd.getMaHD(), hd.getNgayLap(), hd.getTrangThai(), thanhTien);
                    SanPham[] spTrongHD = hd.getDsSanPham();
                    int[] slTrongHD = hd.getSoLuong();
                    for (int k = 0; k < hd.getSoLuongThucTe(); k++) {
                        if (spTrongHD[k] != null) {
                            System.out.printf("     -> SP: %-15s | SL: %-3d | Gia: %,.2f\n",
                                    spTrongHD[k].getTenSanPham(), slTrongHD[k], spTrongHD[k].getGia());
                        }
                    }

                    if (hd.getTrangThai().equals(HoaDon.DA_THANH_TOAN)) {
                        tongChiTieu += thanhTien;
                        soDonThanhCong++;
                    }
                }
            }
            if (!coDonHang) {
                System.out.println("  (!) Khach hang nay chua co lich su giao dich trong he thong.");
            } else {
                System.out.printf("  => Da thanh toan: %d don hang | Tong chi tieu: %,.2f VNĐ\n", soDonThanhCong, tongChiTieu);
            }
            System.out.println("-".repeat(95));
        }
        System.out.println("=".repeat(95));
    }

    public void themVaoDanhSach(KhachHang kh) {
        if (soLuongKH >= dsKhachHang.length) {
            dsKhachHang = Arrays.copyOf(dsKhachHang, dsKhachHang.length + 5);
        }
        dsKhachHang[soLuongKH++] = kh;
    }

    @Override
    public void xoaKhoiDanhSach(String ma) {
        for (int i = 0; i < soLuongKH; i++) {
            if (dsKhachHang[i].getMaKH().equalsIgnoreCase(ma)) {
                for (int j = i; j < soLuongKH - 1; j++) {
                    dsKhachHang[j] = dsKhachHang[j + 1];
                }
                dsKhachHang[soLuongKH - 1] = null;
                soLuongKH--;
                System.out.println("Xoa thanh cong.");
                return;
            }
        }
        System.out.println("Khong tim thay ma khach hang: " + ma);
    }

    @Override
    public void suaDanhSach(String ma) {
        if (ma == null || ma.trim().isEmpty()) {
            System.out.println("Loi: Ma khach hang khong hop le!");
            return;
        }
        KhachHang khSua = null;
        int index = -1;

    for (int i = 0; i < soLuongKH; i++) {
        if (dsKhachHang[i].getMaKH().equalsIgnoreCase(ma)) {
            khSua = dsKhachHang[i];
            index = i;
            break;
        }
    }

    if (khSua == null) {
        System.out.println("Khong tim thay khach hang co ma: " + ma);
        return;
    }

    int luaChon;
    do {
        System.out.println("\n===== CHINH SUA THONG TIN KHACH HANG =====");
        System.out.println("1. Sua Ho Ten          (Hien tai: " + khSua.getHoTen() + ")");
        System.out.println("2. Sua Ngay Sinh       (Hien tai: " + khSua.getNgaySinh() + ")");
        System.out.println("3. Sua So Dien Thoai   (Hien tai: " + khSua.getSdt() + ")");
        System.out.println("4. Sua Email           (Hien tai: " + khSua.getEmail() + ")");
        System.out.println("5. Sua Diem & Hang     (Hien tai: " + khSua.getDiemTichLuy() + " - Hang: " + khSua.getThuHang() + ")");
        System.out.println("6. Sua Ma Khach Hang   (Hien tai: " + khSua.getMaKH() + ")");
        System.out.println("7. Xem nhanh Lich su & Tong chi tieu");
        System.out.println("0. Luu va Thoat");
        System.out.print("Chon thong tin can sua (0-7): ");

        try {
            luaChon = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            luaChon = -1;
        }

        switch (luaChon) {
            case 1:
                while (true) {
                    System.out.print("Nhap ho ten moi: ");
                    String hoTen = sc.nextLine().trim();
                    if (hoTen.matches("^[^0-9]+$") && !hoTen.isEmpty()) {
                        khSua.setHoTen(hoTen);
                        System.out.println(">>> Sua ho ten thanh cong!");
                        break;
                    } else {
                        System.out.println("Ho ten khong hop le va khong duoc chua so!");
                    }
                }
                break;

            case 2:
                while (true) {
                    try {
                        System.out.print("Nhap Ngay Sinh (yyyy-mm-dd) moi: ");
                        java.time.LocalDate ns = java.time.LocalDate.parse(sc.nextLine().trim());
                        java.time.LocalDate now = java.time.LocalDate.now();
                        if (ns.isBefore(now) && (now.getYear() - ns.getYear() <= 120)) {
                            khSua.setNgaySinh(ns);
                            System.out.println(">>> Sua ngay sinh thanh cong!");
                            break;
                        } else {
                            System.out.println("Ngay sinh phai o qua khu va tuoi khong qua 120!");
                        }
                    } catch (Exception e) {
                        System.out.println("Sai dinh dang ngay (yyyy-mm-dd)!");
                    }
                }
                break;

            case 3:
                while (true) {
                    System.out.print("Nhap so dien thoai moi (10 so): ");
                    String sdt = sc.nextLine().trim();

                    if (!sdt.matches("0\\d{9}")) {
                        System.out.println("SDT phai co dung 10 so va bat dau bang so 0!");
                        continue;
                    }

                    if (isTrungSDT(sdt) && !sdt.equals(khSua.getSdt())) {
                        System.out.println("SDT bi trung! Vui long nhap lai.");
                        continue;
                    }

                    khSua.setSdt(sdt);
                    System.out.println(">>> Sua SDT thanh cong!");
                    break;
                }
                break;

            case 4:
                while (true) {
                    System.out.print("Nhap ten email (truoc @) moi: ");
                    String emailName = sc.nextLine().trim();

                    if (emailName.isEmpty() || emailName.contains(" ")) {
                        System.out.println("Ten email khong hop le!");
                        continue;
                    }

                    String email = emailName + "@gmail.com";

                    if (isTrungEmail(email) && !email.equalsIgnoreCase(khSua.getEmail())) {
                        System.out.println("Email bi trung! Vui long nhap lai.");
                        continue;
                    }

                    khSua.setEmail(email);
                    System.out.println(">>> Sua email thanh cong!");
                    break;
                }
                break;

            case 5:
                while (true) {
                    try {
                        System.out.print("Nhap Diem Tich Luy moi: ");
                        double diemMoi = Double.parseDouble(sc.nextLine().trim());

                        if (diemMoi < 0) {
                            System.out.println("Diem tich luy khong duoc am!");
                            continue;
                        }
                        khSua.setDiemTichLuy(diemMoi);
                        CapNhapHang(khSua);
                        String hangMoi = khSua.getThuHang();
                        if (hangMoi.equalsIgnoreCase("Vang") || hangMoi.equalsIgnoreCase("Kim cuong")) {
                            if (!(khSua instanceof KhachHangVIP)) {
                                KhachHangVIP vip = new KhachHangVIP();
                                vip.fromDataString(khSua.toDataString());
                                dsKhachHang[index] = vip;
                                khSua = vip;
                            }
                        } else {
                            if (khSua instanceof KhachHangVIP) {
                                KhachHang khThuong = new KhachHang();
                                khThuong.fromDataString(khSua.toDataString());
                                dsKhachHang[index] = khThuong;
                                khSua = khThuong;
                            }
                        }

                        System.out.println(">>> Cap nhat Diem & Hang thanh cong!");
                        break;
                    } catch (Exception e) {
                        System.out.println("Diem phai la mot con so hop le!");
                    }
                }
                break;

            case 6:
                while (true) {
                    System.out.print("Nhap ma khach hang moi (5 chu so): ");
                    String input = sc.nextLine().trim();
                    if (!input.matches("\\d{5}")) {
                        System.out.println("Ma KH phai co dung 5 chu so!");
                        continue;
                    }
                    String maMoi = "KH" + input;

                    if (isTrungMaKH(maMoi) && !maMoi.equalsIgnoreCase(khSua.getMaKH())) {
                        System.out.println("Ma KH da ton tai!");
                        continue;
                    }
                    khSua.setMaKH(maMoi);
                    System.out.println(">>> Sua Ma KH thanh cong!");
                    break;
                }
                break;

            case 7:
                System.out.println("\n--- THONG TIN CHI TIEU CUA KHACH HANG ---");
                System.out.println("Ma KH: " + khSua.getMaKH());
                System.out.println("Ho ten: " + khSua.getHoTen());
                System.out.println("Hang hien tai: " + khSua.getThuHang());
                System.out.println("Diem hien tai: " + khSua.getDiemTichLuy());
                break;

            case 0:
                System.out.println("Dang luu thay doi...");
                break;

            default:
                System.out.println("Lua chon khong hop le!");
        }
    } while (luaChon != 0);
    write();
    System.out.println("Sua thong tin khach hang thanh cong!");
}
    public void suaThongTinCaNhan(String maKH) {
        Scanner sc = new Scanner(System.in);
        KhachHang khCanSua = null;
        if (this.dsKhachHang != null) {
            for (int i = 0; i < this.soLuongKH; i++) {
                if (this.dsKhachHang[i] != null &&
                        this.dsKhachHang[i].getMaKH().equalsIgnoreCase(maKH)) {
                    khCanSua = this.dsKhachHang[i];
                    break;
                }
            }
        }

        if (khCanSua == null) {
            System.out.println("(!) Khong tim thay thong tin khach hang.");
            return;
        }

        boolean coThayDoi = false;
        int chonSua;
        do {
            System.out.println("\n===== CHINH SUA THONG TIN CA NHAN =====");
            System.out.println("1. Sua Ho Ten          (Hien tai: " + khCanSua.getHoTen() + ")");
            System.out.println("2. Sua Ngay Sinh       (Hien tai: " + khCanSua.getNgaySinh() + ")");
            System.out.println("3. Sua So Dien Thoai   (Hien tai: " + khCanSua.getSdt() + ")");
            System.out.println("4. Sua Email           (Hien tai: " + khCanSua.getEmail() + ")");
            System.out.println("0. Thoat & Luu");
            System.out.print("Nhap lua chon cua ban: ");

            try {
                chonSua = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("(!) Lua chon phai la mot so.");
                chonSua = -1;
                continue;
            }

            switch (chonSua) {
                case 1:
                    System.out.print("Nhap Ho Ten moi: ");
                    khCanSua.setHoTen(sc.nextLine());
                    System.out.println("Cap nhat ho ten thanh cong!");
                    coThayDoi = true;
                    break;

                case 2:
                    while (true) {
                        try {
                            System.out.print("Nhap Ngay Sinh moi (yyyy-MM-dd): ");
                            String ngaySinhNhap = sc.nextLine();
                            LocalDate ngaySinhMoi = LocalDate.parse(ngaySinhNhap);
                            khCanSua.setNgaySinh(ngaySinhMoi);
                            System.out.println("Cap nhat ngay sinh thanh cong!");
                            coThayDoi = true;
                            break;
                        } catch (Exception e) {
                            System.out.println("(!) Dinh dang ngay khong hop le (yyyy-MM-dd).");
                        }
                    }
                    break;

                case 3:
                    String sdtMoi;
                    boolean sdtHopLe;
                    do {
                        sdtHopLe = true;
                        System.out.print("Nhap So Dien Thoai moi: ");
                        sdtMoi = sc.nextLine();
                        for (int i = 0; i < this.soLuongKH; i++) {
                            KhachHang otherKh = this.dsKhachHang[i];
                            if (otherKh != null && !otherKh.getMaKH().equalsIgnoreCase(khCanSua.getMaKH())) {
                                if (sdtMoi.equalsIgnoreCase(otherKh.getSdt())) {
                                    System.out.println("(!) So dien thoai nay da ton tai. Vui long nhap lai!");
                                    sdtHopLe = false;
                                    break;
                                }
                            }
                        }
                    } while (!sdtHopLe);

                    khCanSua.setSdt(sdtMoi);
                    System.out.println("Cap nhat so dien thoai thanh cong!");
                    coThayDoi = true;
                    break;

                case 4:
                    String emailMoi;
                    boolean emailHopLe;
                    do {
                        emailHopLe = true;
                        System.out.print("Nhap Email moi: ");
                        emailMoi = sc.nextLine();
                        for (int i = 0; i < this.soLuongKH; i++) {
                            KhachHang otherKh = this.dsKhachHang[i];
                            if (otherKh != null && !otherKh.getMaKH().equalsIgnoreCase(khCanSua.getMaKH())) {
                                if (emailMoi.equalsIgnoreCase(otherKh.getEmail())) {
                                    System.out.println("(!) Email nay da ton tai. Vui long nhap lai!");
                                    emailHopLe = false;
                                    break;
                                }
                            }
                        }
                    } while (!emailHopLe);

                    khCanSua.setEmail(emailMoi);
                    System.out.println("Cap nhat email thanh cong!");
                    coThayDoi = true;
                    break;

                case 0:
                    if (coThayDoi) {
                        this.write();
                        System.out.println("(*) Du lieu da duoc tu dong luu thanh cong!");
                    } else {
                        System.out.println("Khong co thay doi nao de luu.");
                    }
                    break;

                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chonSua != 0);
    }
    @Override
    public void timKiemChinhXac(String ma) {
        for (int i = 0; i < soLuongKH; i++) {
            if (dsKhachHang[i].getMaKH().equalsIgnoreCase(ma)) {
                dsKhachHang[i].xuat();
                return;
            }
        }
        System.out.println("Khong tim thay!");
    }

    @Override
    public void timKiemTuongDoi(String ten) {
        boolean found = false;
        for (int i = 0; i < soLuongKH; i++) {
            if (dsKhachHang[i].getHoTen().toLowerCase().contains(ten)) {
                dsKhachHang[i].xuat();
                found = true;
            }
        }
        if (!found) System.out.println("Khong co ket qua phu hop.");
    }

    @Override
    public void thongKeTheoKhoa() {
        System.out.println("Tong so luong khach hang: " + soLuongKH);
    }

    @Override
    public void nhapDanhSach() {
        System.out.print("Nhap so luong khach hang muon nhap: ");

        String input = sc.nextLine().trim();
        int n;
        try {
            n = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Nhap sai dinh dang!");
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("Khach hang thu " + (i + 1) + ":");
            themVaoDanhSach();
        }
    }

    @Override
    public void xuatDanhSach() {
        if (soLuongKH == 0) {
            System.out.println("Danh sach trong.");
            return;
        }

        System.out.println("\n" + "=".repeat(140));
        System.out.printf("| %-8s | %-20s | %-12s | %-25s | %-12s | %-18s | %-10s | %-15s | %-12s |\n",
                "MA KH", "HO TEN", "SDT", "EMAIL", "NGAY SINH", "VAI TRO", "HANG", "TONG CHI", "DIEM");
        System.out.println("-".repeat(140));

        for (int i = 0; i < soLuongKH; i++) {
            dsKhachHang[i].xuat();
        }
        System.out.println("=".repeat(140));
    }


    public void congDiem() {
        System.out.print("Nhap ma khach hang can cong diem (vi du: KH12345): ");
        String ma = sc.nextLine().trim();
        boolean found = false;
        for (int i = 0; i < soLuongKH; i++) {
            if (dsKhachHang[i].getMaKH().equalsIgnoreCase(ma)) {
                System.out.print("Nhap so diem muon cong them: ");
                try {
                    double diemCong = Double.parseDouble(sc.nextLine());
                    double diemMoi = dsKhachHang[i].getDiemTichLuy() + diemCong;
                    dsKhachHang[i].setDiemTichLuy(diemMoi);
                    CapNhapHang(dsKhachHang[i]);
                    System.out.println("Cong diem thanh cong! Diem hien tai: " + dsKhachHang[i].getDiemTichLuy());
                    System.out.println("Hang hien tai: " + dsKhachHang[i].getThuHang());
                } catch (Exception e) {
                    System.out.println("Loi: Diem phai la mot con so!");
                }
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Khong tim thay ma khach hang!");
    }

    public void nangCapHang() {
        System.out.println("--- DANG CAP NHAT HANG CHO TOAN BO DANH SACH ---");
        for (int i = 0; i < soLuongKH; i++) {
            CapNhapHang(dsKhachHang[i]);
        }
        System.out.println("Hoan tat nang cap hạng!");
    }
    public void CapNhapHang(KhachHang kh) {
        double diem = kh.getDiemTichLuy();
        if (diem < 5000) kh.setThuHang("Dong");
        else if (diem < 20000) kh.setThuHang("Bac");
        else if (diem < 50000) kh.setThuHang("Vang");
        else kh.setThuHang("Kim cuong");
    }

    public void xepHangVip() {
        if (soLuongKH == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        for (int i = 0; i < soLuongKH - 1; i++) {
            for (int j = i + 1; j < soLuongKH; j++) {
                if (dsKhachHang[i].getDiemTichLuy() < dsKhachHang[j].getDiemTichLuy()) {
                    KhachHang temp = dsKhachHang[i];
                    dsKhachHang[i] = dsKhachHang[j];
                    dsKhachHang[j] = temp;
                }
            }
        }
        System.out.println("--- DANH SACH SAU KHI XEP HANG VIP (DIEM CAO DUNG TRUOC) ---");
        xuatDanhSach();
    }

    public void hienThiVip() {
        if (soLuongKH == 0) {
            System.out.println("Danh sach trong.");
            return;
        }

        System.out.println("\n" + "=".repeat(77));
        System.out.println("                DANH SACH KHACH HANG VIP (VANG & KIM CUONG)");
        System.out.println("=".repeat(77));
        System.out.printf("| %-8s | %-20s | %-10s | %-12s | %-10s |\n",
                "MA KH", "HO TEN", "HANG", "TONG CHI", "DIEM");
        System.out.println("-".repeat(77));

        boolean coVip = false;
        for (int i = 0; i < soLuongKH; i++) {
            KhachHang kh = dsKhachHang[i];

            if (kh instanceof KhachHangVIP ||
                    kh.getThuHang().equalsIgnoreCase("Vang") ||
                    kh.getThuHang().equalsIgnoreCase("Kim cuong")) {
                System.out.printf("| %-8s | %-20s | %-10s | %-12.1f | %-10.1f |\n",
                        kh.getMaKH(),
                        kh.getHoTen(),
                        kh.getThuHang(),
                        kh.getTongChiTieu(),
                        kh.getDiemTichLuy()
                );
                coVip = true;
            }
        }

        if (!coVip) {
            System.out.println("Khong co khach hang nao dat tieu chuan VIP.");
        }
        System.out.println("=".repeat(155));
    }

    public void xuatLichSuMuaHangTuDanhSachKH() {
        if (soLuongKH == 0) {
            System.out.println("Danh sach khach hang trong.");
            return;
        }

        System.out.println("\n" + "=".repeat(105));
        System.out.println("                 LICH SU SAN PHAM DA MUA (THEO FILE KHACH HANG)");
        System.out.println("=".repeat(105));
        System.out.printf("| %-8s | %-20s | %-55s\n", "MA KH", "HO TEN", "CAC SAN PHAM DA MUA");
        System.out.println("-".repeat(105));

        for (int i = 0; i < soLuongKH; i++) {
            KhachHang kh = dsKhachHang[i];
            String spDaMua = kh.getLichSuMuaHang();

            if (spDaMua == null || spDaMua.isEmpty() || spDaMua.equalsIgnoreCase("null")) {
                spDaMua = "Chua co lich su mua san pham.";
            }
            System.out.printf("| %-8s | %-20s | %-55s\n",
                    kh.getMaKH(),
                    kh.getHoTen(),
                    spDaMua);
        }
        System.out.println("=".repeat(105));
    }

    @Override
    public void write() {
        try {
            java.io.File file = new java.io.File("D:\\Java\\QuanLyMyPham\\dsKhachHang.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < soLuongKH; i++) {
                bw.write(dsKhachHang[i].toDataString());
                bw.newLine();
            }

            bw.close();
            System.out.println("Luu file thanh cong!");
            System.out.println("Duong dan file: " + file.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public KhachHang timKhachHangTheoMa(String maKH) {
        for (int i = 0; i < soLuongKH; i++) {
            if (dsKhachHang[i] != null && dsKhachHang[i].getMaKH().equalsIgnoreCase(maKH)) {
                return dsKhachHang[i];
            }
        }
        return null;
    }

    @Override
    public void read() {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\Java\\QuanLyMyPham\\dsKhachHang.txt"))) {
            String line;
            this.soLuongKH = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(";");

                if (data.length >= 9) {
                    String thuHang = data[6].trim();
                    KhachHang kh;
                    if (thuHang.equalsIgnoreCase("Vang") || thuHang.equalsIgnoreCase("Kim cuong")) {
                        kh = new KhachHangVIP();
                    } else {
                        kh = new KhachHang();
                    }
                    kh.fromDataString(line);
                    if (kh instanceof KhachHangVIP) {
                    }
                    this.themVaoDanhSach(kh);
                }
            }
            System.out.println("Doc file khach hang thanh cong! So luong: " + soLuongKH);
        } catch (Exception e) {
            System.out.println("Loi doc file KH: " + e.getMessage());
        }
    }

    public void menu(QuanLyHoaDon qlhd) {
        this.read();
        int chon;
        do {
            System.out.println("\n===== MENU QUAN LY KHACH HANG =====");
            System.out.println("1. Nhap them khach hang (so luong lon)");
            System.out.println("2. Xuat danh sach khach hang");
            System.out.println("3. Xoa khach hang");
            System.out.println("4. Sua thong tin khach hang");
            System.out.println("5. Tim kiem theo Ma KH");
            System.out.println("6. Tim kiem theo Ten KH");
            System.out.println("7. Thong ke so luong khach hang");
            System.out.println("8. Sap xep danh sach (Diem giam dan)");
            System.out.println("9. Cong diem tich luy");
            System.out.println("10. Loc danh sach khach hang VIP");
            System.out.println("11. Cap nhat lai toan bo hang");
            System.out.println("12. Thong ke lich su mua hang tu danh sach hoa don");
            System.out.println("13. Xem lich su mua san pham tu danh sach Khach Hang");
            System.out.println("0. Thoat & Luu");
            System.out.print("Nhap lua chon cua ban: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                chon = -1;
            }

            switch (chon) {
                case 1: nhapDanhSach(); break;
                case 2: xuatDanhSach(); break;
                case 3:
                    System.out.print("Nhap ma khach hang can xoa: ");
                    String ma = sc.nextLine();
                    xoaKhoiDanhSach(ma);
                    write();
                    break;
                case 4:
                    System.out.print("Nhap ma khach hang can sua (VD: KH12345): ");
                    ma = sc.nextLine().trim();
                    suaDanhSach(ma);
                    write();
                    break;
                case 5:
                    System.out.print("Nhap ma KH: ");
                    ma = sc.nextLine().trim();
                    timKiemChinhXac(ma);
                    break;
                case 6:
                    System.out.print("Nhap ten KH: ");
                    String tuKhoa = sc.nextLine().toLowerCase().trim();
                    timKiemTuongDoi(tuKhoa);
                    break;
                case 7: thongKeTheoKhoa(); break;
                case 8: xepHangVip(); break;
                case 9: congDiem(); break;
                case 10: hienThiVip(); break;
                case 11: nangCapHang(); break;
                case 12:
                    thongKeLichSuMuaHang(qlhd);
                    break;
                case 13:
                    xuatLichSuMuaHangTuDanhSachKH();
                    break;
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
