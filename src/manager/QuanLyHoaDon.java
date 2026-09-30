package manager;
import model.*;
import QuanLyMyPham.iDocGhiFile;
import QuanLyMyPham.iQuanLyDanhSach;

import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

public class QuanLyHoaDon implements iQuanLyDanhSach, iDocGhiFile {
    private HoaDon[] dsHoaDon;
    private int soLuongHoaDon;
    private KhachHang[] dsKhachHang;
    private SanPham[] dsSanPham;
    private NhanVien[] dsNhanVien;
    private KhuyenMai[] dsKhuyenMai;
    private QuanLySanPham qlsp;
    private NhanVien nhanVienDangNhap;
    Scanner sc = new Scanner(System.in);

    public QuanLyHoaDon(){
        qlsp = new QuanLySanPham();
        qlsp.read();
        dsSanPham = qlsp.getDsSanPham();
    }

    public QuanLyHoaDon(HoaDon[] dsHoaDon, int soLuongHoaDon, KhachHang[] dsKhachHang, SanPham[] dsSanPham,
                        NhanVien[] dsNhanVien,KhuyenMai[] dsKhuyenMai, QuanLySanPham qlsp) {
        this.dsHoaDon = (dsHoaDon != null) ? dsHoaDon : new HoaDon[0];
        this.soLuongHoaDon = (dsHoaDon != null) ? Math.min(soLuongHoaDon, dsHoaDon.length) : 0;
        this.dsKhachHang = dsKhachHang;
        this.dsSanPham = dsSanPham;
        this.dsNhanVien = dsNhanVien;
        this.dsKhuyenMai = dsKhuyenMai;
        this.qlsp = qlsp;
        this.nhanVienDangNhap = nhanVienDangNhap;
    }
    public void loadSanPham() {
        try {
            QuanLySanPham qlsp = new QuanLySanPham();
            qlsp.read();
            dsSanPham = qlsp.getDsSanPham();

            System.out.println("Auto load dsSanPham: " +
                    (dsSanPham != null ? dsSanPham.length : 0));
        } catch (Exception e) {
            System.out.println("Loi load dsSanPham: " + e.getMessage());
        }
    }
    public HoaDon[] getDsHoaDon() {
        return dsHoaDon;
    }

    public void setDsHoaDon(HoaDon[] dsHoaDon) {
        this.dsHoaDon = dsHoaDon;
    }

    public int getSoLuongHoaDon() {
        return soLuongHoaDon;
    }

    public void setSoLuongHoaDon(int soLuongHoaDon) {
        this.soLuongHoaDon = soLuongHoaDon;
    }

    public KhachHang[] getDsKhachHang() {
        return dsKhachHang;
    }

