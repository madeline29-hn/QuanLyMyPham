package QuanLyMyPham;

import java.time.LocalDate;

public class NhanVienCSKH extends NhanVien {
    private double hoaHong;
    private double diemDanhGia;
    private int caLam;
    public NhanVienCSKH() {
        super();
        this.setLuongCoBan(300000);
        this.setNgayCong(0);
    }

    public NhanVienCSKH(String hoTen, String sdt, String email, String gioiTinh, LocalDate ngaySinh,
                        String maNhanVien, String soCCCD,double ngayCong, float luongCoBan, double hoaHong, double diemDanhGia, int caLam) {
        super(hoTen, sdt, email, gioiTinh, ngaySinh, maNhanVien, soCCCD, ngayCong, luongCoBan);
        this.diemDanhGia = diemDanhGia;
        this.hoaHong = hoaHong;
        this.caLam= caLam;
    }

    public double getHoaHong() {
        return hoaHong;
    }

    public void setHoaHong(double hoaHong) {
        this.hoaHong = hoaHong;
    }

    public double getDiemDanhGia() {
        return diemDanhGia;
    }

    public void setDiemDanhGia(double diemDanhGia) {
        this.diemDanhGia = diemDanhGia;
    }

    public int getCaLam() {
        return caLam;
    }

    public void setCaLam(int caLam) {
        this.caLam = caLam;
    }

    // --- PHƯƠNG THỨC ĐẶC TRƯNG ---
    public void tuVanSP() {
        System.out.println("-> CSKH " + getMaNhanVien() + ": Dang tu van san pham.");
    }

    public void tiepNhanKhieuNai() {
        System.out.println("-> CSKH " + getMaNhanVien() + ": Dang nhan khieu nai.");
    }

    public void giaiQuyetThacMac() {
        System.out.println("-> CSKH " + getMaNhanVien() + ": Dang giai quyet thac mac.");
    }

    @Override
    public double tinhLuong() {
        double tongLuong = (getLuongCoBan() * getNgayCong()) + this.hoaHong;
        //System.out.printf("Tong luong NV CSKH [%s]: %,.0f VND\n", getMaNhanVien(), tongLuong);
       return tongLuong;
    }


    @Override
    public String getVaiTro() {
        return "CSKH";
    }

    @Override
    public void nhap() {
        super.nhap();
        while (true) {
            try {
                System.out.print("Nhap hoa hong: ");
                this.hoaHong = Double.parseDouble(sc.nextLine());
                if (hoaHong >= 0)
                    break;
                else System.out.println("Phải >= 0");
            } catch (Exception e) {
                System.out.println("Phải nhập số!");
            }
        }

        while (true) {
            System.out.print("Nhap diem danh gia (0-5): ");
            this.diemDanhGia = Double.parseDouble(sc.nextLine());
            //this.diemDanhGia = sc.nextDouble();
            if (this.diemDanhGia >= 0 && this.diemDanhGia <= 5) break;
            System.out.println("Loi: Diem phai tu 0 den 5!");
        }
        while (true) {
            try {
                System.out.print("Chon ca lam (1: 8h-15h, 2: 15h-22h): ");
                int chonCa = Integer.parseInt(sc.nextLine());
                if (chonCa == 1 || chonCa == 2) {
                    this.caLam = (int) chonCa; // Lưu 1.0 hoặc 2.0
                    break;
                } else {
                    System.out.println("Loi: Chi co ca 1 hoac ca 2!");
                }
            } catch (Exception e) {
                System.out.println("Loi: Vui long nhap so 1 hoac 2!");
            }
        }
    }
//    @Override
//    public String toString() {
//        // Nối tiếp và thêm HoaHong, DiemDG
//        return super.toString() + String.format(" HH: %,.0f | Diem: %.1f |", hoaHong, diemDanhGia);
//    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %-10.0f | %-5.1f | %-5s |",
                hoaHong,
                diemDanhGia,
                (caLam == 1 ? "Sang" : "Chieu")
        );
    }

    @Override
    public void xuat() {
        super.xuat();

        System.out.printf("| %-10s | %-10.0f | %-10.1f | %-10s |\n",
                "CSKH",
                hoaHong,
                diemDanhGia,
                (caLam == 1 ? "Sang" : "Chieu")
        );

        System.out.println("-----------------------------------------------------------------------");
    }

}