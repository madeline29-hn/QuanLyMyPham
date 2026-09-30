package model;
import manager.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
public class PhieuNhap {
    private LocalDate ngayNhap;
    private NhanVien nv;
    private NhaCungCap nCC;
    private SanPham[] dsSanPham = new SanPham[50];
    private String maPhieuNhap;
    private int soLuongSP;
    private boolean trangThai;
    private double tongTien;
    private QuanLySanPham qlsp;
    static Scanner sc = new Scanner(System.in);

    public PhieuNhap() {}


    public PhieuNhap(LocalDate ngayNhap, NhanVien nv, NhaCungCap nCC, SanPham[] dsSanPham, String maPhieuNhap,
                     int soLuongSP,double tongTien, boolean trangThai) {
        this.ngayNhap = ngayNhap;
        this.nv = nv;
        this.nCC = nCC;
        this.dsSanPham = dsSanPham;
        this.maPhieuNhap = maPhieuNhap;
        this.soLuongSP = soLuongSP;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }
    public QuanLySanPham getQlsp() {
        return qlsp;
    }
    public void setQlsp(QuanLySanPham qlsp) {
        this.qlsp = qlsp;
    }
    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }
    public LocalDate getNgayNhap() {
        return ngayNhap;
    }
    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    public NhanVien getNhanVien() {
        return nv;
    }
    public void setNhanVien(NhanVien nv) {
        this.nv = nv;
    }
    public NhaCungCap getNhaCungCap() {
        return nCC;
    }
    public void setNhaCungCap(NhaCungCap nCC) {
        this.nCC = nCC;
    }
    public SanPham[] getDsSanPham() {
        return dsSanPham;
    }
    public void setDsSanPham(SanPham[] dsSanPham) {
        this.dsSanPham = dsSanPham;
    }
    public boolean getTrangThai() {
        return trangThai;
    }
    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuong) {
        this.soLuongSP = soLuong;
    }
    public double getTongTien() {
        return tongTien;
    }
    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    public void themSanPham(SanPham sp) {
        if (sp == null) {
            return;
        }

        for (int i = 0; i < soLuongSP; i++) {
            if (dsSanPham[i] != null && dsSanPham[i].getMaSP().equalsIgnoreCase(sp.getMaSP())) {
                dsSanPham[i].setSoLuong(dsSanPham[i].getSoLuong() + sp.getSoLuong());
                return;
            }
        }

        if (soLuongSP >= dsSanPham.length) {
            dsSanPham = Arrays.copyOf(dsSanPham, dsSanPham.length + 5);
        }

        dsSanPham[soLuongSP] = sp;
        soLuongSP++;
    }
    public SanPham timKiemSP(String maSP) {
        for (SanPham sp : dsSanPham) {
            if (sp != null && sp.getMaSP().equalsIgnoreCase(maSP)) {
                System.out.println("San pham tim thay: " + sp);
                return sp;
            }
        }
        System.out.println("Khong tim thay san pham co ma " + maSP);
        return null;
    }
