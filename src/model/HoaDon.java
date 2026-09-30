package model;
import manager.*;

import java.nio.channels.Pipe.SourceChannel;

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
    private KhuyenMai[] kmSanPham;
    private KhuyenMai kmHoaDon;
    private String trangThai;

    public static final String CHUA_THANH_TOAN = "Chua thanh toan";
    public static final String DA_THANH_TOAN = "Da thanh toan";
    public static final String DA_HUY = "Da huy";
    static Scanner sc = new Scanner(System.in);

    public HoaDon() {
        this.maHD = "";
        this.ngayLap = LocalDate.now();
        this.trangThai = CHUA_THANH_TOAN;
    }

    public HoaDon(String maHD, LocalDate ngayLap, KhachHang thongTinKH,
                  NhanVien nhanVien, SanPham[] dsSanPham, int[] soLuong, int soLuongThucTe,
                  KhuyenMai[] kmSanPham, KhuyenMai kmHoaDon, String trangThai) {

        this.maHD = maHD;
        this.ngayLap = (ngayLap != null) ? ngayLap : LocalDate.now();
        this.thongTinKH = thongTinKH;
        this.nhanVien = nhanVien;
        this.dsSanPham = dsSanPham;
        this.soLuongThucTe = (dsSanPham != null) ? dsSanPham.length : 0;
        this.kmSanPham = kmSanPham;
        this.kmHoaDon = kmHoaDon;
        this.trangThai = (trangThai != null) ? trangThai : CHUA_THANH_TOAN;

        if (this.dsSanPham != null) {
            this.soLuong = new int[this.dsSanPham.length];
        }
    }

    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public LocalDate getNgayLap() { return ngayLap; }
    public void setNgayLap(LocalDate ngayLap) { this.ngayLap = ngayLap; }

    public KhachHang getThongTinKH() { return thongTinKH; }
    public void setThongTinKH(KhachHang thongTinKH) { this.thongTinKH = thongTinKH; }

    public NhanVien getNhanVien() { return nhanVien; }
    public void setNhanVien(NhanVien nhanVien) { this.nhanVien = nhanVien; }

    public SanPham[] getDsSanPham() { return dsSanPham; }
    public void setDsSanPham(SanPham[] dsSanPham) { this.dsSanPham = dsSanPham; }

    public int[] getSoLuong() { return soLuong; }
    public void setSoLuong(int[] soLuong) { this.soLuong = soLuong; }

    public int getSoLuongThucTe() { return soLuongThucTe; }
    public void setSoLuongThucTe(int soLuongThucTe) { this.soLuongThucTe = soLuongThucTe; }

    public KhuyenMai[] getKmSanPham() { return kmSanPham; }
    public void setKmSanPham(KhuyenMai[] kmSanPham) { this.kmSanPham = kmSanPham;}

    public KhuyenMai getKmHoaDon() { return kmHoaDon; }
    public void setKmHoaDon(KhuyenMai kmHoaDon) { this.kmHoaDon = kmHoaDon;}

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public double tinhTongTienHang() {
        double tong = 0;
        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsSanPham[i] != null) {
                tong += dsSanPham[i].giaNiemYet() * soLuong[i];
            }
        }
        return tong;
    }
    public double tinhTienKhuyenMai() {
        String loaiKH = (thongTinKH != null) ? thongTinKH.getThuHang() : "dong";
        double tongGiam = 0;

        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsSanPham[i] == null || kmSanPham[i] == null) continue;
            double giaTri = dsSanPham[i].giaNiemYet() * soLuong[i];
            tongGiam += kmSanPham[i].tinhGiamGiaDuKien(giaTri, ngayLap, loaiKH, dsSanPham[i].getMaSP());
        }

        if (kmHoaDon != null && thongTinKH instanceof KhachHangVIP) {
            double tongTien = tinhTongTienHang();
            tongGiam += kmHoaDon.tinhGiamGiaDuKien(tongTien, ngayLap, loaiKH, null);
        }

        return tongGiam;
    }

    public double tinhTongTien() {
        double tongGoc = tinhTongTienHang();
        double giamKM = tinhTienKhuyenMai();

        double sauKM = tongGoc - giamKM;

        double giamVIP = 0;
        if (thongTinKH instanceof KhachHangVIP) {
            giamVIP = ((KhachHangVIP) thongTinKH).tinhGiamVIP(sauKM);
        }

        double tongSauCung = Math.min(sauKM, sauKM - giamVIP);
        return Math.max(0, tongSauCung);
    }

    public double tinhTienPhaiTra() {
        return tinhTongTien();
    }

    public KhuyenMai chonKMChoSanPham(SanPham sp, KhuyenMai[] dsKhuyenMai ,int soLuong) {
        if (dsKhuyenMai == null) return null;
        KhuyenMai best = null;
        double maxGiam = 0;
        String loaiKH = (thongTinKH != null) ? thongTinKH.getThuHang() : "dong";
        for (KhuyenMai km : dsKhuyenMai) {
            if (km == null) continue;
            if (km.getLoaiKM() != KhuyenMai.KM_SAN_PHAM) continue;

            double giam = km.tinhGiamGiaDuKien(sp.giaNiemYet() * soLuong, ngayLap, loaiKH, sp.getMaSP());
            if (giam > maxGiam) {
                maxGiam = giam;
                best = km;
            }
        }
        return best;
    }
    public KhuyenMai chonKMHoaDon(KhuyenMai[] dsKhuyenMai, double tongTien) {
        if (dsKhuyenMai == null) return null;
        KhuyenMai best = null;
        double maxGiam = 0;
        String loaiKH = (thongTinKH != null) ? thongTinKH.getThuHang() : "dong";
        for (KhuyenMai km : dsKhuyenMai) {
            if (km == null) continue;
            if (km.getLoaiKM() != KhuyenMai.KM_HOA_DON) continue;

            double giam = km.tinhGiamGiaDuKien(tongTien, ngayLap, loaiKH,null);
            if (giam > maxGiam) {
                maxGiam = giam;
                best = km;
            }
        }
        return best;
    }
    public void tuDongChonKhuyenMai(KhuyenMai[] dsKhuyenMai) {
        if (dsKhuyenMai == null) return;
        for (int i = 0; i < soLuongThucTe; i++) {
            SanPham sp = dsSanPham[i];
            if (sp == null) continue;

            kmSanPham[i] = chonKMChoSanPham(sp, dsKhuyenMai, soLuong[i]);
        }
        double tongTien = tinhTongTienHang();
        KhuyenMai bestHD = chonKMHoaDon(dsKhuyenMai, tongTien);

        if (thongTinKH instanceof KhachHangVIP) {
            kmHoaDon = bestHD;
        } else {
            kmHoaDon = null;
        }
    }

    public void thanhToan() {
        if (DA_THANH_TOAN.equals(trangThai) || DA_HUY.equals(trangThai)) return;
        if (!kiemTraDuHang()) {
            System.out.println("Khong du hang!");
            return;
        }
        String loaiKH = (thongTinKH != null) ? thongTinKH.getThuHang() : "dong";

        for (int i = 0; i < soLuongThucTe; i++) {
            if (kmSanPham[i] != null) {
                double giaTri = dsSanPham[i].giaNiemYet() * soLuong[i];

                double giam = kmSanPham[i].tinhGiamGiaDuKien(
                        giaTri, ngayLap,
                        (thongTinKH != null ? thongTinKH.getThuHang(): "dong"),
                        dsSanPham[i].getMaSP());

                kmSanPham[i].xacNhanSuDung(
                        giam, ngayLap,
                        (thongTinKH != null ? thongTinKH.getThuHang() : "dong"),
                        dsSanPham[i].getMaSP()
                );
            }
        }

        if (kmHoaDon != null && thongTinKH instanceof KhachHangVIP) {
            double tong = tinhTongTienHang();
            double giam = kmHoaDon.tinhGiamGiaDuKien(
                    tong, ngayLap,
                    (thongTinKH != null ? thongTinKH.getThuHang() : "dong"),
                    null
            );

            kmHoaDon.xacNhanSuDung(
                    giam, ngayLap,
                    (thongTinKH != null ? thongTinKH.getThuHang() : "dong"),
                    null
            );
        }
        truKho();
        trangThai = DA_THANH_TOAN;
        System.out.println("Thanh toan thanh cong!");
        if (thongTinKH != null) {
            double tien = tinhTongTien();
            thongTinKH.capNhatSauMua(tien);
            System.out.println("Da cap nhat khach hang!");
        }
        if (thongTinKH instanceof KhachHangVIP) {
            ((KhachHangVIP) thongTinKH).quaSN(ngayLap);
        }
    }
    public int tongSoLuongDaChon(String maSP){
        int tong = 0;
        for (int i=0; i < soLuongThucTe; i++){
            if(dsSanPham[i] != null && dsSanPham[i].getMaSP().equals(maSP)){
                tong += soLuong[i];
            }
        }
        return tong;
    }

    public boolean kiemTraDuHang() {
        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsSanPham[i].getSoLuong() < soLuong[i]) {
                return false;
            }
        }
        return true;
    }

    public void truKho() {
        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsSanPham[i] != null){
                dsSanPham[i].setSoLuong(
                        dsSanPham[i].getSoLuong() - soLuong[i]);
            }
        }
    }

    public void hoanKho() {
        for (int i = 0; i < soLuongThucTe; i++) {
            dsSanPham[i].setSoLuong(
                    dsSanPham[i].getSoLuong() + soLuong[i]
            );
        }
    }
   
    public void huyHoaDon() {
        if (DA_HUY.equals(trangThai)) {
            System.out.println("Hoa don da huy roi!");
            return;
        }

        if (DA_THANH_TOAN.equals(trangThai)) {
            System.out.println("Hoa don da thanh toan, khong the huy!");
            return;
        }
        trangThai = DA_HUY;
        System.out.println("Huy hoa don thanh cong!");
    }

    public void themSanPham(SanPham sp, int sl) {
        if (sp == null || sl <= 0) return;
        if (dsSanPham == null) {
            dsSanPham = new SanPham[10];
            soLuong = new int[10];
            soLuongThucTe = 0;
            kmSanPham = new KhuyenMai[10];
        }
        int daChon = tongSoLuongDaChon(sp.getMaSP());
        if (sp.getSoLuong() < daChon + sl){
            System.out.println("Khong du hang! (Da chon: "+ daChon + ", Con kho: "+ sp.getSoLuong() + ")");
            return;
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
            KhuyenMai[] newKM = new KhuyenMai[(kmSanPham != null ? kmSanPham.length : 10) * 2];

            for (int i = 0; i < soLuongThucTe; i++) {
                newSP[i] = dsSanPham[i];
                newSL[i] = soLuong[i];
                newKM[i] = kmSanPham[i];
            }

            dsSanPham = newSP;
            soLuong = newSL;
            kmSanPham = newKM;
        }
        dsSanPham[soLuongThucTe] = sp;
        soLuong[soLuongThucTe] = sl;
        kmSanPham[soLuongThucTe] = null;
        soLuongThucTe++;
        this.tuDongChonKhuyenMai(kmSanPham);
    }

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
            kmSanPham[i] = kmSanPham[i + 1];

        }
        dsSanPham[soLuongThucTe - 1] = null;
        soLuong[soLuongThucTe - 1] = 0;
        soLuongThucTe--;
        System.out.println("Da xoa san pham");
    }

    public void suaSanPham(String maSP, int soLuongMoi) {
        if (dsSanPham == null || soLuongThucTe == 0) return;

        if (soLuongMoi <= 0) {
            System.out.println("So luong khong hop le!");
            return;
        }

        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsSanPham[i].getMaSP().equalsIgnoreCase(maSP)) {

                int daChonKhac = tongSoLuongDaChon(maSP) - soLuong[i];

                if (soLuongMoi + daChonKhac > dsSanPham[i].getSoLuong()) {
                    System.out.println("Khong du hang!");
                    return;
                }

                soLuong[i] = soLuongMoi;
                System.out.println("Cap nhat thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay san pham!");
    }
    public void nhap(KhachHang[] dsKH, SanPham[] dsSP, NhanVien[] dsNV, QuanLyHoaDon qlhd, KhuyenMai[] dsKhuyenMai) {
        while (true) {
            System.out.print("Nhap ma hoa don (HDxxxxx): ");
            String ma = sc.nextLine().trim();
            if (ma.matches("HD\\d{5}") && !qlhd.kiemTraTrungMa(ma)) {
                this.maHD = ma;
                break;
            }
            System.out.println("Ma khong hop le (phai la HDxxxxx) hoac bi trung!");
        }

        if (this.nhanVien != null) {
            System.out.println("Nhan vien: "
                    + nhanVien.getMaNhanVien() + " - " + nhanVien.getHoTen());
        }
        while (true) {
            System.out.print("Nhap ma khach hang: ");
            String maKH = sc.nextLine().trim();

            for (KhachHang kh : dsKH) {
                if (kh != null && kh.getMaKH().equalsIgnoreCase(maKH)) {
                    KhachHang fullKH = new KhachHang();
                    this.thongTinKH = kh;
                    break;
                }
            }

            if (this.thongTinKH != null) {
                System.out.println("Khach hang: " + thongTinKH.getHoTen());
                break;
            } else {

                System.out.println("Khong tim thay khach hang!");
                System.out.print("Ban co muon tao moi khach hang nay khong? (y/n): ");
                String choice = sc.nextLine().trim();

                if (choice.equalsIgnoreCase("y")) {

                    KhachHang khMoi = new KhachHang();
                    khMoi.setMaKH(maKH);
                    int soLuongKHThucTe = 0;
                    for (KhachHang kh : dsKH) {
                        if (kh != null) soLuongKHThucTe++;
                    }
                    khMoi.nhap(dsKH, soLuongKHThucTe);
                    for (int i = 0; i < dsKH.length; i++) {
                        if (dsKH[i] == null) {
                            dsKH[i] = khMoi;
                            break;
                        }
                    }
                    this.thongTinKH = khMoi;
                    System.out.println("Da tao moi va gan khach hang vao hoa don.");
                    break;
                } else {
                    System.out.println("Vui long nhap lai ma khach hang hop le!");
                }
            }
        }

        int n;
        while (true) {
            System.out.print("Nhap so luong loai san pham muon mua (>0): ");
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n <= 0) {
                    System.out.println("So luong phai lon hon 0!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }

        this.dsSanPham = new SanPham[n];
        this.soLuong = new int[n];
        this.kmSanPham = new KhuyenMai[n];
        this.soLuongThucTe = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- San pham thu " + (i + 1) + " ---");
            while (true) {
                System.out.print("Nhap ten hoac ma san pham: ");
                String input = sc.nextLine().trim().toLowerCase();

                SanPham spFound = null;

                for (SanPham sp : dsSP) {
                    if (sp != null && sp.getMaSP().equalsIgnoreCase(input)) {
                        spFound = sp;
                        break;
                    }
                }

                if (spFound == null) {
                    System.out.println("Danh sach san pham tim duoc:");

                    boolean coTimThay = false;
                    for (SanPham sp : dsSP) {
                        if (sp != null && sp.getTenSanPham().toLowerCase().contains(input)) {
                            System.out.println(
                                    sp.getMaSP() + " | " +
                                            sp.getTenSanPham() + " | Gia: " + sp.giaNiemYet()
                            );
                            coTimThay = true;
                        }
                    }

                    if (!coTimThay) {
                        System.out.println("Khong tim thay san pham!");
                        continue;
                    }

                    System.out.print("Nhap ma san pham ban muon chon: ");
                    String maChon = sc.nextLine().trim();

                    for (SanPham sp : dsSP) {
                        if (sp != null && sp.getMaSP().equalsIgnoreCase(maChon)) {
                            spFound = sp;
                            break;
                        }
                    }

                    if (spFound == null) {
                        System.out.println("Ma khong hop le!");
                        continue;
                    }
                }

                System.out.println("Ten SP: " + spFound.getTenSanPham() + " | Gia: " + spFound.giaNiemYet());
                int sl;
                while (true) {
                    System.out.print("Nhap so luong mua (>0): ");
                    sl = Integer.parseInt(sc.nextLine());
                    if (sl <= 0) {
                        System.out.println("So luong phai lon hon 0!");
                        continue;
                    }
                    break;
                }

                int daChon = tongSoLuongDaChon(spFound.getMaSP());

                while (true) {
                    if (daChon + sl <= spFound.getSoLuong()) {
                        break;
                    }

                    int tonThucTe = spFound.getSoLuong() - daChon;

                    System.out.println("Khong du hang (Da chon: " + daChon + ", Con kho: " + tonThucTe + ")");
                    System.out.print("Nhap lai so luong: ");
                    sl = Integer.parseInt(sc.nextLine());

                    if (sl <= 0) {
                        System.out.println("So luong phai > 0!");
                    }
                }

                if (spFound == null) {
                    continue;
                }
                themSanPham(spFound, sl);
                break;
            }
        }

        System.out.println("\nNhap hoa don thanh cong!");
        System.out.println("Tong tien tam tinh: " + String.format("%,.2f", this.tinhTongTienHang()));
        System.out.print("\nBan co muon ap dung Khuyen Mai khong? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Chon cach ap dung (1-Nhap tay tung mon, 2-Tu dong chon KM tot nhat): ");
            int choiceKM = Integer.parseInt(sc.nextLine());

            if (choiceKM == 1) {
                for (int i = 0; i < soLuongThucTe; i++) {
                    System.out.println("Nhap KM cho san pham " + dsSanPham[i].getTenSanPham() + "? (y/n): ");
                    if (sc.nextLine().equalsIgnoreCase("y")) {
                        this.kmSanPham[i] = new KhuyenMai();
                        this.kmSanPham[i].nhap();
                    }
                }
                System.out.print("Nhap KM cho tong hoa don? (y/n): ");
                if (sc.nextLine().equalsIgnoreCase("y")) {
                    this.kmHoaDon = new KhuyenMai();
                    this.kmHoaDon.nhap();
                }
            } else {
                this.tuDongChonKhuyenMai(dsKhuyenMai);
                System.out.println("Da tu dong ap dung cac ma KM tot nhat.");
            }
        } else {
            System.out.println("Khong ap dung khuyen mai!");
        }

        System.out.println("\nChon trang thai hoa don:");
        System.out.println("1. Chua thanh toan");
        System.out.println("2. Da thanh toan");
        System.out.println("3. Da huy");
        System.out.print("Chon (1-3): ");
        int choiceTT = Integer.parseInt(sc.nextLine());
        switch (choiceTT) {
            case 1: this.trangThai = CHUA_THANH_TOAN; break;
            case 2:
                this.thanhToan();   
                this.trangThai = DA_THANH_TOAN;
                break;
            case 3: this.trangThai = DA_HUY; break;
            default: this.trangThai = CHUA_THANH_TOAN;
        }

        System.out.println("\nNhap hoa don thanh cong!");
        System.out.println("Tong tien phai tra: " + String.format("%,.2f", this.tinhTongTien()));

    }

    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        sb.append("\n======================================== HOA DON =========================================\n\n");
        sb.append(String.format("Ma HD: %-10s | Ngay: %-12s | Trang thai: %-15s\n", maHD, ngayLap.format(dtf), trangThai));
        sb.append("\n\n================================== THONG TIN KHACH HANG ==================================\n\n");
        if(thongTinKH != null){
            sb.append(String.format("%-10s | %-30s | %-30s", "MaKH", "Ho Ten", "Sdt"));
            sb.append("\n");
            sb.append(String.format("%-10s | %-30s | %-30s\n", thongTinKH.getMaKH(), thongTinKH.getHoTen(), thongTinKH.getSdt()));
        } else{
            sb.append("Khong tim thay thong tin khach hang!");
        }
        sb.append("\n\n======================================== NHAN VIEN =======================================\n\n");
        if(nhanVien != null ){
            sb.append(String.format("%-10s | %-30s  ", "MaNV", "Ho Ten"));
            sb.append("\n");
            sb.append(String.format("%-10s | %-30s\n", nhanVien.getMaNhanVien(), nhanVien.getHoTen()));
        } else {
            sb.append("Chua co nhan vien\n");
        }
        sb.append("\n\n======================================== SAN PHAM ========================================\n\n");
        sb.append("------------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-10s | %-20s | %-5s | %-12s | %-12s | %-15s\n",
                "MaSP", "Ten", "SL", "Gia", "Thanh tien", "Khuyen Mai"));

        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsSanPham[i] == null) continue;
            double gia = dsSanPham[i].giaNiemYet();
            double tt = gia * soLuong[i];
            String km = (kmSanPham[i] != null) ? kmSanPham[i].getMaKM(): "Khong co";
            sb.append(String.format("%-10s | %-20s | %-5d | %-12.2f | %-12.2f | %-15s\n",
                    dsSanPham[i].getMaSP(),
                    dsSanPham[i].getTenSanPham(),
                    soLuong[i],
                    gia,
                    tt,
                    km));
        }
        sb.append("------------------------------------------------------------------------------------------\n");
        double tongGoc = tinhTongTienHang();
        double giamKM = tinhTienKhuyenMai();
        double sauKM = tongGoc - giamKM;

        double giamVIP = 0;
        if (thongTinKH instanceof KhachHangVIP) {
            giamVIP = ((KhachHangVIP) thongTinKH).tinhGiamVIP(sauKM);
        }

        double thanhTien = Math.max(0, sauKM - giamVIP);

        sb.append("\n====================================== THANH TOAN ========================================\n");
        sb.append(String.format("Gia niem yet   : %,.2f\n", tongGoc));
        sb.append(String.format("Giam khuyen mai: %,.2f\n", giamKM));
        sb.append(String.format("Sau KM         : %,.2f\n", sauKM));
        sb.append(String.format("Giam VIP       : %,.2f\n", giamVIP));
        sb.append(String.format("Thanh tien     : %,.2f\n", thanhTien));
        sb.append("==========================================================================================\n");

        return sb.toString();
    }

    public void xuat() {
        System.out.println(this.toString());
    }
    public String outputFile() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.maHD).append(";")
                .append(this.ngayLap).append(";")
                .append(this.thongTinKH != null ? this.thongTinKH.getMaKH() : "null").append(";")
                .append(this.nhanVien != null ? this.nhanVien.getMaNhanVien() : "null").append(";")
                .append(this.trangThai).append(";")
                .append(this.kmHoaDon != null ? this.kmHoaDon.getMaKM() : "Null").append(";");
        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsSanPham[i] != null) {
                sb.append(dsSanPham[i].getMaSP()).append(",")
                        .append(soLuong[i]).append(",")
                        .append(kmSanPham[i] != null ? kmSanPham[i].getMaKM() : "Null");
                if (i < soLuongThucTe - 1) sb.append("|");
            }
        }
        return sb.toString();
    }
}


