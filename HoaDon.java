package QuanLyMyPham;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HoaDon {
    private String maHD;
    private LocalDate ngayLap;
    private KhachHang thongTinKH;
    private NhanVien nhanVien;
    private SanPham[] dsSanPham;
    private int[] soLuong;
    private int soLuongThucTe;
    private KhuyenMai khuyenMai;
    private String trangThai;

    public static final String CHUA_THANH_TOAN = "Chua thanh toan";
    public static final String DA_THANH_TOAN = "Da thanh toan";
    public static final String DA_HUY = "Da huy";

    static Scanner sc = new Scanner(System.in);

    public HoaDon() {
        this.maHD = "";
        this.ngayLap = LocalDate.now();
        this.thongTinKH = null;
        this.nhanVien = null;
        this.dsSanPham = null;
        this.soLuongThucTe = 0;
        this.khuyenMai = null;
        this.trangThai = CHUA_THANH_TOAN;
    }

    public HoaDon(String maHD, LocalDate ngayLap, KhachHang thongTinKH, NhanVien nhanVien, SanPham[] dsSanPham, KhuyenMai khuyenMai, String trangThai) {
        this.maHD = maHD;
        this.ngayLap = (ngayLap != null) ? ngayLap : LocalDate.now();
        this.thongTinKH = thongTinKH;
        this.nhanVien = nhanVien;
        this.dsSanPham = dsSanPham;
        this.soLuongThucTe = (dsSanPham != null) ? dsSanPham.length : 0;
        this.khuyenMai = khuyenMai;
        this.trangThai = (trangThai != null) ? trangThai : CHUA_THANH_TOAN;
        if (this.dsSanPham != null) {
            this.soLuong = new int[this.dsSanPham.length];
        }
    }

    // --- NHAP ---
    public void nhap(KhachHang[] dsKH, SanPham[] dsSP, NhanVien[] dsNV) {
        nhanVien = null;
        thongTinKH = null;

        while (true) {
            System.out.print("Nhap ma hoa don: ");
            String input = sc.nextLine().trim();
            if (input.matches("^hd\\d{5}$")) {
                maHD = input;
                break;
            } else {
                System.out.println("Sai dinh dang!");
            }
        }

        while (true) {
            System.out.print("Nhap ma nhan vien: ");
            String maNV = sc.nextLine().trim();

            for (NhanVien nv : dsNV) {
                if (nv != null && nv.getMaNV().equals(maNV)) {
                    nhanVien = nv;
                    break;
                }
            }

            if (nhanVien != null) break;
            System.out.println("Khong tim thay!");
        }

        while (true) {
            System.out.print("Nhap ma KH: ");
            String maKH = sc.nextLine().trim();

            if (!maKH.matches("^\\d{5}$")) {
                System.out.println("Sai dinh dang!");
                continue;
            }

            for (KhachHang kh : dsKH) {
                if (kh != null && kh.getMaKH().equals(maKH)) {
                    thongTinKH = kh;
                    break;
                }
            }

            if (thongTinKH != null) break;

            System.out.print("Khong tim thay, tao moi? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                thongTinKH = new KhachHang();
                thongTinKH.setMaKH(maKH);
                thongTinKH.nhap();
                break;
            }
        }

        int n;
        while (true) {
            try {
                System.out.print("Nhap so loai san pham: ");
                n = Integer.parseInt(sc.nextLine());
                if (n > 0) break;
            } catch (Exception e) {}
            System.out.println("Nhap sai!");
        }

        dsSanPham = new SanPham[n];
        soLuong = new int[n];
        soLuongThucTe = 0;

        for (int i = 0; i < n; i++) {
            while (true) {
                System.out.print("Ma SP " + (i + 1) + ": ");
                String maSP = sc.nextLine().trim();

                SanPham found = null;
                for (SanPham sp : dsSP) {
                    if (sp != null && sp.getMaSP().equals(maSP)) {
                        found = sp;
                        break;
                    }
                }

                if (found != null) {                    
                    int index = -1;
                    for (int k = 0; k < soLuongThucTe; k++) {
                        if (dsSanPham[k].getMaSP().equals(maSP)) {
                            index = k;
                            break;
                        }
                    }

                    if (index != -1) {
                        while (true) {
                            try {
                                System.out.print("San pham da ton tai, nhap them so luong: ");
                                int sl = Integer.parseInt(sc.nextLine());
                                if (sl > 0) {
                                    soLuong[index] += sl;
                                    break;
                                }
                            } catch (Exception e) {}
                            System.out.println("Sai!");
                        }
                        continue;
                    }

                    dsSanPham[soLuongThucTe] = found;
                    while (true) {
                        try {
                            System.out.print("So luong: ");
                            int sl = Integer.parseInt(sc.nextLine());
                            if (sl > 0) {
                                soLuong[soLuongThucTe] = sl;
                                break;
                            }
                        } catch (Exception e) {}
                        System.out.println("Sai!");
                    }

                    soLuongThucTe++;
                    break;
                } else {
                    System.out.println("Khong tim thay!");
                }
            }
        }

        System.out.print("Co KM? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            khuyenMai = new KhuyenMai();
            khuyenMai.nhap();
        }

        while (true) {
            try {
                System.out.print("Trang thai (1-Chua TT, 2-Da TT, 3-Huy): ");
                int chon = Integer.parseInt(sc.nextLine());

                if (chon == 1) {
                    trangThai = CHUA_THANH_TOAN;
                    break;
                } else if (chon == 2) {
                    trangThai = DA_THANH_TOAN;
                    break;
                } else if (chon == 3) {
                    trangThai = DA_HUY;
                    break;
                }
            } catch (Exception e) {}
            System.out.println("Nhap sai!");
        }
    }
    // ================= GIÁ NIÊM YẾT  =================
    public void tinhGiaNiemYet() {
        System.out.println("Gia niem yet: " + tinhTongTienHang());
    }

    // ================= TÍNH TIỀN =================
    private double tinhTongTienHang() {
        double tong = 0;

        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsSanPham[i] != null) {
                tong += dsSanPham[i].getGia() * soLuong[i];
            }
        }
        return tong;
    }
    public double tinhTienKhuyenMai() {
        if (khuyenMai == null) return 0;
    
    // Lấy thứ hạng khách hàng (Ví dụ: "Vang", "Kim cuong")
        String loaiKH = (thongTinKH != null) ? thongTinKH.getThuHang() : "Normal";

    // TRƯỜNG HỢP 1: KHUYẾN MÃI HÓA ĐƠN
        if (khuyenMai.getLoaiKM() == KhuyenMai.KM_HOA_DON) {
            return khuyenMai.tinhGiamGiaDuKien(tinhTongTienHang(), ngayLap, loaiKH, null);
        }

    // TRƯỜNG HỢP 2: KHUYẾN MÃI SẢN PHẨM
        double tongGiam = 0;
        if (khuyenMai.getLoaiKM() == KhuyenMai.KM_SAN_PHAM) {
            for (int i = 0; i < soLuongThucTe; i++) {
                if (dsSanPham[i] == null) continue;

                double giaTriItem = dsSanPham[i].getGia() * soLuong[i];
            // Tính giảm giá riêng cho từng sản phẩm khớp mã
                tongGiam += khuyenMai.tinhGiamGiaDuKien(giaTriItem, ngayLap, loaiKH, dsSanPham[i].getMaSP());
            }
        }

        return tongGiam;
    }

    public double tinhTongTien() {
        return Math.max(0, tinhTongTienHang() - tinhTienKhuyenMai());
    }

    public double tinhTienPhaiTra() {
        return tinhTongTien();
    }
    // --- THEM SAN PHAM ---
    public void themSanPham(SanPham sp, int sl) {
        if (sp == null || sl <= 0) return;

        if (dsSanPham == null) {
            dsSanPham = new SanPham[10];
            soLuong = new int[10];
            soLuongThucTe = 0;
        }

        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsSanPham[i].getMaSP().equals(sp.getMaSP())) {
                soLuong[i] += sl;
                return;
            }
        }

        if (soLuongThucTe == dsSanPham.length) {
            SanPham[] newSP = new SanPham[dsSanPham.length * 2];
            int[] newSL = new int[soLuong.length * 2];

            for (int i = 0; i < soLuongThucTe; i++) {
                newSP[i] = dsSanPham[i];
                newSL[i] = soLuong[i];
            }

            dsSanPham = newSP;
            soLuong = newSL;
        }

        dsSanPham[soLuongThucTe] = sp;
        soLuong[soLuongThucTe] = sl;
        soLuongThucTe++;
    }

    // --- XOA SAN PHAM ---
    public void xoaSanPham(String maSP) {
        if (dsSanPham == null || soLuongThucTe == 0) return;

        int index = -1;
        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsSanPham[i].getMaSP().equals(maSP)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Khong tim thay san pham!");
            return;
        }

        for (int i = index; i < soLuongThucTe - 1; i++) {
            dsSanPham[i] = dsSanPham[i + 1];
            soLuong[i] = soLuong[i + 1];
        }

        dsSanPham[soLuongThucTe - 1] = null;
        soLuong[soLuongThucTe - 1] = 0;
        soLuongThucTe--;
    }
    public void suaSanPham(String maSP, int soLuongMoi) {
        if (dsSanPham == null || soLuongThucTe == 0) return;

        if (soLuongMoi <= 0) {
            System.out.println("So luong khong hop le!");
            return;
        }

        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsSanPham[i].getMaSP().equals(maSP)) {
                soLuong[i] = soLuongMoi;
                System.out.println("Cap nhat thanh cong!");
                return;
            }
        }

        System.out.println("Khong tim thay san pham!");
    }

    // --- KHO ---
    public boolean kiemTraDuHang() {
        if (dsSanPham == null) return true;

        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsSanPham[i] != null && dsSanPham[i].getSoLuongTonKho() < soLuong[i]) {
                System.out.println("Khong du hang: " + dsSanPham[i].getMaSP());
                return false;
            }
        }
        return true;
    }

    public void truKho() {
        for (int i = 0; i < soLuongThucTe; i++) {
            dsSanPham[i].setSoLuongTon(
                dsSanPham[i].getSoLuongTonKho() - soLuong[i]
            );
        }
    }

    public void hoanKho() {
        for (int i = 0; i < soLuongThucTe; i++) {
            dsSanPham[i].setSoLuongTon(
                dsSanPham[i].getSoLuongTonKho() + soLuong[i]
            );
        }
    }

    public void thanhToan() {
        if (DA_THANH_TOAN.equals(trangThai)) {
            System.out.println("Da thanh toan roi!");
            return;
        }

        if (!kiemTraDuHang()) {
            if(khuyenMai != null){
                double tienGiam = tinhTienKhuyenMai();
                String loaiKH = (thongTinKH != null) ? thongTinKH.getThuHang(): "Normal";
                String maSP = (soLuongThucTe > 0) ? dsSanPham[0].getMaSP(): null;
                khuyenMai.xacNhanSuDung(tienGiam, ngayLap, loaiKH, maSP);
            }
            truKho();
            trangThai = DA_THANH_TOAN;

            System.out.println("Thanh toan thanh cong!");
        }
    }

    public void huyHoaDon() {
        if (DA_HUY.equals(trangThai)) return;

        if (DA_THANH_TOAN.equals(trangThai)) {
            hoanKho();
        }

        trangThai = DA_HUY;
        System.out.println("Da huy!");
    }
    
    // --- XUAT ---
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        sb.append("\n===== HOA DON =====\n");
        sb.append(String.format("Ma HD: %-10s | Ngay: %-12s | Trang thai: %-15s\n",
                maHD, ngayLap.format(dtf), trangThai));

        sb.append("\n--- Khach hang ---\n");
        sb.append(thongTinKH != null ? thongTinKH.toString() : "Chua co\n");

        sb.append("\n--- Nhan vien ---\n");
        sb.append(nhanVien != null ? nhanVien.toString() : "Chua co\n");

        sb.append("\n--- San pham ---\n");
        for (int i = 0; i < soLuongThucTe; i++) {
            double tt = dsSanPham[i].getGia() * soLuong[i];
            sb.append(dsSanPham[i].toString())
              .append(" | SL: ").append(soLuong[i])
              .append(" | TT: ").append(String.format("%.2f", tt))
              .append("\n");
        }

        sb.append(String.format("\n--- THANH TOAN ---\n"));
        sb.append(String.format("Gia niem yet: %.2f\n", tinhTongTienHang()));
        sb.append(String.format("Tien giam: %.2f\n", tinhTienKhuyenMai()));
        
        sb.append(String.format("Thanh tien: %.2f\n", tinhTienPhaiTra()));
        return sb.toString();
    }

    public void xuat() {
        System.out.println(this.toString());
    }
}