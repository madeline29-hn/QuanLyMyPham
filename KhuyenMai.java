package QuanLyMyPham;

import java.time.LocalDate;
import java.util.Scanner;

public class KhuyenMai {

    private String maKM;
    private String tenKM;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private double phanTramGiam;
    private int loaiKM; // 1: SP, 2: Hóa đơn
    private double giaTriToiThieu;
    private double giamToiDa;
    private int soLuongMa;
    private int soLuotDaDung;
    private String dieuKienApDung;
    private boolean dangKichHoat;
    private String maSanPhamApDung;
    private String loaiKhachHangApDung;
    private double tongTienDaGiam;

    public static final int KM_SAN_PHAM = 1;
    public static final int KM_HOA_DON = 2;

    public static Scanner sc = new Scanner(System.in);

    // --- CONSTRUCTOR ---
    public KhuyenMai() {
        this.dangKichHoat = true;
        this.soLuotDaDung = 0;
        this.tongTienDaGiam = 0;
        this.loaiKhachHangApDung = "Tat ca";
    }

    public KhuyenMai(String maKM, String tenKM, LocalDate ngayBatDau, LocalDate ngayKetThuc,
                     double phanTramGiam, int loaiKM, double giaTriToiThieu,
                     double giamToiDa, int soLuongMa, String dieuKienApDung,
                     String maSanPhamApDung, String loaiKhachHangApDung) {

        this();
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.phanTramGiam = phanTramGiam;
        this.loaiKM = loaiKM;
        this.giaTriToiThieu = giaTriToiThieu;
        this.giamToiDa = giamToiDa;
        this.soLuongMa = soLuongMa;
        this.dieuKienApDung = dieuKienApDung;
        this.maSanPhamApDung = maSanPhamApDung;
        this.loaiKhachHangApDung = loaiKhachHangApDung;
    }

    // --- GETTERS & SETTERS ---
    public String getMaKM() { return maKM; }
    public void setMaKM(String maKM) { this.maKM = maKM; }

    public String getTenKM() { return tenKM; }
    public void setTenKM(String tenKM) { this.tenKM = tenKM; }

    public LocalDate getNgayBatDau() { return ngayBatDau; }
    public void setNgayBatDau(LocalDate ngayBatDau) { this.ngayBatDau = ngayBatDau; }

