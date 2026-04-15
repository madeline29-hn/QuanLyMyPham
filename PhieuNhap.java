package QuanLyMyPham;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class PhieuNhap {
    private LocalDate ngayNhap;
    private NhanVien nv;
    private NhaCungCap nCC;
    private SanPham[] dsNhapHang;
    private String maPhieuNhap;
    private String trangThai;
    private int soLuongSP;
    static Scanner sc = new Scanner(System.in);
    public PhieuNhap() {}

    public PhieuNhap(String maPhieuNhap, LocalDate ngayNhap, NhanVien nv, NhaCungCap nCC,int soLuongSP, SanPham[] dsNhapHang, String trangThai) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.nv = nv;
        this.nCC = nCC;
        this.dsNhapHang = new SanPham[100];
        this.trangThai = "Chua thanh toan"; 
        this.soLuongSP = soLuongSP;
    }
    public String getMaPhieuNhap() {
        return maPhieuNhap;
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
    public SanPham[] getDsNhapHang() {
        return dsNhapHang;
    }
    public void setDsNhapHang(SanPham[] dsNhapHang) {
        this.dsNhapHang = dsNhapHang;
    }
    public String getTrangThai() {
        return trangThai;
    }
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuong(int soLuong) {
        this.soLuongSP = soLuong;
    }
    public String capNhattrangThai() {
        if (trangThai.equalsIgnoreCase("Chua thanh toan")) {
            trangThai = "Da thanh toan";
        } else {
            trangThai = "Chua thanh toan";
        }
        return trangThai;

    }
    public void themSanPham(SanPham sp) {
       // boolean timThay = false;
       
        for (int i = 0; i < dsNhapHang.length; i++) {
            if (dsNhapHang[i] != null && dsNhapHang[i].getMaSP().equals(sp.getMaSP())) {
                dsNhapHang[i].setSoLuong(dsNhapHang[i].getSoLuong() + sp.getSoLuong());
                return;
                //timThay = true;
                //break;
            }
         /*  if (timThay == false && dsNhapHang[i] == null) {
                dsNhapHang[i] = sp;
                break;
            }*/  
        }
        for (int i = 0; i < dsNhapHang.length; i++) {
            if (dsNhapHang[i] == null) {
                dsNhapHang[i] = sp;
                return;
            }
    } }
    public SanPham timKiemSP(String maSP) {
        for (SanPham sp : dsNhapHang) {
            if (sp != null && sp.getMaSP().equalsIgnoreCase(maSP)) {
                System.out.println("San pham tim thay: " + sp);
                return sp;
            }
        }
        System.out.println("Khong tim thay san pham co ma " + maSP);
        return null;
    }
    public void suaSanPham(String maSPSua) {
        boolean timThay = false;
        //SanPham spTonTai = timKiemSP(maSPSua);
        for (int i = 0; i < dsNhapHang.length; i++) {
            if (dsNhapHang[i] != null && dsNhapHang[i].getMaSP().equalsIgnoreCase(maSPSua)) {
                timThay = true;
                System.out.println("Da tim thay san pham co ma " + maSPSua + ". Vui long nhap thong tin san pham moi:");
                SanPham spMoi = new SanPham();
                spMoi.nhap();
                dsNhapHang[i] = spMoi;
                System.out.println("San pham " + maSPSua + " da duoc sua.");
                break;
            }
        }
        if (!timThay) {
            System.out.println("Khong tim thay san pham co ma " + maSPSua);
        }
    }
    public void xoaSanPham(String maSPXoa) {
        boolean timThay = false;
        for (int i = 0; i < dsNhapHang.length; i++) {
            if (dsNhapHang[i] != null && dsNhapHang[i].getMaSP().equalsIgnoreCase(maSPXoa)) {
                dsNhapHang[i] = dsNhapHang[dsNhapHang.length - 1];
                dsNhapHang[dsNhapHang.length - 1] = null;
                soLuongSP--;
                System.out.println("San pham " + maSPXoa + " da duoc xoa.");
                break;
            }
            if (!timThay && dsNhapHang[i] == null) {
                System.out.println("Khong tim thay san pham co ma " + maSPXoa);
                break;
            }
        }
        System.out.println("Khong tim thay san pham co ma " + maSPXoa);
    }


    public int tongSoLuong() {
        int tong = 0;
        for (SanPham sp : dsNhapHang) {
            if (sp != null) {
                tong += sp.getSoLuong();
            }
        }
        return tong;
    }
    public double tongTien() {
        double tong = 0;
        for (int i = 0; i < dsNhapHang.length; i++) {
            if (dsNhapHang[i] != null) {
                tong += dsNhapHang[i].getSoLuong() * dsNhapHang[i].getGia();
            }
        }
        return tong;
    }
    public void nhap() {
        while(true) {
            System.out.println("Nhap ma phieu nhap (PN + 5 chu so): ");
            this.maPhieuNhap = sc.nextLine().trim();
            if (this.maPhieuNhap.matches("\\d{5}")) {
                setMaPhieuNhap("PN" + this.maPhieuNhap);
                break;
            } else {
                System.out.println("Ma phieu nhap phai co dang 'PN' theo sau la 5 chu so!");
            }
        }
        while(true) {
            System.out.println("Nhap ma nhan vien nhap kho: ");
            this.nv = new ThuNgan();
            String maNVInput = sc.nextLine().trim();
            if (maNVInput.matches("\\d{5}")) {
                this.nv.setMaNhanVien("NV" + maNVInput);
                break;
            } else {
                System.out.println("Ma nhan vien phai co dang 'NV' theo sau la 5 chu so!");
            }
        }
        while (true) {
            System.out.println("Nhap ten nhan vien nhap kho: ");
            String hoTen = sc.nextLine().trim();
            if (hoTen.matches("[a-zA-Z ]+")) {
                this.nv.setHoTen(hoTen);
                break;
            } else {
                System.out.println("Ten nhan vien khong duoc de trong! Vui long nhap lai!");
            }
        }
        while(true) {
            System.out.println("Nhap ma nha cung cap: ");
            String maNCCInput = sc.nextLine().trim();
            if (maNCCInput.matches("\\d{5}")) {
                this.nCC = new NhaCungCap();
                this.nCC.setMaNCC("NCC" + maNCCInput);
                break;
            } else {
                System.out.println("Ma nha cung cap phai co dang 'NCC' theo sau la 5 chu so!");
            }
        }
        while (true) {
            System.out.println("Nhap ten nha cung cap: ");
            String tenNCC = sc.nextLine().trim();
            if (tenNCC.matches("[a-zA-Z ]+")) {
                this.nCC.setTenNCC(tenNCC);
                break;
            } else {
                System.out.println("Ten nha cung cap khong duoc de trong! Vui long nhap lai!");
            }
        }
        while (true) {
            System.out.print("Nhap ngay nhap (yyyy-MM-dd): ");
            String ngayNhapInput = sc.nextLine().trim();

            
            if (ngayNhapInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
                String[] parts = ngayNhapInput.split("-");
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);
                if (month >= 1 && month <= 12) {
    
                    int maxDay;
                    switch (month) {
                        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                            maxDay = 31; break;
                        case 4: case 6: case 9: case 11:
                            maxDay = 30; break;
                        case 2:
                
                            boolean leap = (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
                            maxDay = leap ? 29 : 28;
                            break;
                        default:
                            maxDay = 0;
                    }
                    if (day >= 1 && day <= maxDay) {
                        LocalDate ngayNhapTachDate = LocalDate.of(year, month, day);
                        if (!ngayNhapTachDate.isAfter(LocalDate.now())) {
                            setNgayNhap(ngayNhapTachDate);
                            break;
                        } else {
                            System.out.println("Ngay nhap khong duoc lon hon ngay hien tai! Vui long nhap lai!");
                        }
                    } else {
                        System.out.println("Ngay khong hop le trong thang " + month);
                    }
                } else {
                    System.out.println("Thang phai tu 1 den 12!");
                }
            } else {
                System.out.println("Dinh dang ngay khong hop le, vui long nhap lai (yyyy-MM-dd)!");
            }
        }

        for (int i = 0; i < dsNhapHang.length; i++) {
            System.out.println("Nhap thong tin san pham thu " + (i + 1) + ": ");
            SanPham sp = new SanPham();
            sp.nhap();
            themSanPham(sp);
            System.out.println("Ban co muon nhap san pham tiep theo khong? (Yes/No)");
            String luaChon = sc.nextLine().trim();
            if (!luaChon.equalsIgnoreCase("Yes")) {
                break;
            }
        }


    }    
        @Override
    public String toString() {
        String result = "\n========================= HOA DON NHAP HANG =========================\n";
        result += "Ma phieu nhap : " + maPhieuNhap + "\n";
        result += "Ngay nhap     : " + ngayNhap + "\n";
        result += "---------------------------------------------------------------------\n";
        result += "Ma Nhan vien     : " + nv.getMaNhanVien() + "\n";
        result += "Ten nhan vien : " + nv.getHoTen() + "\n";
        result += "---------------------------------------------------------------------\n";
        result += "Nha cung cap  : " + nCC.getMaNCC() + "\n";
        result += "Ten nha cung cap : " + nCC.getTenNCC() + "\n";
        result += "---------------------------------------------------------------------\n";
        result += String.format("%-10s %-20s %-15s %-10s %-10s\n",
                                "Ma SP", "Ten SP","Thuong Hieu", "SL", "Gia");
        for (SanPham sp : dsNhapHang) {
            if (sp != null) {
                result += String.format("%-10s %-20s %-15s %-10d %-10.2f\n",
                                        sp.getMaSP(), sp.getTenSanPham(), sp.getThuongHieu(),
                                        sp.getSoLuong(), sp.getGia());
            }
        }

        

        result += "---------------------------------------------------------------------\n";
        result += "Tong so luong : " + tongSoLuong() + "\n";
        result += "Tong tien     : " + tongTien() + "\n";
        result += "=====================================================================\n";
        return result;
    }

    public void xuat() {
        System.out.println(toString());
    }
    

    
}