//    public void suaSanPham() {
//        System.out.println("Nhap ma san pham can sua: ");
//        String maSP = sc.nextLine().trim();
//        for (int i = 0; i < dsSanPham.length; i++) {
//            if (dsSanPham[i] != null && dsSanPham[i].getMaSP().equalsIgnoreCase(maSP)) {
//                System.out.println("Thong tin hien tai cua san pham: " + dsSanPham[i].toString());
//                System.out.println("Nhap so luong moi: ");
//                int soLuongMoi = Integer.parseInt(sc.nextLine().trim());
//                dsSanPham[i].setSoLuong(soLuongMoi);
//                System.out.println("So luong san pham " + maSP + " da duoc cap nhat.");
//            }
//            else {
//                System.out.println("Khong tim thay san pham co ma " + maSP + " trong phieu nhap!");
//            }
//        }
//    }

    public void suaSanPham() {
        System.out.println("Nhap ma san pham can sua: ");
        String maSP = sc.nextLine().trim();

        boolean timThay = false; 

        for (int i = 0; i < soLuongSP; i++) {
            if (dsSanPham[i] != null && dsSanPham[i].getMaSP().equalsIgnoreCase(maSP)) {
                System.out.println("Thong tin hien tai cua san pham: " + dsSanPham[i].toString());

                System.out.print("Nhap so luong moi: ");
                int soLuongMoi = Integer.parseInt(sc.nextLine().trim());
                dsSanPham[i].setSoLuong(soLuongMoi);

                System.out.println("So luong san pham " + maSP + " da duoc cap nhat.");
                timThay = true; 
                break;
            }
        }


        if (!timThay) {
            System.out.println("Khong tim thay san pham co ma " + maSP + " trong phieu nhap!");
        }
    }

    public void soLuongMaxMin(String maSP) {
        
        for ( int i = 0; i < dsSanPham.length ; i++) { 
            if(dsSanPham[i] != null && dsSanPham[i].getMaSP().equals(maSP)) {
                
                
                
            }

        }
    }
    public void xoaSanPham(String maSPXoa) {
        boolean timThay = false;
        for (int i = 0; i < dsSanPham.length; i++) {
            if (dsSanPham[i] != null && dsSanPham[i].getMaSP().equalsIgnoreCase(maSPXoa)) {
                for (int j = i; j < dsSanPham.length - 1; j++) {
                    dsSanPham[j] = dsSanPham[j + 1];
                }
                dsSanPham[dsSanPham.length - 1] = null;
                soLuongSP--;
                System.out.println("San pham " + maSPXoa + " da duoc xoa.");
                timThay = true;
            }
            if (!timThay && dsSanPham[i] == null) {
                System.out.println("Khong tim thay san pham co ma " + maSPXoa);
                break;
            }
        }
        System.out.println("Khong tim thay san pham co ma " + maSPXoa);
    }


    public int tongSoLuong() {
        int tong = 0;
        for (SanPham sp : dsSanPham) {
            if (sp != null) {
                tong += sp.getSoLuong();
            }
        }
        return tong ;
    }
    public double tongTien() {
        double tong = 0;
        for (int i = 0; i < dsSanPham.length; i++) {
            if (dsSanPham[i] != null) {
                tong += dsSanPham[i].getSoLuong() * dsSanPham[i].getGia();
            }
        }
        return this.tongTien = tong;
    }
    public void ganNhanVienDangNhap(NhanVien nvDangNhap) {
        this.nv = nvDangNhap;
    }
    public void nhapThongTinChung(NhanVien[] dsNhanVien) {
        if (nv != null) {
            System.out.println("Nhan vien thuc hien: " + nv.getMaNhanVien() + " - " + nv.getHoTen());
        }
        if(nCC == null) {
            nCC = new NhaCungCap();
        }
        nCC.nhap();
        System.out.println("Ma nha cung cap : " + nCC.getMaNCC());
        System.out.println("Ten nha cung cap: " + nCC.getTenNCC());
        LocalDate now = LocalDate.now();
        setNgayNhap(now);
    }
    public int capNhatKho(SanPham[] dsSanPhamGoc, int soLuongSPGoc) {
    if (!trangThai) return soLuongSPGoc;

    for (int i = 0; i < soLuongSP; i++) {
        SanPham spTrongPhieu = dsSanPham[i];
        if (spTrongPhieu == null) continue;

        boolean timThay = false;
        for (int j = 0; j < soLuongSPGoc; j++) {
            if (dsSanPhamGoc[j] != null && dsSanPhamGoc[j].getMaSP().equalsIgnoreCase(spTrongPhieu.getMaSP())) {
                dsSanPhamGoc[j].setSoLuong(dsSanPhamGoc[j].getSoLuong() + spTrongPhieu.getSoLuong());
                timThay = true;
                break;
            }
        }

        if (!timThay) {
            SanPham spMoiChoKho = new SanPham(
                    spTrongPhieu.getMaSP(), spTrongPhieu.getTenSanPham(), spTrongPhieu.getThuongHieu(),
                    spTrongPhieu.getPhanLoai(), spTrongPhieu.getGia(), spTrongPhieu.getSoLuong(),
                    spTrongPhieu.getNgaySX(), spTrongPhieu.getNgayHH(), spTrongPhieu.getNuocSX(),
                    spTrongPhieu.getNhaCungCap(), spTrongPhieu.getTrangThai(), spTrongPhieu.getCongDung()
            );
            dsSanPhamGoc[soLuongSPGoc] = spMoiChoKho;
            soLuongSPGoc++;
        }
    }
    return soLuongSPGoc;
}

    public void nhap(SanPham[] dsSanPhamGoc) {
        for (int i = 0; i < dsSanPham.length; i++) {
            System.out.println("\n--- Nhap san pham thu " + (i + 1) + " ---");
            String maSPCheck = "";

            while (true) {
                System.out.print("Nhap ma san pham (SPxxxxx): ");
                maSPCheck = sc.nextLine().trim();
                if (maSPCheck.matches("SP\\d{5}")) break;
                System.out.println("Loi: Ma phai co dang SP + 5 chu so!");
            }

            SanPham spTimThay = null;
            for (SanPham sp : dsSanPhamGoc) {
                if (sp != null && sp.getMaSP() != null && sp.getMaSP().equalsIgnoreCase(maSPCheck)) {
                    spTimThay = sp;
                    break;
                }
            }

            SanPham spMoi = new SanPham();
            spMoi.setMaSP(maSPCheck);

            if (spTimThay != null) {
                System.out.println("=> San pham: " + spTimThay.getTenSanPham());
                System.out.println("=> Thuong hieu: " + spTimThay.getThuongHieu());

                int sl = 0;
                while (true) {
                    System.out.print("Nhap so luong muon nhap: ");
                    sl = Integer.parseInt(sc.nextLine().trim());
                    if (sl > 0) {
                        break;
                    }
                    System.out.println("Loi: So luong phai lon hon 0. Vui long nhap lai!");
                }

                boolean daCoTrongPhieu = false;
                for (int j = 0; j < soLuongSP; j++) {
                    if (dsSanPham[j] != null && dsSanPham[j].getMaSP().equalsIgnoreCase(maSPCheck)) {
                        dsSanPham[j].setSoLuong(dsSanPham[j].getSoLuong() + sl);
                        daCoTrongPhieu = true;
                        System.out.println("Da cong don so luong vao san pham co san.");
                        break;
                    }
                }

                if (!daCoTrongPhieu) {
                    spMoi.setMaSP(spTimThay.getMaSP());
                    spMoi.setTenSanPham(spTimThay.getTenSanPham());
                    spMoi.setThuongHieu(spTimThay.getThuongHieu());
                    spMoi.setGia(spTimThay.getGia());
                    spMoi.setSoLuong(sl);

                    themSanPham(spMoi);
                    System.out.println("Da them moi san pham vao phieu.");
                }
            } else {
                System.out.println("Ma san pham " + maSPCheck + " khong ton tai trong he thong!");
                spMoi.nhap();
                while (true) {
                    System.out.print("Nhap so luong: ");
                    try {
                        int soLuong = Integer.parseInt(sc.nextLine().trim());
                        if (soLuong >= 0) {
                            spMoi.setSoLuong(soLuong);
                            break;
                        } else {
                            System.out.println("Loi: So luong phai >= 0");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Loi: So luong phai la so nguyen.");
                    }
                }
                themSanPham(spMoi);
            }

            System.out.print("Ban co muon nhap tiep khong? (1: Tiep tuc / 0: Dung): ");
            if (!sc.nextLine().trim().equals("1")) break;
            soLuongSP = getSoLuongSP();
            tongTien = tongTien();
        }

        System.out.print("Trang thai phieu (1: Da thanh toan / 0: Chua): ");
        while (true) {
            String tt = sc.nextLine().trim();
            if (tt.equals("1")) { setTrangThai(true); break; }
            else if (tt.equals("0")) { setTrangThai(false); break; }
            else System.out.print("Nhap lai (1/0): ");
        }
    }

    public String outputFile() {
        String s = maPhieuNhap + ";" + ngayNhap + ";" + nv.getMaNhanVien() + ";" +
                nv.getHoTen() + ";" + nCC.getMaNCC() + ";" +
                nCC.getTenNCC() + ";" + soLuongSP + ";" + tongTien + ";" + (trangThai ? "1" : "0");

        for (int i = 0; i < soLuongSP; i++) {
            if (dsSanPham[i] != null) {
                s += ";" + dsSanPham[i].getMaSP() + ";" + dsSanPham[i].getTenSanPham() + ";" +
                        dsSanPham[i].getThuongHieu() + ";" + dsSanPham[i].getSoLuong() + ";" +
                        dsSanPham[i].getGia();
            }
        }
        return s;
    }
    @Override
    public String toString() {
        String result = "\n========================= HOA DON NHAP HANG =========================\n";
        result += "Ma phieu nhap : " + maPhieuNhap + "\n";
        result += "Ngay nhap     : " + ngayNhap + "\n";
        result += "---------------------------------------------------------------------\n";
        result += "Ma Nhan vien  : " +  nv.getMaNhanVien()  + "\n";
        result += "Ten nhan vien : " +  nv.getHoTen() + "\n";
        result += "---------------------------------------------------------------------\n";
        result += "Nha cung cap  : " + nCC.getMaNCC() + "\n";
        result += "Ten nha cung cap : " + nCC.getTenNCC() + "\n";
        result += "---------------------------------------------------------------------\n";
        result += String.format("%-10s %-20s %-15s %-10s %-10s\n",
                "Ma SP", "Ten SP","Thuong Hieu", "SL", "Gia");
        for (SanPham sp : dsSanPham) {
            if (sp != null) {
                result += String.format("%-10s %-20s %-15s %-10d %-10.2f\n",
                        sp.getMaSP(), sp.getTenSanPham(), sp.getThuongHieu(),
                        sp.getSoLuong(), sp.getGia());
            }
        }



        result += "---------------------------------------------------------------------\n";
        result += "Tong so luong : " + tongSoLuong() + "\n";
        result += "Tong tien     : " + tongTien() + "\n";
        result += "Trang thai    : " + (this.trangThai ? "Da thanh toan" : "Chua thanh toan") + "\n";
        result += "=====================================================================\n";
        return result;
    }

    public void xuat() {
        System.out.println(toString());
    }


}