    public LocalDate getNgayKetThuc() { return ngayKetThuc; }
    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        if (this.ngayBatDau != null && ngayKetThuc.isBefore(this.ngayBatDau)) {
            System.out.println("Loi: Ngay ket thuc phai sau ngay bat dau!");
        } else {
            this.ngayKetThuc = ngayKetThuc;
        }
    }

    public double getPhanTramGiam() { return phanTramGiam; }
    public void setPhanTramGiam(double phanTramGiam) {
        if (phanTramGiam >= 0 && phanTramGiam <= 100)
            this.phanTramGiam = phanTramGiam;
    }

    public int getLoaiKM() { return loaiKM; }

    public void setLoaiKM(int loaiKM) {
        if (loaiKM != KM_SAN_PHAM && loaiKM != KM_HOA_DON) {
            System.out.println("Loai khuyen mai khong hop le!");
            return;
        }

        this.loaiKM = loaiKM;

        if (loaiKM == KM_HOA_DON) {
            this.maSanPhamApDung = null;
        }
    }

    public double getGiaTriToiThieu() { return giaTriToiThieu; }
    public void setGiaTriToiThieu(double giaTriToiThieu) {
        if (giaTriToiThieu >= 0) {
            this.giaTriToiThieu = giaTriToiThieu;
        } else {
            System.out.println("Gia tri toi thieu phai >= 0!");
        }
    }

    public double getGiamToiDa() { return giamToiDa; }
    public void setGiamToiDa(double giamToiDa) { this.giamToiDa = giamToiDa; }

    public int getSoLuongMa() { return soLuongMa; }
    public void setSoLuongMa(int soLuongMa) { this.soLuongMa = soLuongMa; }

    public int getSoLuotDaDung() { return soLuotDaDung; }

    public String getDieuKienApDung() { return dieuKienApDung; }
    public void setDieuKienApDung(String dieuKienApDung) { this.dieuKienApDung = dieuKienApDung; }

    public boolean isDangKichHoat() { return dangKichHoat; }
    public void setDangKichHoat(boolean dangKichHoat) { this.dangKichHoat = dangKichHoat; }

    public String getMaSanPhamApDung() { return maSanPhamApDung; }
    public void setMaSanPhamApDung(String maSanPhamApDung) { this.maSanPhamApDung = maSanPhamApDung; }

    public String getLoaiKhachHangApDung() { return loaiKhachHangApDung; }
    public void setLoaiKhachHangApDung(String loaiKhachHangApDung) { this.loaiKhachHangApDung = loaiKhachHangApDung; }

    public double getTongTienDaGiam() { return tongTienDaGiam; }

    // --------
    public boolean conHieuLuc(LocalDate ngayMua) {
        if (!dangKichHoat) return false;
        if (soLuongMa > 0 && soLuotDaDung >= soLuongMa) return false;
        if (ngayMua == null || ngayBatDau == null || ngayKetThuc == null) return false;

        return (!ngayMua.isBefore(ngayBatDau) && !ngayMua.isAfter(ngayKetThuc));
    }

    public double tinhGiamGiaDuKien(double giaTri, LocalDate ngayMua, String loaiKH, String maSP) {
        if (!conHieuLuc(ngayMua)) return 0;
    
    // Kiểm tra điều kiện loại khách hàng áp dụng
        if (loaiKhachHangApDung != null && !loaiKhachHangApDung.equalsIgnoreCase("Tat ca")) {
            if (loaiKH == null) return 0;
        
        //  kiểm tra hạng Vàng/Kim Cương
            if (!(loaiKH.equalsIgnoreCase("Vang") || loaiKH.equalsIgnoreCase("Kim cuong"))) {
                return 0;
            }     
        // Nếu yêu cầu hạng cụ thể (ví dụ: "Bac"), phải khớp chính xác
            else if (!loaiKhachHangApDung.equalsIgnoreCase(loaiKH.trim())) {
            return 0;
            }
        }

        double tienGiam = 0;
        if (loaiKM == KM_HOA_DON) {
            if (giaTri < giaTriToiThieu) return 0;
            tienGiam = giaTri * (phanTramGiam / 100);
        } else if (loaiKM == KM_SAN_PHAM) {
            if (maSanPhamApDung == null || !maSanPhamApDung.equalsIgnoreCase(maSP)) return 0;
            tienGiam = giaTri * (phanTramGiam / 100);
        }

        if (giamToiDa > 0 && tienGiam > giamToiDa) tienGiam = giamToiDa;
        return Math.max(tienGiam, 0);
    }

    public double tinhTienSauGiam(double giaTri, LocalDate ngayMua, String loaiKH, String maSP) {
        double giam = tinhGiamGiaDuKien(giaTri, ngayMua, loaiKH, maSP);
        return giaTri - giam;
    }

    public boolean xacNhanSuDung(double tienGiam, LocalDate ngayMua, String loaiKH, String maSP) {
        if (!dangKichHoat) return false;
        if (!conHieuLuc(ngayMua)) return false;

        if (soLuongMa > 0 && soLuotDaDung >= soLuongMa) return false;

        this.soLuotDaDung++;
        this.tongTienDaGiam += tienGiam;

        return true;
    }

    public void hoanTacSuDung(double tienGiam) {
        if (soLuotDaDung > 0) {
            this.soLuotDaDung--;
            this.tongTienDaGiam -= tienGiam;
            if (this.tongTienDaGiam < 0) this.tongTienDaGiam = 0;
        }
    }
    public double tinhGiaNiemYet(double giaTri) {
        return giaTri;
    }

    // --- NHẬP ---
    public void nhap() {
        System.out.println("--- NHAP KHUYEN MAI ---");

        while (true) {
            System.out.print("Ma KM (KMxxxxx): ");
            String input = sc.nextLine();

            if (input.matches("^KM\\d{5}$")) {
                maKM = input;
                break;
            }
            System.out.println("Sai dinh dang!");
        }

        System.out.print("Ten KM: ");
        tenKM = sc.nextLine();

        while (true) {
            try {
                System.out.print("Ngay BD (yyyy-mm-dd): ");
                ngayBatDau = LocalDate.parse(sc.nextLine());

                System.out.print("Ngay KT (yyyy-mm-dd): ");
                LocalDate kt = LocalDate.parse(sc.nextLine());

                if (kt.isBefore(ngayBatDau)) {
                    System.out.println("Loi: Ngay ket thuc phai sau ngay bat dau!");
                } else {
                    ngayKetThuc = kt;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Sai dinh dang!");
            }
        }

        while (true) {
            try {
                System.out.print("Loai (1-SP,2-HD): ");
                int loai = Integer.parseInt(sc.nextLine());

                if (loai == KM_SAN_PHAM || loai == KM_HOA_DON) {
                    setLoaiKM(loai);
                    break;
                } else {
                    System.out.println("Chi duoc nhap 1 hoac 2!");
                }
            } catch (Exception e) {
                System.out.println("Nhap so hop le!");
            }
        }

        while (true) {
            try {
                System.out.print("Phan tram giam: ");
                phanTramGiam = Double.parseDouble(sc.nextLine());

                if (phanTramGiam >= 0 && phanTramGiam <= 100) break;
                else System.out.println("Phai tu 0-100!");
            } catch (Exception e) {
                System.out.println("Nhap so hop le!");
            }
        }

        while (true) {
            try {
                System.out.print("Gia tri toi thieu: ");
                giaTriToiThieu = Double.parseDouble(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Nhap so hop le!");
            }
        }

        System.out.print("Giam toi da: ");
        giamToiDa = Double.parseDouble(sc.nextLine());

        System.out.print("So luong ma: ");
        soLuongMa = Integer.parseInt(sc.nextLine());

        if (loaiKM == KM_SAN_PHAM) {
            System.out.print("Ma SP ap dung: ");
            maSanPhamApDung = sc.nextLine();
        } else {
            maSanPhamApDung = null;
        }

        System.out.print("Loai KH ap dung: ");
        loaiKhachHangApDung = sc.nextLine();

        System.out.print("Dieu kien: ");
        dieuKienApDung = sc.nextLine();
    }

    public void xuat() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        String loai = (loaiKM == KM_SAN_PHAM) ? "SP" : "HD";
        String status = dangKichHoat ? "Mo" : "Khoa";

        return String.format("|%-7s|%-15s|%2.0f%%|%s|%s->%s|Con:%d/%d|Max:%,.0f|KH:%s|%s|",
                maKM, tenKM, phanTramGiam, loai,
                ngayBatDau, ngayKetThuc,
                (soLuongMa - soLuotDaDung), soLuongMa,
                giamToiDa,
                loaiKhachHangApDung,
                status
        );
    }
}