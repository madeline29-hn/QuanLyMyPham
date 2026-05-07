package QuanLyMyPham;
import java.time.LocalDate;
public class NhanVienCSKH extends NhanVien {
    private double diemDanhGia;
    private int caLam;

    public NhanVienCSKH() {
        super();
        this.setLuongCoBan(300000);
        this.setNgayCong(0);
    }

    public NhanVienCSKH(String hoTen, String sdt, String email, String gioiTinh, LocalDate ngaySinh,
                        String maNhanVien, String soCCCD, double ngayCong, float luongCoBan, double diemDanhGia, int caLam) {
        super(hoTen, sdt, email, gioiTinh, ngaySinh, maNhanVien, soCCCD, ngayCong, luongCoBan);
        this.diemDanhGia = diemDanhGia;
        this.caLam = caLam;
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

    @Override
    public double tinhLuong() {
        return (double) (getLuongCoBan() * getNgayCong());
    }

    @Override
    public String getVaiTro() {
        return "CSKH";
    }

    @Override
    public void nhap(NhanVien[] ds, int soLuong) {
        super.nhap(ds, soLuong);
        while (true) {
            try {
                System.out.print("Nhap diem danh gia (0-5): ");
                this.diemDanhGia = Double.parseDouble(sc.nextLine());
                if (this.diemDanhGia >= 0 && this.diemDanhGia <= 5) break;
                System.out.println("Loi: Diem phai tu 0 den 5!");
            } catch (Exception e) {
                System.out.println("Loi: Vui long nhap dung dinh dang so!");
            }
        }

        while (true) {
            try {
                System.out.print("Chon ca lam (1: 8h-15h, 2: 15h-22h): ");
                int chonCa = Integer.parseInt(sc.nextLine());
                if (chonCa == 1 || chonCa == 2) {
                    this.caLam = chonCa;
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
        return super.toString() + String.format(" %-9.1f | %-8s |",
                this.diemDanhGia,
                (caLam == 1 ? "Sang" : "Chieu")
        );
    }

    @Override
    public void xuat() {
        System.out.println(this.toString());
    }

    @Override
    public String toDataString() {
        return super.toDataString() + ";" + diemDanhGia + ";" + caLam;
    }

    @Override
    public void fromString(String[] data) {
        super.fromString(data);
        this.diemDanhGia = Double.parseDouble(data[12]);
        this.caLam = Integer.parseInt(data[13]);
    }
}