    public void setDsKhachHang(KhachHang[] dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    public SanPham[] getDsSanPham() {
        return dsSanPham;
    }

    public void setDsSanPham(SanPham[] dsSanPham) {
        this.dsSanPham = dsSanPham;
    }

    public NhanVien[] getDsNhanVien() {
        return dsNhanVien;
    }

    public void setDsNhanVien(NhanVien[] dsNhanVien) {
        this.dsNhanVien = dsNhanVien;
    }
    public KhuyenMai[] getDsKhuyenMai(){
        return dsKhuyenMai;
    }
    public void setDsKhuyenMai(KhuyenMai[] dsKhuyenMai){
        this.dsKhuyenMai = dsKhuyenMai;
    }

    public QuanLySanPham getQlsp() {
        return qlsp;
    }

    public void setQlsp(QuanLySanPham qlsp) {
        this.qlsp = qlsp;
    }

    public NhanVien getNhanVienDangNhap() {
        return nhanVienDangNhap;
    }

    public void setNhanVienDangNhap(NhanVien nhanVienDangNhap) {
        this.nhanVienDangNhap = nhanVienDangNhap;
    }

    public boolean kiemTraTrungMa(String ma) {
        for (int i = 0; i < soLuongHoaDon; i++) {
            if (dsHoaDon[i] != null && dsHoaDon[i].getMaHD().equalsIgnoreCase(ma)) {
                return true;
            }
        }
        return false;
    }

    
    public void themVaoDanhSach() {
        if (this.dsKhachHang == null || this.dsKhachHang.length == 0) {
            QuanLyKhachHang qlkh = new QuanLyKhachHang();
            qlkh.read();
            this.dsKhachHang = qlkh.getDsKhachHang();
        }
        if (dsSanPham == null || dsSanPham.length == 0) {
            System.out.println("Canh bao: dsSanPham rong!");
        }
        if (this.dsNhanVien == null || this.dsNhanVien.length == 0) {
            QuanLyNhanVien qlnv = new QuanLyNhanVien();
            qlnv.read();
            this.dsNhanVien= qlnv.getDsNhanVien();
        }
        if (this.dsKhuyenMai == null || this.dsKhuyenMai.length == 0) {
            QuanLyKM qlkm = new QuanLyKM();
            qlkm.read();
            this.dsKhuyenMai= qlkm.getDsKhuyenMai();
        }
        HoaDon hd = new HoaDon();
        hd.setNhanVien(nhanVienDangNhap);
        hd.nhap(dsKhachHang, dsSanPham, dsNhanVien, this, dsKhuyenMai);
        
        if (soLuongHoaDon >= dsHoaDon.length) {
            dsHoaDon = Arrays.copyOf(dsHoaDon, dsHoaDon.length + 10);
        }
        dsHoaDon[soLuongHoaDon++] = hd;
        System.out.println(" Da them hoa don moi thanh cong!");
        hoiGhiFile();
        qlsp.write();
    }

    
    public void suaDanhSach(String ma) {
        for (int i = 0; i < soLuongHoaDon; i++) {
            if (dsHoaDon[i] != null && dsHoaDon[i].getMaHD().equalsIgnoreCase(ma)) {
                HoaDon hd = dsHoaDon[i];
                LocalDate ngayHienTai = LocalDate.now();
                LocalDate ngayLap = hd.getNgayLap();
                int gioiHanNgay = 1;
                if(ngayLap == null){
                    System.out.println("Hoa don khong co ngay lap, khong tim thay!");
                    return;
                }
                if (ngayLap.plusDays(gioiHanNgay).isBefore(ngayHienTai)) {
                    System.out.println("Hoa don da qua " + gioiHanNgay + " ngay, khong the sua!");
                    return;
                }

                if (hd.getTrangThai().equals(HoaDon.DA_THANH_TOAN)) {
                    System.out.println("Khong the sua hoa don da thanh toan!");
                    return;
                }

                if (hd.getTrangThai().equals(HoaDon.DA_HUY)) {
                    System.out.println("Khong the sua hoa don da huy!");
                    return;
                }
                hd.xuat();
                int chon;
                do {
                    System.out.println("\n--- SUA HOA DON " + ma + " ---");
                    System.out.println("1. Them san pham");
                    System.out.println("2. Sua so luong san pham");
                    System.out.println("3. Xoa san pham");
                    System.out.println("0. Thoat");

                    System.out.print("Chon: ");
                    chon = Integer.parseInt(sc.nextLine());
                    switch (chon) {
                        case 1:
                            System.out.print("Nhap ten hoac ma san pham: ");
                            String input = sc.nextLine().trim().toLowerCase();

                            SanPham spFound = null;

                            for (SanPham sp : dsSanPham) {
                                if (sp != null && sp.getMaSP().equalsIgnoreCase(input)) {
                                    spFound = sp;
                                    break;
                                }
                            }

                            if (spFound == null) {
                                System.out.println("Danh sach san pham tim duoc:");
                                boolean co = false;

                                for (SanPham sp : dsSanPham) {
                                    if (sp != null && sp.getTenSanPham().toLowerCase().contains(input)) {
                                        System.out.println(sp.getMaSP() + " | " + sp.getTenSanPham());
                                        co = true;
                                    }
                                }

                                if (!co) {
                                    System.out.println("Khong tim thay!");
                                    break;
                                }

                                System.out.print("Nhap ma chon: ");
                                String maChon = sc.nextLine();

                                for (SanPham sp : dsSanPham) {
                                    if (sp != null && sp.getMaSP().equalsIgnoreCase(maChon)) {
                                        spFound = sp;
                                        break;
                                    }
                                }
                            }

                            if (spFound == null) {
                                System.out.println("Khong hop le!");
                                break;
                            }

                            System.out.print("Nhap so luong: ");
                            int sl = Integer.parseInt(sc.nextLine());

                            hd.themSanPham(spFound, sl);
                            break;
                        case 2:
                            System.out.print("Nhap ten hoac ma san pham: ");
                            String input2 = sc.nextLine().trim().toLowerCase();

                            String maSP2 = null;

                            for (SanPham sp : dsSanPham) {
                                if (sp != null && sp.getMaSP().equalsIgnoreCase(input2)) {
                                    maSP2 = sp.getMaSP();
                                    break;
                                }
                            }

                            if (maSP2 == null) {
                                System.out.println("Danh sach tim duoc:");
                                for (SanPham sp : dsSanPham) {
                                    if (sp != null && sp.getTenSanPham().toLowerCase().contains(input2)) {
                                        System.out.println(sp.getMaSP() + " | " + sp.getTenSanPham());
                                    }
                                }

                                System.out.print("Nhap ma chon: ");
                                maSP2 = sc.nextLine();
                            }

                            System.out.print("Nhap so luong moi: ");
                            int slMoi = Integer.parseInt(sc.nextLine());

                            hd.suaSanPham(maSP2, slMoi);
                            break;
                        case 3:
                            System.out.print("Nhap ten hoac ma san pham: ");
                            String input3 = sc.nextLine().trim().toLowerCase();

                            String maSP3 = null;

                            for (SanPham sp : dsSanPham) {
                                if (sp != null && sp.getMaSP().equalsIgnoreCase(input3)) {
                                    maSP3 = sp.getMaSP();
                                    break;
                                }
                            }

                            if (maSP3 == null) {
                                System.out.println("Danh sach tim duoc:");
                                for (SanPham sp : dsSanPham) {
                                    if (sp != null && sp.getTenSanPham().toLowerCase().contains(input3)) {
                                        System.out.println(sp.getMaSP() + " | " + sp.getTenSanPham());
                                    }
                                }

                                System.out.print("Nhap ma chon: ");
                                maSP3 = sc.nextLine();
                            }

                            hd.xoaSanPham(maSP3);
                            break;
                    }
                } while (chon != 0);
                System.out.println("Cap nhat thanh cong!");
                hoiGhiFile();
                return;
            }
        }
        System.out.println("Khong tim thay hoa don!");
    }

    
    public void xoaKhoiDanhSach(String ma) {
        for (int i = 0; i < soLuongHoaDon; i++) {
            if (dsHoaDon[i] != null && dsHoaDon[i].getMaHD().equalsIgnoreCase(ma)) {
                if(HoaDon.DA_THANH_TOAN.equals(dsHoaDon[i].getTrangThai())){
                    System.out.println("Hoa don da thanh toan, khong the xoa! ");
                    return;
                }
                for (int j = i; j < soLuongHoaDon - 1; j++) {
                    dsHoaDon[j] = dsHoaDon[j + 1];
                }

                dsHoaDon[--soLuongHoaDon] = null;
                System.out.println("Xoa thanh cong! ");
                hoiGhiFile();
                return;
            }
        }
        System.out.println("Khong tim thay!");
    }

    
    public void timKiemChinhXac(String ma) {
        for (int i = 0; i < soLuongHoaDon; i++) {
            if (dsHoaDon[i] != null && dsHoaDon[i].getMaHD().equalsIgnoreCase(ma)) {
                dsHoaDon[i].xuat();
                return;
            }
        }
        System.out.println("Khong tim thay!");
    }

    
    public void timKiemTuongDoi(String tuKhoa){
        String keyword = tuKhoa.toLowerCase().trim();

        boolean timThay = false;
        for(int i = 0; i< soLuongHoaDon; i++){
            HoaDon hd = dsHoaDon[i];
            if(hd == null) continue;
            String maHD = hd.getMaHD().toLowerCase();
            String maKH = (hd.getThongTinKH() != null) ? hd.getThongTinKH().getMaKH().toLowerCase(): "";
            String maNV = (hd.getNhanVien() != null) ? hd.getNhanVien().getMaNhanVien().toLowerCase() : "";

            if (maHD.contains(keyword) || maKH.contains(keyword) || maNV.contains(keyword)) {
                hd.xuat();
                timThay = true;
            }
        }
        if (!timThay) {
            System.out.println("Khong tim thay ket qua phu hop!");
        }
    }

    
    public void thongKeTheoKhoa() {
        double tongDoanhThu = 0;
        int daThanhToan = 0;
        int chuaThanhToan = 0;
        int daHuy = 0;

        for (int i = 0; i < soLuongHoaDon; i++) {
            HoaDon hd = dsHoaDon[i];
            if(hd == null) continue;

            if (hd.getTrangThai().equals(HoaDon.DA_THANH_TOAN)) {
                tongDoanhThu += hd.tinhTongTien();
                daThanhToan++;
            } else if (hd.getTrangThai().equals(HoaDon.CHUA_THANH_TOAN)) {
                chuaThanhToan++;
            } else if (hd.getTrangThai().equals(HoaDon.DA_HUY)) {
                daHuy++;
            }
        }

        System.out.println("===== THONG KE =====");
        System.out.println("Tong doanh thu: " + tongDoanhThu);
        System.out.println("Da thanh toan: " + daThanhToan);
        System.out.println("Chua thanh toan: " + chuaThanhToan);
        System.out.println("Da huy: " + daHuy);
    }

    public void hienThiDanhSach() {
        if (soLuongHoaDon == 0) {
            System.out.println("Danh sach rong!");
            return;
        }

        for (int i = 0; i < soLuongHoaDon; i++) {
            dsHoaDon[i].xuat();
        }
    }

    public void thongKeTatCaTheoThang() {
        double[][] doanhThu = new double[100][13];

        for (int i = 0; i < soLuongHoaDon; i++) {
            HoaDon hd = dsHoaDon[i];

            if (hd.getTrangThai().equals(HoaDon.DA_THANH_TOAN)) {
                int nam = hd.getNgayLap().getYear();
                int thang = hd.getNgayLap().getMonthValue();

                doanhThu[nam % 100][thang] += hd.tinhTongTien();
            }
        }

        System.out.println("===== DOANH THU THEO THANG =====");
        for (int i = 0; i < 100; i++) {
            for (int j = 1; j <= 12; j++) {
                if (doanhThu[i][j] > 0) {
                    System.out.println("Nam " + (2000 + i) + " - Thang " + j + ": " + doanhThu[i][j]);

                }
            }
        }
    }
    public void menu() {
        int chon;
        do {
            System.out.println("\n===== QUAN LY HOA DON =====");
            System.out.println("1. Them hoa don");
            System.out.println("2. Sua hoa don");
            System.out.println("3. Xoa hoa don");
            System.out.println("4. Tim kiem chinh xac hoa don");
            System.out.println("5. Tim kiem tuong doi hoa don");
            System.out.println("6. Hien thi danh sach");
            System.out.println("7. Nhap danh sach hoa don");
            System.out.println("8. Thong ke ");
            System.out.println("9. Doanh thu tat ca cac thang");
            System.out.println("10. Thao tac chi tiet tren hoa don");
            System.out.println("0. Thoat");

            System.out.print("Chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1: themVaoDanhSach(); break;
                case 2: 
                    System.out.println("Nhap ma hoa don can sua: ");
                    suaDanhSach(sc.nextLine()); break;
                case 3: 
                    System.out.println("Nhap ma hoa don can xoa: ");
                    xoaKhoiDanhSach(sc.nextLine()); break;
                case 4: 
                    System.out.println("Nhap ma hoa don can tim kiem: ");
                    timKiemChinhXac(sc.nextLine()); break;
                case 5:
                    System.out.println("Nhap maHD, hoac maNV, hoac maKH: "); 
                    timKiemTuongDoi(sc.nextLine()); break;
                case 6: hienThiDanhSach(); break;
                case 7: nhapDanhSach(); break;
                case 8: thongKeTheoKhoa(); break;
                case 9: thongKeTatCaTheoThang(); break;
                case 10: menuThaoTacChiTiet(); break;
                case 0: System.out.println("Thoat!"); break;
                default: System.out.println("Lua chon khong hop le!");
            }

        } while (chon != 0);
    }
    public void menuThaoTacChiTiet(){
        System.out.println("Nhap ma hoa don can thao tac: ");
        String ma = sc.nextLine();

        HoaDon hd = null;
        for(int i=0; i < soLuongHoaDon; i++){
            if(dsHoaDon[i] != null && dsHoaDon[i].getMaHD().equalsIgnoreCase(ma)){
                hd = dsHoaDon[i];
                break;
            }
        }
        if (hd == null){
            System.out.println("Khong tim thay hoa don!");
            return;
        }
        int chon;
        do {
            System.out.println("-----THAO TAC TREN HOA DON: "+ ma + "-----");
            System.out.println("1. Xem chi tiet hoa don");
            System.out.println("2. Thanh toan");
            System.out.println("3. Huy hoa don");
            System.out.println("0. Thoat");

            System.out.print("Chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    hd.xuat();
                    break;

                case 2:
                    if (hd.getTrangThai().equalsIgnoreCase(HoaDon.DA_THANH_TOAN)) {
                        System.out.println("Hoa don da thanh toan roi!");
                    } else if (hd.getTrangThai().equalsIgnoreCase(HoaDon.DA_HUY)) {
                        System.out.println("Hoa don da bi huy, khong the thanh toan!");
                    } else {
                        hd.thanhToan();
                        if (hd.getThongTinKH() != null && this.dsKhachHang != null) {
                            KhachHang kh = hd.getThongTinKH();

                            double tongTienHD = hd.tinhTongTien();
                            kh.setTongChiTieu(kh.getTongChiTieu() + tongTienHD);

                            int diemCongThem = (int) (tongTienHD * 0.0001);
                            kh.setDiemTichLuy(kh.getDiemTichLuy() + diemCongThem);

                            StringBuilder sbSP = new StringBuilder();
                            for (int j = 0; j < hd.getSoLuongThucTe(); j++) {
                                if (hd.getDsSanPham()[j] != null) {
                                    sbSP.append(hd.getDsSanPham()[j].getTenSanPham())
                                            .append(" (SL: ").append(hd.getSoLuong()[j]).append(")");
                                    if (j < hd.getSoLuongThucTe() - 1) {
                                        sbSP.append(", ");
                                    }
                                }
                            }
                            String spMoiMua = sbSP.toString();
                            String lsCu = kh.getLichSuMuaHang();
                            if (lsCu == null || lsCu.isEmpty() || lsCu.equalsIgnoreCase("null") || lsCu.equalsIgnoreCase("Chua co")) {
                                kh.setLichSuMuaHang(spMoiMua);
                            } else {
                                kh.setLichSuMuaHang(lsCu + " - " + spMoiMua);
                            }
                            try {
                                QuanLyKhachHang qlkh = new QuanLyKhachHang();
                                qlkh.setDsKhachHang(this.dsKhachHang);
                                qlkh.write(); 
                                System.out.println("-> Da cap nhat Chi tieu, Diem tich luy va Lich su cho KH: " + kh.getHoTen());
                            } catch (Exception e) {
                                System.out.println(" Loi khi luu thong tin khach hang xuong file: " + e.getMessage());
                            }
                        } else {
                            System.out.println("-> Hoa don khong co thong tin khach hang (Khach vang lai), bo qua tich diem.");
                        }

                        System.out.println(" Thanh toan hoa don thanh cong!");
                        hoiGhiFile();
                        if (qlsp != null) {
                            qlsp.write();
                        }
                    }
                    break;

                case 3:
                    if(hd.getTrangThai().equals(HoaDon.DA_THANH_TOAN)){
                        System.out.println("Hoa don da thanh toan khong the huy!");
                    }
                    if(hd.getTrangThai().equals(HoaDon.DA_HUY)){
                        System.out.println("Hoa don da huy roi!");
                    } 
                    if(hd.getTrangThai().equals(HoaDon.CHUA_THANH_TOAN)){
                        System.out.print("Ban chac chan huy? (y/n): ");
                        if(sc.nextLine().equalsIgnoreCase("y")){
                            hd.huyHoaDon();
                            System.out.println("Da huy hoa don!");
                            hoiGhiFile();
                        }
                    }
                    break;
                case 0: System.out.println("Thoat!"); break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while(chon != 0);
    }

    public void xuatDanhSach() {
        for(int i = 0 ; i < soLuongHoaDon ; i++ ) {
            if(dsHoaDon[i] != null && dsHoaDon[i].DA_THANH_TOAN() == true )
                 dsHoaDon[i].xuat();
        }
    }
    public void hoiGhiFile() {
        System.out.print("Ban co muon ghi file khong? (1 = Co, 2 = Khong): ");
        String chon = sc.nextLine();
        if (chon.equals("1")) {
            write();
            System.out.println("Da ghi file!");
        } else {
            System.out.println("Chua ghi file.");
        }
    }

    
    public void read() {
        try {
            
            if (this.dsSanPham == null || this.dsSanPham.length == 0) {
                QuanLySanPham qlsp = new QuanLySanPham();
                qlsp.read();
                this.dsSanPham = qlsp.getDsSanPham();
            }
            if (this.dsNhanVien == null || this.dsNhanVien.length == 0) {
                QuanLyNhanVien qlnv = new QuanLyNhanVien(); // Đảm bảo bạn có class này
                qlnv.read();
                this.dsNhanVien = qlnv.getDsNhanVien();
            }
            if (this.dsKhachHang == null || this.dsKhachHang.length == 0) {
                QuanLyKhachHang qlkh = new QuanLyKhachHang();
                qlkh.read();
                this.dsKhachHang = qlkh.getDsKhachHang();
            }
            if (this.dsKhuyenMai == null || this.dsKhuyenMai.length == 0) {
                QuanLyKM qlkm = new QuanLyKM();
                qlkm.read();
                this.dsKhuyenMai = qlkm.getDsKhuyenMai();
            }

            File f = new File("D:\\Java\\QuanLyMyPham\\Danh Sach file\\dsHoaDon.txt");
            if (!f.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            this.soLuongHoaDon = 0;
            this.dsHoaDon = new HoaDon[100]; // Reset mảng tránh lỗi null

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(";");
                if (data.length < 6) continue;

                HoaDon hd = new HoaDon();
                hd.setMaHD(data[0]);
                hd.setNgayLap(LocalDate.parse(data[1].trim()));
                hd.setThongTinKH(timKH(data[2]));
                hd.setNhanVien(timNV(data[3]));
                hd.setTrangThai(data[4]);
                hd.setKmHoaDon(timKM(data[5]));

                if (data.length > 6) {
                    String[] items = data[6].split("\\|");
                    int n = items.length;

                    SanPham[] spHD = new SanPham[n];
                    int[] slHD = new int[n];
                    KhuyenMai[] kmSPHD = new KhuyenMai[n];

                    for (int i = 0; i < n; i++) {
                        String[] details = items[i].split(",");
                        if (details.length < 2) continue;

                        spHD[i] = timSP(details[0].trim());
                        slHD[i] = Integer.parseInt(details[1].trim());
                        if (details.length > 2) {
                            kmSPHD[i] = timKM(details[2].trim());
                        }
                    }

                    hd.setDsSanPham(spHD);
                    hd.setSoLuong(slHD);
                    hd.setKmSanPham(kmSPHD);
                    hd.setSoLuongThucTe(n);
                    hd.tuDongChonKhuyenMai(this.dsKhuyenMai);
                }

                if (soLuongHoaDon >= dsHoaDon.length) {
                    dsHoaDon = Arrays.copyOf(dsHoaDon, dsHoaDon.length + 50);
                }
                dsHoaDon[soLuongHoaDon++] = hd;

            }
            br.close();
            System.out.println("Da nap " + soLuongHoaDon + " hoa don.");
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
            e.printStackTrace(); // In ra để xem chính xác lỗi dòng nào
        }
    }


    private KhachHang timKH(String ma) {
        if (ma == null || ma.equalsIgnoreCase("null") || this.dsKhachHang == null) return null;
        for (KhachHang kh : this.dsKhachHang) {
            if (kh != null && kh.getMaKH() != null && kh.getMaKH().equalsIgnoreCase(ma)) return kh;
        }
        return null;
    }

    private NhanVien timNV(String ma) {
        if (ma == null || ma.equalsIgnoreCase("null") || this.dsNhanVien == null) return null;
        for (NhanVien nv : this.dsNhanVien) {
            if (nv != null && nv.getMaNhanVien() != null && nv.getMaNhanVien().equalsIgnoreCase(ma)) return nv;
        }
        return null;
    }

    private KhuyenMai timKM(String ma) {
        if (ma == null 
            || ma.equalsIgnoreCase("null")
            || ma.equalsIgnoreCase("Khong co")
            || this.dsKhuyenMai == null) {
            return null;
        }

        for (KhuyenMai km : this.dsKhuyenMai) {
            if (km != null 
                && km.getMaKM() != null 
                && km.getMaKM().equalsIgnoreCase(ma)) {
                return km;
            }
        }
        return null;
    }
    private SanPham timSP(String ma) {
        if (ma == null || ma.equalsIgnoreCase("null") || this.dsSanPham == null) return null;
        for (SanPham sp : this.dsSanPham) {
            if (sp != null && sp.getMaSP() != null && sp.getMaSP().equalsIgnoreCase(ma)) return sp;
        }
        return null;
    }

    public void write() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Java\\QuanLyMyPham\\dsHoaDon.txt"));

            for (int i = 0; i < soLuongHoaDon; i++) {
                HoaDon hd = dsHoaDon[i];
                if (hd == null) continue;

                StringBuilder sbSP = new StringBuilder();    
                for (int j = 0; j < hd.getSoLuongThucTe(); j++) {
                    if (hd.getDsSanPham()[j] == null) continue;

                    sbSP.append(hd.getDsSanPham()[j].getMaSP()).append(",")
                             .append(hd.getSoLuong()[j]).append(",")
                             .append(hd.getKmSanPham()[j] != null 
                                ? hd.getKmSanPham()[j].getMaKM() 
                                : "Khong co");

                    if (j < hd.getSoLuongThucTe() - 1) {
                        sbSP.append("|");
                    }
                }

                String line = String.format("%s;%s;%s;%s;%s;%s;%s",
                        hd.getMaHD(),
                        hd.getNgayLap(),
                        (hd.getThongTinKH() != null ? hd.getThongTinKH().getMaKH() : "null"),
                        (hd.getNhanVien() != null ? hd.getNhanVien().getMaNhanVien() : "null"),
                        hd.getTrangThai(),
                        (hd.getKmHoaDon() != null 
                        ? hd.getKmHoaDon().getMaKM() : "Khong co"),
                        sbSP.toString()
                );

                bw.write(line);
                bw.newLine();
            }
            bw.close();
            System.out.println("Ghi file thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }



    
    public void nhapDanhSach() {
        System.out.print("Nhap so luong hoa don muon them: ");
        int soLuongThem = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < soLuongThem; i++){
            System.out.println("\n--- Nhap hoa don thu " + (i + 1) + " ---");
            HoaDon hd = new HoaDon();
            hd.setNhanVien(nhanVienDangNhap);

            hd.nhap(dsKhachHang, dsSanPham, dsNhanVien, this, dsKhuyenMai);

            if (soLuongHoaDon < dsHoaDon.length) {
                dsHoaDon[soLuongHoaDon++] = hd;
            }
        }
    }

}
