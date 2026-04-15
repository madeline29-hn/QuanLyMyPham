package QuanLyMyPham;

import java.time.LocalDate;

public class ThuNgan extends NhanVien {
    private String maQuay;
    private int soHoaDon;
    private int caLam;


    public ThuNgan() {
        super();
        this.setLuongCoBan(300000);
        this.setNgayCong(0);
    }

    public ThuNgan(String hoTen, String sdt, String email, String gioiTinh, LocalDate ngaySinh,
                   String maNhanVien, String soCCCD, double ngayCong, float luongCoBan, String maQuay, int soHoaDon, int caLam) {
        super(hoTen, sdt, email, gioiTinh, ngaySinh,maNhanVien,soCCCD,ngayCong,luongCoBan); // Gọi constructor lớp cha
        this.maQuay = maQuay;
        this.soHoaDon = soHoaDon;
        this.caLam = caLam;
    }

    public String getMaQuay() { return maQuay; }
    public void setMaQuay(String maQuay) { this.maQuay = maQuay; }

    public int getSoHoaDon() { return soHoaDon; }
    public void setSoHoaDon(int soHoaDon) { this.soHoaDon = soHoaDon; }

    public int getCaLam() { return caLam; }
    public void setCaLam(int caLam) { this.caLam = caLam; }
    public void xuatHD() {
        System.out.println("-> Thu ngan " + getMaNhanVien() + " dang thuc hien: XUAT HOA DON.");
    }

    public double doanhThuCa() {
        return this.soHoaDon * 200000;
    }

    @Override
    public double tinhLuong() {
        double tongLuong = getLuongCoBan() * getNgayCong();
        //System.out.printf("Tong luong Thu Ngan [%s]: %,.0f VND\n", getMaNhanVien(), tongLuong);
        return tongLuong;
    }

//    @Override
//    public String getVaiTro() {
//        return "Vai tro: Thu Ngan (Thanh toan tai quay " + this.maQuay + ")";
//    }

    @Override
    public String getVaiTro() {
        return "Thu Ngan";
    }

    @Override
    public void nhap() {
        super.nhap();
        while (true) {
            System.out.print("Nhap ma quay (chon 1-4): ");
            String input = sc.nextLine().trim();
            if (input.matches("[1-4]")) { // Regex kiểm tra đúng 1 ký tự từ 1 đến 4
                this.maQuay = "MQ" + input;
                break;
            } else {
                System.out.println("Loi: Ma quay chi duoc phep chon tu 1 den 4");
            }
        }

        // Ràng buộc Số hóa đơn (> 0)
        while (true) {
            System.out.print("Nhap so hoa don: ");
            try {
                this.soHoaDon = Integer.parseInt(sc.nextLine());
                if (this.soHoaDon > 0) {
                    break;
                } else {
                    System.out.println("Loi: So hoa don phai lon hon 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap mot so nguyen!");
            }
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


    @Override
    public String toString() {
        return super.toString() + String.format(" %-5s | %-5d | %-5s | %-10.0f |",
                maQuay,
                soHoaDon,
                (caLam == 1 ? "Sang" : "Chieu"),
                doanhThuCa()
        );
    }

    @Override
    public void xuat() {
        super.xuat();

        System.out.printf("| %-10s | %-10s | %-10s | %-15s |\n",
                "Ma quay", "So HD", "Ca lam", "Doanh thu");

        System.out.printf("| %-10s | %-10d | %-10s | %-15.0f |\n",
                maQuay,
                soHoaDon,
                (caLam == 1 ? "Sang" : "Chieu"),
                doanhThuCa()
        );

        System.out.println("-----------------------------------------------------------------------");
    }
    }